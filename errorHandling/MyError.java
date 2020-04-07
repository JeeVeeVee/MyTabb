package errorHandling;

import JDBC.ConnectionProvider;
import Main.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyError extends Application {
    private String errorMessage;

    public MyError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public void launch(){
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Error.fxml"));
        loader.setController(new ErrorController(errorMessage));
        Parent root = loader.load();
        stage.setTitle("Error");
        stage.setScene(new Scene(root, 135, 300));
        stage.show();
    }
}
