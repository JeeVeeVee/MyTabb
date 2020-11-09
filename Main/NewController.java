package Main;

import JDBC.ConnectionProvider;
import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewController {

    public AnchorPane anker;

    private ConnectionProvider provider;
    private DatabaseCommunicator dbc;

    public NewController(ConnectionProvider provider){
        this.provider = provider;
        this.dbc = new DatabaseCommunicator(provider.getConnection());
    }

    public void initialize(){
        List<Leider> allLeiding = new ArrayList<>();
        try {
             allLeiding = dbc.getAllLeiding();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Leider> pseudoAllLeiding = new ArrayList<>();
        pseudoAllLeiding.add(new Leider("Emile", "Vervaeke", 0));
        pseudoAllLeiding.add(new Leider("Emma", "Steyaert", 0));
        pseudoAllLeiding.add(new Leider("Marie", "Wylin", 0));
        pseudoAllLeiding.add(new Leider("Hannah", "Dhaene", 0));
        pseudoAllLeiding.add(new Leider("Jasper", "Vlerick", 0));
        for (int i = 0; i < allLeiding.size(); i += 8){
           for (int j = 0; j < 8 && i + j < allLeiding.size(); j++){
               PersonPane newPersonPane = new PersonPane(allLeiding.get(i + j));
               newPersonPane.setLayoutY(75 + 100 * i / 8);
               newPersonPane.setLayoutX(50 + 100 * j);
               newPersonPane.setPrefHeight(80);
               newPersonPane.setPrefWidth(80);
               anker.getChildren().add(newPersonPane);
           }
        }
    }
}
