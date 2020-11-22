package Login;

import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import main.Controller;
import ZuipGedeelte.DrinkControlla;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class LoginControlla {

    public AnchorPane anker;
    public ImageView leftImage;
    public ImageView rightImage;
    public AnchorPane fouteCodePane;
    public Button retryButton;
    public Button annuleerButton;

    private int[] checkSum;
    private Leider leider;
    private DatabaseCommunicator dbc;
    private Connection connection;
    private Label[] labels;
    private int counter;
    private ArrayList<Node> toBeRefreshedUponRetry;
    private ArrayList<Node> toBeInvisibleUponWrongCode;

    public LoginControlla(Connection connection, Leider leider){
        this.connection = connection;
        this.dbc = new DatabaseCommunicator(connection);
        this.leider = leider;
        System.out.println(leider.getHash());
        checkSum = new int[4];
        counter = 0;
        labels = new Label[4];
        toBeRefreshedUponRetry = new ArrayList<>();
        toBeInvisibleUponWrongCode = new ArrayList<>();
    }

    public void initialize(){
        for (int i = 0; i< 4 ; i++){
            for (int j = 0; j < 3; j++){
                Button button = new Button();
                button.setContentDisplay(ContentDisplay.CENTER);
                button.setLayoutY(200 + i * 80);
                button.setLayoutX(330 + j * 80);
                button.setPrefWidth(75);
                button.setPrefHeight(75);
                button.setFont(new Font(34));
                final int index = i * 3 + j + 1;
                if (index  < 10) {
                    button.setText(Integer.toString(index));
                    button.setOnAction(e -> buttonPress(index));
                } else {
                    if (index ==  10){
                        button.setText("C");
                        button.setOnAction(e -> {
                            toBeRefreshedUponRetry.forEach(node -> anker.getChildren().remove(node));
                            generateLabels();
                            counter = 0;
                            checkSum = new int[4];
                        });
                    } else if (index == 11){
                        button.setText("0");
                        button.setOnAction(e -> buttonPress(index));
                    }  else if (index == 12){
                        button.setText("X");
                        button.setOnAction(e -> back());

                    }
                }
                toBeInvisibleUponWrongCode.add(button);
                anker.getChildren().add(button);
            }
        }
        retryButton.setOnAction(e ->{
            fouteCodePane.setVisible(false);
            generateLabels();
            toBeInvisibleUponWrongCode.forEach(node -> node.setVisible(true));
        });
        generateLabels();
        Image leiderImage = new Image("/properties/images/" + leider.getFirst() + leider.getLast() + ".jpg");
        leftImage.setImage(leiderImage);
        rightImage.setImage(leiderImage);
    }

    public void generateLabels(){
        for (int i = 0; i < 4; i++){
            Label label = new Label();
            label.setFont(new Font(96));
            label.setContentDisplay(ContentDisplay.RIGHT);
            label.setLayoutX(210 + i * 120);
            label.setLayoutY(50);
            label.setPrefWidth(100);
            label.setAlignment(Pos.CENTER);
            //label.setText(Integer.toString(i));
            label.setStyle(" -fx-background-color: #DFB951;\n" +
                    "    -fx-border-radius: 20;\n" +
                    "    -fx-background-radius: 20;\n" +
                    "    -fx-padding: 5;");
            labels[i] = label;
            anker.getChildren().add(label);
            toBeInvisibleUponWrongCode.add(label);
            toBeRefreshedUponRetry.add(label);
        }
        annuleerButton.setOnAction(e -> back());
    }

    public void buttonPress(int i){
        labels[counter].setText("#");
        checkSum[counter] = i;
        counter++;
        if(counter == 4){
            if (calcHash(checkSum) == leider.getHash()){
                login(leider);
            } else{
                fouteCodePane.setVisible(true);
                toBeRefreshedUponRetry.forEach(node -> anker.getChildren().remove(node));
                toBeInvisibleUponWrongCode.forEach(node -> node.setVisible(false));
                counter = 0;
                checkSum = new int[4];
            }
        }

    }

    public int calcHash(int[] integers){
        return (integers[0] * integers[1] * integers[2] + integers[3] + integers[1] * integers[2] * integers[3] + integers[0] +
                integers[0] * integers[2] * integers[3] + integers[1] + integers[0] * integers[1] * integers[3] + integers[2]) % 232;
    }

    public void login(Leider leider){
        System.out.println("login");
        //Load new FXML and assign it to scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ZuipGedeelte/zuip_sample.fxml"));
        DrinkControlla drinkControlla = new DrinkControlla(this.connection, leider);
        fxmlLoader.setController(drinkControlla);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 900, 600);
        Stage stage = (Stage) anker.getScene().getWindow();
        stage.setScene(scene);
    }

    public void back(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/sample.fxml"));
        fxmlLoader.setController(new Controller(connection));
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 900, 600);
        Stage stage = (Stage) anker.getScene().getWindow();
        stage.setScene(scene);
    }
}
