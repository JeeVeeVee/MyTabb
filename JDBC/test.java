package JDBC;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Searcher searcher = new Searcher();
        ArrayList<Leider> lijst = null;
        ArrayList<Drank> dranklijst = null;

            lijst = (ArrayList<Leider>) searcher.getAllLeiding();
            dranklijst = (ArrayList<Drank>) searcher.getAllDrank();

       System.out.println(lijst.get(0).getFirst());
        System.out.printf(dranklijst.get(0).toString());


    }
}
