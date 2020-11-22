package ZuipGedeelte;

import JDBC.*;
import main.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkControlla {
    public Label loggedInAs;
    public AnchorPane anker;
    public AnchorPane gekozen;
    public Label titel;
    public Button schol;
    public Button another;


    private DatabaseCommunicator dbc;
    private Connection connection;
    private Leider leider;
    private Bestelling bestelling;
    private ArrayList<Node> toBeInvisibleUponChoiche;
    private List<Drank> allDrank;
    private List<Node> toBeRemovedUponAnother;

    public DrinkControlla(Connection connection, Leider leider) {
        this.connection = connection;
        this.dbc = new DatabaseCommunicator(connection);
        this.leider = leider;
        try {
            this.allDrank = dbc.getAllDrank();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.bestelling = new Bestelling(leider, allDrank);
        toBeInvisibleUponChoiche = new ArrayList<>();
        toBeRemovedUponAnother = new ArrayList<>();
    }

    public void initialize() {
        for (int i = 0; i < allDrank.size(); i += 4) {
            for (int j = 0; j + i < allDrank.size() && j < 4; j++) {
                final Drank drank = allDrank.get(j + i);
                DrankPane drankPane = new DrankPane(allDrank.get(j + i));
                drankPane.setLayoutX(50 + j * 200);
                drankPane.setLayoutY(125 + (i / 4) * 200);
                drankPane.setPrefWidth(500);
                drankPane.setPrefHeight(200);
                drankPane.setOnMouseClicked(e -> {
                    System.out.println("click");
                    gekozen.setVisible(true);
                    toBeInvisibleUponChoiche.forEach(node -> node.setVisible(false));
                    bestelling.addDrank(drank);
                    generate_bestelling();
                    System.out.println(bestelling.toString());
                });
                anker.getChildren().add(drankPane);
                toBeInvisibleUponChoiche.add(drankPane);
            }
        }
        loggedInAs.setText("logged in as : " + leider.getFirst() + " " + leider.getLast() + ", voorlopige schuld : " + leider.getSchuld());
        toBeInvisibleUponChoiche.add(titel);
        another.setOnAction(e -> {
            gekozen.setVisible(false);
            for(Node node : toBeRemovedUponAnother){
                gekozen.getChildren().remove(node);
            }
            for(Node node : toBeInvisibleUponChoiche){
                node.setVisible(true);
            }
        });
        schol.setOnAction(e -> afsluiten());
    }

    public void generate_bestelling(){
        int i = 0;
        for(Drank drank : bestelling.getMap().keySet()){
            Label newLabel = new Label(drank.getNaam() + " : " + bestelling.getMap().get(drank));
            newLabel.setLayoutY(150 + 50 * i);
            newLabel.setLayoutX(400);
            i++;
            gekozen.getChildren().add(newLabel);
            toBeRemovedUponAnother.add(newLabel);
        }
    }

    public void afsluiten(){
        verwerkBestelling();
        //Load new FXML and assign it to scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/sample.fxml"));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = new ConnectionProvider().getConnection();
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

    public void verwerkBestelling(){
        for(Drank drank : bestelling.getMap().keySet()){
            for(int i = 0 ; i < bestelling.getMap().get(drank); i++) {
                dbc.zuip(leider, drank);
                System.out.println(leider.getFirst() + " zoop een " + drank.getNaam());
            }
        }
    }

}
