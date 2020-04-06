package DrankVerantwoordelijke;

import JDBC.Drank;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AanvulCell<S, P> extends TableCell<S, Drank> {
    private Button button;
    private TextField aantal;
    private AnchorPane grafic;

    public AanvulCell(){
        this.grafic = new AnchorPane();
        this.button = new Button();
        this.aantal = new TextField();
        aantal.setPrefWidth(40);
        button.setLayoutX(45.0);
        button.setText("add");
        grafic.getChildren().add(button);
        grafic.getChildren().add(aantal);

        setGraphic(grafic);
    }

    @Override
    public void updateItem(Drank item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(grafic);
        }
    }

    public Button getButton(){
        return this.button;
    }

    public TextField getAantal(){
        return this.aantal;
    }
}