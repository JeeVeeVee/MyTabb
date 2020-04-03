package Main;

import JDBC.ConnectionProvider;
import JDBC.Leider;
import JDBC.Searcher;
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
        Searcher searcher = new Searcher(provider.getConnection());
        ArrayList<Leider> leiders = null;
        try {
            leiders = (ArrayList<Leider>) searcher.getAllLeiding();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Leider leider : leiders) {
            if(leider.getFirst() == voornaam.getText() && leider.getLast() == achternaam.getText()) {
                Scene scene = new Scene(new ZuipComponent(leider, provider.getConnection()));
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
