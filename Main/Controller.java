package Main;

import JDBC.ConnectionProvider;
import JDBC.DatabaseCommunicator;
import JDBC.Leider;
import Login.LoginControlla;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public AnchorPane anker;

    private ConnectionProvider provider;
    private DatabaseCommunicator dbc;

    public Controller(ConnectionProvider provider){
        this.provider = provider;
        this.dbc = new DatabaseCommunicator(provider.getConnection());
    }

    public void initialize(){
        List<Leider> allLeiding = new ArrayList<>();
        try {
             allLeiding = dbc.getAllLeiding();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < allLeiding.size(); i += 8){
           for (int j = 0; j < 8 && i + j < allLeiding.size(); j++){
               final Leider leider = allLeiding.get(i + j);
               PersonPane newPersonPane = new PersonPane(allLeiding.get(i + j));
               newPersonPane.setLayoutY(75 + 100 * i / 8);
               newPersonPane.setLayoutX(50 + 100 * j);
               newPersonPane.setPrefHeight(80);
               newPersonPane.setPrefWidth(80);
               newPersonPane.setOnMouseClicked(e -> loadSecondFxml(leider));
               anker.getChildren().add(newPersonPane);
           }
        }
    }
    public void loadSecondFxml(Leider leider){
        //Load new FXML and assign it to scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login/login.fxml"));
        LoginControlla loginControlla = new LoginControlla(provider.getConnection(), leider);
        fxmlLoader.setController(loginControlla);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root, 900, 600);
        Stage stage = (Stage) anker.getScene().getWindow();
        stage.setScene(scene);
    }
}
