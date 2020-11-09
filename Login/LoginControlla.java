package Login;

import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.sql.Connection;

public class LoginControlla {

    public AnchorPane anker;

    private int checkSum;
    private Leider leider;
    private DatabaseCommunicator dbc;
    private Label[] labels;
    private int counter;

    public LoginControlla(){
        checkSum = 0;
        counter = 0;
        labels = new Label[4];
        System.out.println("initialized");
    }

    public LoginControlla(Connection connection, Leider leider){
        this.dbc = new DatabaseCommunicator(connection);
        this.leider = leider;
        checkSum = 0;
        counter = 0;
        labels = new Label[4];
        System.out.println("initialized");
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
                final int index = i * 3 + j;
                if (index  < 9) {
                    button.setText(Integer.toString(index));
                    button.setOnAction(e -> buttonPress(index));
                } else {
                    if (index ==  9){
                        button.setText("C");
                    } else if (index == 10){
                        button.setText("0");
                        button.setOnAction(e -> buttonPress(index));
                    }
                }
                if (i * 3 + j  < 11) {
                    anker.getChildren().add(button);
                }
            }
        }
        for (int i = 0; i < 4; i++){
            Label label = new Label();
            label.setFont(new Font(96));
            label.setContentDisplay(ContentDisplay.RIGHT);
            label.setLayoutX(210 + i * 120);
            label.setLayoutY(50);
            label.setAlignment(Pos.CENTER);
            label.setText(Integer.toString(i));
            labels[i] = label;
            anker.getChildren().add(label);
         }
    }
    public void buttonPress(int i){
        if(counter == 4){
            checkSum = 0;
            counter = 0;
            for(Label label : labels){
                label.setText("");
            }
        }
        labels[counter].setText("#");
        checkSum += i;
        counter++;
    }
}
