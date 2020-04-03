package JDBC;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionProvider provider = new ConnectionProvider();
        Searcher searcher = new Searcher(provider.getConnection());
        ArrayList<Leider> lijst = null;
        ArrayList<Drank> dranklijst = null;
        lijst = (ArrayList<Leider>) searcher.getAllLeiding();
        dranklijst = (ArrayList<Drank>) searcher.getAllDrank();
        System.out.println(lijst.get(0).toString());
        System.out.printf(dranklijst.get(0).toString());
        Zuiper zuiper = new Zuiper(provider.getConnection());
        zuiper.zuip(lijst.get(0), dranklijst.get(0));
        dranklijst = (ArrayList<Drank>) searcher.getAllDrank();
        lijst = (ArrayList<Leider>) searcher.getAllLeiding();
        System.out.println(lijst.get(0).toString());
    }
}
