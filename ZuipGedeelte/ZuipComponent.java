package ZuipGedeelte;

import JDBC.*;
import errorHandling.MyError;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZuipComponent extends AnchorPane {

    private DatabaseCommunicator dc;

    private Leider leider;

    private ChoiceBox<Drank> assortiment;
    private Button schol;
    private TextField aantal;
    private Label loggedInAs;

    public ZuipComponent(Leider leider, Connection connection){
        super();
        super.setPrefHeight(235.0);
        super.setPrefWidth(355.0);

        dc = new DatabaseCommunicator(connection);

        Font textFont = new Font(20);

        assortiment =  new ChoiceBox<>();
        ArrayList<Drank> dranklijst = null;
        try {
            dranklijst = (ArrayList<Drank>) dc.getAllDrank();
        } catch (SQLException e) {
            MyError myError = new MyError("Could not retrieve drinks from the database");
            myError.launch();
            e.printStackTrace();
        }

        for(Drank drank : dranklijst){
            if(drank.getVoorraad() > 0) {
                assortiment.getItems().add(drank);
            }
        }
        assortiment.setLayoutX(169.0);
        assortiment.setLayoutY(74.0);
        assortiment.setPrefWidth(150.0);
        super.getChildren().add(assortiment);

        schol = new Button();
        schol.setLayoutX(120.0);
        schol.setLayoutY(170.0);
        schol.setPrefHeight(60.0);
        schol.setPrefWidth(107.0);
        schol.setText("SCHOL!");
        schol.setOnAction(e -> drink());
        super.getChildren().add(schol);

        Label keer = new Label("keer");
        keer.setLayoutX(211.0);
        keer.setLayoutY(120.0);
        keer.setFont(textFont);
        super.getChildren().add(keer);

        this.aantal = new TextField();
        aantal.setText("1");
        aantal.setLayoutX(140.0);
        aantal.setLayoutY(115.0);
        aantal.setFont(new Font(20));
        aantal.setPrefWidth(60);
        aantal.setPrefHeight(20);

        super.getChildren().add(aantal);

        Label zinIn = new Label("kheb zin in");
        zinIn.setLayoutX(31.0);
        zinIn.setLayoutY(75.0);
        zinIn.setFont(textFont);
        super.getChildren().add(zinIn);

        Label aantalLabel = new Label("en dat wel ");
        aantalLabel.setLayoutX(31.0);
        aantalLabel.setLayoutY(120.0);
        aantalLabel.setFont(textFont);
        super.getChildren().add(aantalLabel);

        loggedInAs = new Label("ingelogd als " + leider.getFirst() + " " + leider.getLast() + " , schuld = " + Double.toString(leider.getSchuld()));
        loggedInAs.setLayoutX(31.0);
        loggedInAs.setLayoutY(14.0);
        super.getChildren().add(loggedInAs);
        update(leider);
    }

    public void drink(){
        int aantalConsumpties = Integer.parseInt(aantal.getText());
        if((assortiment.getValue() != null) && (aantalConsumpties < assortiment.getValue().getVoorraad())) {
            for (int i = 0; i < aantalConsumpties; i++) {
                dc.zuip(leider, assortiment.getValue());
                try {
                    leider = dc.getuUpdatedLeider(leider);
                } catch (SQLException e) {
                    MyError error = new MyError("something went wrong, your order was not registered");
                    error.launch();
                    e.printStackTrace();
                }
            }
            update(leider);
        }else{
            if(assortiment.getValue() != null){
                MyError error = new MyError("you did not pick a drink");
                error.launch();
            } else {
                MyError error = new MyError("this drink is no longer available");
                error.launch();
            }
        }
    }

    public void update(Leider leider){
        this.leider = leider;

        super.getChildren().remove(loggedInAs);
        loggedInAs = new Label("ingelogd als " + leider.getFirst() + " " + leider.getLast() + " , schuld = " + Double.toString(leider.getSchuld()));
        loggedInAs.setLayoutX(31.0);
        loggedInAs.setLayoutY(14.0);
        super.getChildren().add(loggedInAs);

        super.getChildren().remove(assortiment);
        assortiment =  new ChoiceBox<>();
        ArrayList<Drank> dranklijst = null;
        try {
            dranklijst = (ArrayList<Drank>) dc.getAllDrank();
        } catch (SQLException e) {
            MyError error = new MyError("could not retrieve drinks from database");
            e.printStackTrace();
        }

        for(Drank drank : dranklijst){
            if(drank.getVoorraad() > 0) {
                assortiment.getItems().add(drank);
            }
        }
        assortiment.setLayoutX(169.0);
        assortiment.setLayoutY(74.0);
        assortiment.setPrefWidth(150.0);
        super.getChildren().add(assortiment);
    }
}
