package ZuipGedeelte;

import JDBC.ConnectionProvider;
import JDBC.Leider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZuipTestMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("zuip_sample.fxml"));
        loader.setController(new DrinkControlla(new ConnectionProvider().getConnection(), new Leider("Jules", "Vervaeke", 0)));
        Parent root = loader.load();
        primaryStage.setTitle("MyTabb");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

