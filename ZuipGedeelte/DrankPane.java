package ZuipGedeelte;

import JDBC.Drank;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class DrankPane extends Pane {
    private Drank drank;
    private ImageView drankImageView;

    public DrankPane(){
        super();
    }

    public DrankPane(Drank drank) {
        super();
        this.drank = drank;
        try {
            Image leiderImage = new Image("/properties/images/" + drank.getNaam() + ".jpg");
            this.drankImageView = new ImageView(leiderImage);
            this.drankImageView.setFitHeight(100);
            this.drankImageView.setFitWidth(100);
            this.drankImageView.setFitWidth(200);
            this.drankImageView.setFitHeight(200);
            this.getChildren().add(drankImageView);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Drank getDrank() {
        return drank;
    }
}
