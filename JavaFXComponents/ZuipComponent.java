package JavaFXComponents;

import JDBC.*;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;

public class ZuipComponent extends AnchorPane {

    private Searcher searcher;
    private Updater updater;
    private Zuiper zuiper;

    private Leider leider;

    private ChoiceBox<Drank> assortiment;
    private Button schol;
    private TextField aantal;


    public ZuipComponent(Leider leider){
        super();
        super.setPrefHeight(235.0);
        super.setPrefWidth(355.0);

        this.leider = leider;

        searcher = new Searcher();
        updater = new Updater();
        zuiper = new Zuiper();

        ArrayList<Node> allChilderen = new ArrayList<>();
        Font textFont = new Font(20);

        Label zinIn = new Label("kheb zin in");
        zinIn.setLayoutX(31.0);
        zinIn.setLayoutY(75.0);
        zinIn.setFont(textFont);
        allChilderen.add(zinIn);

        Label aantalLabel = new Label("en dat wel ");
        aantalLabel.setLayoutX(31.0);
        aantalLabel.setLayoutY(120.0);
        aantalLabel.setFont(textFont);
        allChilderen.add(aantalLabel);

        this.aantal = new TextField();
        aantal.setText("1");
        aantal.setLayoutX(140.0);
        aantal.setLayoutY(115.0);
        aantal.setFont(new Font(20));
        aantal.setPrefWidth(60);
        aantal.setPrefHeight(20);

        allChilderen.add(aantal);


        Label keer = new Label("keer");
        keer.setLayoutX(211.0);
        keer.setLayoutY(120.0);
        keer.setFont(textFont);
        allChilderen.add(keer);

        schol = new Button();
        schol.setLayoutX(120.0);
        schol.setLayoutY(170.0);
        schol.setPrefHeight(60.0);
        schol.setPrefWidth(107.0);
        schol.setText("SCHOL!");
        allChilderen.add(schol);
        schol.setOnAction(e -> drink());

        Label loggedInAs = new Label("ingelogd als " + leider.getFirst() + " " + leider.getLast());
        loggedInAs.setLayoutX(31.0);
        loggedInAs.setLayoutY(14.0);
        allChilderen.add(loggedInAs);

        assortiment =  new ChoiceBox<>();
        ArrayList<Drank> dranklijst = null;
        try {
            dranklijst = (ArrayList<Drank>) searcher.getAllDrank();
        } catch (SQLException e) {
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
        allChilderen.add(assortiment);


        for(Node node : allChilderen){
            super.getChildren().add(node);
        }
    }

    public void drink(){
        int aantalConsumpties = Integer.parseInt(aantal.getText());
        if(assortiment.getValue() != null) {
            for (int i = 0; i < aantalConsumpties; i++) {
                zuiper.zuip(leider, assortiment.getValue());
            }
        }else{
            System.out.println("je koos geen drankje");
        }
    }
}
