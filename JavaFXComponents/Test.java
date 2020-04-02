package JavaFXComponents;

import JDBC.Leider;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new ZuipComponent(new Leider("Jules", "Vervaeke", 0)));
        stage.setScene(scene);
        stage.show();
    }

    public static void test(String[] args){
        launch(args);
    }
}
