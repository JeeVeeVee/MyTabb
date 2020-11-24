package main;

import JDBC.ConnectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    standaard applicatie klasse
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        /*
        omdat de controller van de fxml een connection nodig heeft, moet ik hem hier setten in de plaats van in de fxml.
        Als je loader een private veld heeft, set je die beter hier want anders loopt de fxml vast.
         */
        loader.setController(new Controller(new ConnectionProvider().getConnection()));
        Parent root = loader.load();
        primaryStage.setTitle("MyTabb");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
