package errorHandling;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ErrorController {
    public Label errorMessage;
    public ImageView errorSign;
    public Button close;

    private String reasonForError;

    public ErrorController(String reasonForError){
        this.reasonForError = reasonForError;
    }

    public void initialize(){
        errorMessage.setText(reasonForError);
        errorSign.setImage(new Image("properties/images/error.jpg"));
        close.setOnAction(e -> {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        });
    }
}
