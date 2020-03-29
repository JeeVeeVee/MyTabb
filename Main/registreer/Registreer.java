package Main.registreer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Registreer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registreer.fxml"));
        RegistreerController registreerController = new RegistreerController("Jules", "Vervaeke");
        fxmlLoader.setController(registreerController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("MyTabb");
        primaryStage.setScene(new Scene(root, 615, 311));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
