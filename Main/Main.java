package Main;

import JDBC.ConnectionProvider;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(new Controller(new ConnectionProvider()));
        Parent root = loader.load();
        primaryStage.setTitle("MyTabb");
        primaryStage.setScene(new Scene(root, 615, 588));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
