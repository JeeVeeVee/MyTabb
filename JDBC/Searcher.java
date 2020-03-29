package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private static final String JDBC_URL = "jdbc:sqlite:/home/jules/MyDevelopAdventure/Tap.db";

    private Connection connection;

    public Searcher() {
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet search(String sqlStatement){
        try{
            PreparedStatement ps = connection.prepareStatement(sqlStatement);
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void reestablishConnection(){
        try {
            connection.close();
            connection = DriverManager.getConnection(JDBC_URL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getAllLeiding() throws SQLException {
        ResultSet rs = search("SELECT * FROM leiding");
        System.out.println(rs);
        while(rs.next()){
            System.out.println(rs.getString(1));
        }
        reestablishConnection();
    }

    public void getAllDrank(){

    }
}
