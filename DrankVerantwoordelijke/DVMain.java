package DrankVerantwoordelijke;

import JDBC.ConnectionProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DVMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DV.fxml"));
        loader.setController(new DVController(new ConnectionProvider()));
        Parent root = loader.load();
        stage.setTitle("MyTabb");
        stage.setScene(new Scene(root, 615, 400));
        stage.show();
    }
}
