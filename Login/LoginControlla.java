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
    //zijn in het begin invisible
    public AnchorPane fouteCodePane;
    public Button retryButton;
    public Button annuleerButton;

    //deze zijn allemaal private, maar alleen leider en Connection moeten worden meegegeven in de constructor, zie main.Controller
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
        /*
        als je een foute code hebt ingetikt en je klikt op opnieuw, moeten de vorige elementen moeten worden gerefreshed
        ik doe dit door ze toe te voegen aan de lijst,
        als het voorvalt, verwijder ik dan alle elementen uit de getChilderen van Anker
        vervolgens genereer ik ze opnieuw en voeg ik ze weer toe aan de lijst en aan getChilderen
         */
        toBeRefreshedUponRetry = new ArrayList<>();
        /*
        zelfde principe als toBeRefreshedUponRetry, maar hier moet ik ze gwn op .setVisible(false) zetten
        en ook weer op true als de code fout is
         */
        toBeInvisibleUponWrongCode = new ArrayList<>();
    }

    public void initialize(){
        /*
        deze for voegt alle Buttons toe, alweer dubbele for voor de coordinaten
         */
        for (int i = 0; i< 4 ; i++){
            for (int j = 0; j < 3; j++){
                Button button = new Button();
                button.setContentDisplay(ContentDisplay.CENTER);
                button.setLayoutY(200 + i * 80);
                button.setLayoutX(330 + j * 80);
                button.setPrefWidth(75);
                button.setPrefHeight(75);
                button.setFont(new Font(34));
                /*
                als deze niet final is kan ik hem niet gebruiken in de setOnAction, en eig is ie ook final dusja
                 */
                final int index = i * 3 + j + 1;
                if (index  < 10) {
                    //de eerste 9 moeten natuurlijk gwn op hun index worden gedaan
                    button.setText(Integer.toString(index));
                    button.setOnAction(e -> buttonPress(index));
                } else {
                    if (index ==  10){
                        //links onderdaan staat de clear button, dus das index 10
                        button.setText("C");
                        button.setOnAction(e -> {
                            toBeRefreshedUponRetry.forEach(node -> anker.getChildren().remove(node));
                            generateLabels();
                            counter = 0;
                            checkSum = new int[4];
                        });
                    } else if (index == 11){
                        //onderaan centraal staat de 0 button, dus die moet op setOnAction 0 staan
                        button.setText("0");
                        button.setOnAction(e -> buttonPress(0));
                    }  else if (index == 12){
                        //dit is cancel button, dus gaat je terugbrengen naar het vorige scherm (is het main scherm)
                        button.setText("X");
                        button.setOnAction(e -> back());

                    }
                }
                //alle buttons worden onzichtbaar als je een foute code geeft
                toBeInvisibleUponWrongCode.add(button);
                anker.getChildren().add(button);
            }
        }
        //deze is dus eerst onzichtbaar, wordt zichtbaar als je code fout is
        retryButton.setOnAction(e ->{
            //als je op retry klikt, moet de fouteCodepane dus waar onzichtbaar worden
            fouteCodePane.setVisible(false);
            //de codes moeten dus opnieuw worden gegenereerd
            generateLabels();
            //deze lijn zet alles weer op visible, is een lambda
            toBeInvisibleUponWrongCode.forEach(node -> node.setVisible(true));
        });
        //deze functie generate de labels waar da de puntejs komen van de code
        generateLabels();
        //de images setten op basis van de naam van de leider, net zoals main.PersonPane
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
            //dit is css, gekopieerd van het internet
            label.setStyle(" -fx-background-color: #DFB951;\n" +
                    "    -fx-border-radius: 20;\n" +
                    "    -fx-background-radius: 20;\n" +
                    "    -fx-padding: 5;");
            labels[i] = label;
            anker.getChildren().add(label);
            //moeten natuurlijk wel nog in de 2 arrays voor een foute code
            toBeInvisibleUponWrongCode.add(label);
            toBeRefreshedUponRetry.add(label);
        }
        annuleerButton.setOnAction(e -> back());
    }

    /*
    deze functie verwerkt de input, en update de checksum (met een hash)
     */
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
    //berekent de hash
    public int calcHash(int[] integers){
        return (integers[0] * integers[1] * integers[2] + integers[3] + integers[1] * integers[2] * integers[3] + integers[0] +
                integers[0] * integers[2] * integers[3] + integers[1] + integers[0] * integers[1] * integers[3] + integers[2]) % 232;
    }
    //gaat naar het volgende scherm, analoog met main.controller
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
    //brengt je terug naar het vorige scherm, analoog met main.Controller
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
