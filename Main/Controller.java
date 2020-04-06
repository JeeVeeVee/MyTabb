package Main;

import JDBC.ConnectionProvider;
import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import JavaFXComponents.ZuipComponent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;


public class Controller {

    public TextField voornaam;
    public TextField achternaam;
    public Button launch;

    private ConnectionProvider provider;

    public Controller(ConnectionProvider provider){
        this.provider = provider;
    }

    public void initialize(){
        launch.setOnAction(e -> launch());
    }

    public void launch(){
        System.out.println("ready for lift of");
        DatabaseCommunicator dc = new DatabaseCommunicator(provider.getConnection());
        ArrayList<Leider> leiders = null;
        try {
            leiders = (ArrayList<Leider>) dc.getAllLeiding();
            System.out.println("we got here");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Leider leider : leiders) {
            if(leider.getFirst().equals(voornaam.getText()) && leider.getLast().equals(achternaam.getText())) {
                Scene scene = new Scene(new ZuipComponent(leider, provider.getConnection()));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
