package Login;

import JDBC.ConnectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        //loader.setController(new NewController(new ConnectionProvider()));
        Parent root = loader.load();
        primaryStage.setTitle("MyTabb");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}