package main;

import JDBC.Leider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/*
eigen klasse, je kan het ook zonder doen, maar ik vind het leuk om ze zelf te maken, let hier maar niet te veel op.
 */

public class PersonPane extends Pane {
    private Leider leider;
    private ImageView leiderImageView;

    /*
    standaar constructor, was vooral voor testen, mag eig weg
     */
    public PersonPane(){

    }

    public PersonPane(Leider leider){
        this.leider = leider;
        try {
            /*
            image ophalen op basis van de naam van de leider
             */
            Image leiderImage = new Image("/properties/images/" + leider.getFirst() + leider.getLast() + ".jpg");
            this.leiderImageView = new ImageView(leiderImage);
            this.leiderImageView.setFitHeight(100);
            this.leiderImageView.setFitWidth(100);
            this.getChildren().add(leiderImageView);
        } catch (IllegalArgumentException e){
            //again, testdoeleinden
            System.out.println("@../properties/images/" + leider.getFirst() + leider.getLast() + ".jpg");
        }
    }

    public Leider getLeider(){
        return this.leider;
    }
}
