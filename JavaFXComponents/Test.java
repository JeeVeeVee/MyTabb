package JavaFXComponents;

import JDBC.ConnectionProvider;
import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ConnectionProvider provider = new ConnectionProvider();
        DatabaseCommunicator dc = new DatabaseCommunicator(provider.getConnection());
        ArrayList<Leider> leiders = (ArrayList<Leider>) dc.getAllLeiding();
        Leider leider = leiders.get(0);
        Scene scene = new Scene(new ZuipComponent(leider, provider.getConnection()));
        stage.setScene(scene);
        stage.show();
    }

    public static void test(String[] args){
        launch(args);
    }
}
