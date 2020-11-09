package Login;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class LoginControlla {

    public AnchorPane anker;

    public LoginControlla(){}

    public void initialize(){
         /*<Button contentDisplay="CENTER" layoutX="333.0" layoutY="213.0" mnemonicParsing="false" prefHeight="75.0" prefWidth="75.0" text="1">
         <font>
            <Font size="34.0" />
         </font>
      </Button>*/
        for (int i = 0; i< 4 ; i++){
            for (int j = 0; j < 3; j++){
                Button button = new Button();
                button.setContentDisplay(ContentDisplay.CENTER);
                button.setLayoutY(200 + i * 80);
                button.setLayoutX(330 + j * 80);
                button.setPrefWidth(75);
                button.setPrefHeight(75);
                button.setFont(new Font(34));
                if (i * 3 + j  < 9) {
                    button.setText(Integer.toString(i * 3 + j + 1));
                } else {
                    if (i* 3 + j ==  9){
                        button.setText("C");
                    } else if (i * 3 + j == 10){
                        button.setText("0");
                    }
                }
                if (i * 3 + j  < 11) {
                    anker.getChildren().add(button);
                }
            }
        }


         for (int i = 0; i < 4; i++){
             Label label = new Label();
             label.setFont(new Font(96));
             label.setContentDisplay(ContentDisplay.RIGHT);
             label.setLayoutX(210 + i * 120);
             label.setLayoutY(50);
             label.setAlignment(Pos.CENTER);
             label.setText(Integer.toString(i));
             anker.getChildren().add(label);
         }

    }

}
