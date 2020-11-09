package Main;

import JDBC.Leider;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

public class PersonPane extends Pane {
    private Leider leider;
    private ImageView leiderImageView;

    public PersonPane(){

    }

    public PersonPane(Leider leider){
        this.leider = leider;
        try {
            Image leiderImage = new Image("/properties/images/" + leider.getFirst() + leider.getLast() + ".jpg");
            this.leiderImageView = new ImageView(leiderImage);
            this.leiderImageView.setFitHeight(100);
            this.leiderImageView.setFitWidth(100);
            this.getChildren().add(leiderImageView);

        } catch (IllegalArgumentException e){
            System.out.println("@../properties/images/" + leider.getFirst() + leider.getLast() + ".jpg");
        }
    }
}