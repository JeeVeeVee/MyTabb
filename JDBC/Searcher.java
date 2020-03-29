package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private static final String JDBC_URL = "jdbc:sqlite:/home/jules/MyTab/src/Tap.db";

    private Connection connection;

    public Searcher() throws ClassNotFoundException {
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


    public List<Leider> getAllLeiding() throws SQLException {
        ResultSet rs = search("SELECT * FROM leiding");
        ArrayList<Leider> output = new ArrayList<>();
        while(rs.next()){
            Leider newLeider = new Leider(rs.getString(1),
                                          rs.getString(2),
                                          Integer.parseInt(rs.getString(3)));
            output.add(newLeider);
        }
        reestablishConnection();
        return output;
    }

    public List<Drank> getAllDrank(){
        ResultSet rs = search("SELECT * FROM drank");
        ArrayList<Drank> output = new ArrayList<>();



        return output;
    }
}
