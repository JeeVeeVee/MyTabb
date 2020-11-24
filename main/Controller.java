package main;

import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import Login.LoginControlla;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    /*
    algemeen AnchorPane waar ik in de initialize alles aanhang
     */
    public AnchorPane anker;

    /*
    deze heb ik nodig om de Leider objecten uit de db te lezen
     */
    private Connection connection;
    private DatabaseCommunicator dbc;

    public Controller(Connection connection){
        this.connection = connection;
        this.dbc = new DatabaseCommunicator(connection);
    }

    public void initialize(){
        System.out.println("i start initialize");
        /*
        afhalen van de Leiding objecten
         */
        List<Leider> allLeiding = new ArrayList<>();
        try {
             allLeiding = dbc.getAllLeiding();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        in deze for (is een dubbele omdat je een X en een Y coordinaat nodig hebt) voeg ik voor elk object een PersonPane toe.
        Een PersonPane is een FX-klasse die ik zelf heb geschreven (staat in dit mapje, heb je nrml niet nodig)
         */
        for (int i = 0; i < allLeiding.size(); i += 8){
           for (int j = 0; j < 8 && i + j < allLeiding.size(); j++){
               final Leider leider = allLeiding.get(i + j);
               PersonPane newPersonPane = new PersonPane(allLeiding.get(i + j));
               /*
               dit is dus de reden waarom ik een dubbele for gebruik
                */
               newPersonPane.setLayoutY(75 + 100 * i / 8);
               newPersonPane.setLayoutX(50 + 100 * j);
               newPersonPane.setPrefHeight(80);
               newPersonPane.setPrefWidth(80);
               /*
               hierdoor wordt hete volgende scherm geladen als je klikt op een persoon, de methode loadSecondFxml staat op lijn 69
                */
               newPersonPane.setOnMouseClicked(e -> loadSecondFxml(leider));
               /*
               als je dit niet doet dan showt ie niet
                */
               anker.getChildren().add(newPersonPane);
           }
        }
    }
    public void loadSecondFxml(Leider leider){
        //Load new FXML and assign it to scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login/login.fxml"));
        /*
        de controller van /Login/login.fxml heeft 2 private velden, een Connection en een Leider, dus deze set ik hier al
         */
        LoginControlla loginControlla = new LoginControlla(connection, leider);
        fxmlLoader.setController(loginControlla);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 900, 600);
        /*
        door anker.getScene().getWindow() zorg je ervoor dat er geen nieuw scherm komt, maar dat de volgende fxml op hetzelfde scherm komt
         */
        Stage stage = (Stage) anker.getScene().getWindow();
        stage.setScene(scene);
    }
}
