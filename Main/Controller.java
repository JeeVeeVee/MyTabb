package Main;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Properties;

import static jdk.xml.internal.SecuritySupport.getResourceAsStream;

public class Controller {

    public TextField voornaam;
    public TextField achternaam;

    private Properties properties = new Properties();
    private String[] leiding;

    public Controller(){
        try {
            properties.load(Controller.class.getResourceAsStream("/properties/files/allLeiding.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        leiding = properties.getProperty("leiding").split(", ");

    }

    public void launch(){
        String naam = voornaam.getText() + " " + achternaam.getText();
        if(checkIfIn(naam, leiding)){

        } else{
            registreer(naam);
        }
    }

    public boolean checkIfIn(String x, String[] list){
        for(String s : list){
            if(s.equals(x)){
                return true;
            }
        }
        return false;
    }

    public void registreer(String naam){


    }

}
