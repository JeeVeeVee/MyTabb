package JDBC;

import java.sql.SQLException;

public class test {
    public static void main(String[] args){
        Searcher searcher = new Searcher();
        try {
            searcher.getAllLeiding();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
