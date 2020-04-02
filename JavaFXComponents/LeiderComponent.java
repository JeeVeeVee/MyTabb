package JavaFXComponents;

import JDBC.Leider;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LeiderComponent extends AnchorPane {

    private Label naam;
    private Label schuld;
    private Button zuip;

    private Leider leider;

    public LeiderComponent(Leider leider){
        super();
        this.leider = leider;
        naam = new Label(leider.getFirst() + " " + leider.getLast());
        schuld = new Label("schuld : " + Double.toString(leider.getSchuld()));

    }
}
