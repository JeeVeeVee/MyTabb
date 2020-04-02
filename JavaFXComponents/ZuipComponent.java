package JavaFXComponents;

import JDBC.Drank;
import JDBC.Leider;
import JDBC.Searcher;
import JDBC.Updater;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ZuipComponent extends AnchorPane {

    private Searcher searcher;
    private Updater updater;

    private Leider leider;

    private ComboBox<Drank> assortiment;
    private Button schol;

    public ZuipComponent(Leider leider){
        super();
        super.setPrefHeight(235.0);
        super.setPrefWidth(355.0);

        this.leider = leider;

        searcher = new Searcher();
        updater = new Updater();

        ArrayList<Node> allChilderen = new ArrayList<>();
        Font textFont = new Font(20);

        Label zinIn = new Label("kheb zin in");
        zinIn.setLayoutX(31.0);
        zinIn.setLayoutY(75.0);
        zinIn.setFont(textFont);
        allChilderen.add(zinIn);

        schol = new Button();
        schol.setLayoutX(116.0);
        schol.setLayoutY(125.0);
        schol.setPrefHeight(60.0);
        schol.setPrefWidth(107.0);
        schol.setText("SCHOL!");
        allChilderen.add(schol);

        Label loggedInAs = new Label("ingelogd als " + leider.getFirst() + " " + leider.getLast());
        loggedInAs.setLayoutX(31.0);
        loggedInAs.setLayoutY(14.0);
        allChilderen.add(loggedInAs);

        assortiment =  new ComboBox<>();
        assortiment.setLayoutX(169.0);
        assortiment.setLayoutY(74.0);
        assortiment.setPrefWidth(150.0);
        allChilderen.add(assortiment);

        for(Node node : allChilderen){
            super.getChildren().add(node);
        }
    }
}
