package DrankVerantwoordelijke;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AanvulCell<S, P> extends TableCell<S, Button> {
    private Button button;

    public AanvulCell(){
        this.button = new Button();

        setGraphic(button);
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(button);
        }
    }

    public Button getButton(){
        return this.button;
    }
}