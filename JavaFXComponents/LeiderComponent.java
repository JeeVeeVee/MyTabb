package JavaFXComponents;

import JDBC.Leider;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class LeiderComponent extends AnchorPane {

    private Label naam;
    private Label schuld;
    private Button zuip;

    private Leider leider;

    public LeiderComponent(Leider leider){
        super();
        this.leider = leider;
        super.setPrefHeight(243.0);
        super.setPrefWidth(472.0);
        this.leider = leider;
        naam = new Label("welkom " + leider.getFirst() + " " + leider.getLast());
        Font nameFont = new Font(54.0);
        naam.setFont(nameFont);


        schuld = new Label("schuld : " + Double.toString(leider.getSchuld()));
        Font schuldFont = new Font(25.0);
        schuld.setFont(schuldFont);
        schuld.setLayoutX(50.0);
        schuld.setLayoutY(160.0);

        Button zuipKnop = new Button();
        zuipKnop.setLayoutX(350.0);
        zuipKnop.setLayoutY(160.0);
        zuipKnop.setText("kben dorstig");

        this.getChildren().addAll(naam, schuld, zuipKnop);
    }
}
