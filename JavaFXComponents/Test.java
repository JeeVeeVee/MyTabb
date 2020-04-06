package JavaFXComponents;

import JDBC.ConnectionProvider;
import JDBC.Leider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ConnectionProvider provider = new ConnectionProvider();
        Searcher searcher = new Searcher(provider.getConnection());
        ArrayList<Leider> leiders = (ArrayList<Leider>) searcher.getAllLeiding();
        Leider leider = leiders.get(0);
        Scene scene = new Scene(new ZuipComponent(leider, provider.getConnection()));
        stage.setScene(scene);
        stage.show();
    }

    public static void test(String[] args){
        launch(args);
    }
}
