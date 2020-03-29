package Main.registreer;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

import static javafx.application.Platform.exit;

public class RegistreerController {

    public TextField voornaam;
    public TextField achternaam;

    private String voorNaam;
    private String achterNaam;

    public RegistreerController(String x, String y){
        voorNaam = x;
        achterNaam = y;
    }

    public void initialize(){
        voornaam.setText(voorNaam);
        achternaam.setText(achterNaam);
    }

    public void done(){
        String path = "/home/jules/MyTab/src/properties/files/leiding/";
        path += voornaam.getText();
        path += achternaam.getText();
        path += ".properties";
        File file = new File(path);
        System.out.println("we got here");
        try {
            if(file.createNewFile()){
                System.out.println("we created a file");
            }
        } catch (IOException e) {
            System.out.printf("something went wrong");
            e.printStackTrace();
        }
        exit();
    }
}
