package JDBC;

import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Searcher extends DatabaseCommunicator {

    public Searcher(){
        super();
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

    public List<Leider> getAllLeiding() throws SQLException {
        ResultSet rs = search("SELECT * FROM leiding");
        ArrayList<Leider> output = new ArrayList<>();
        while(rs.next()){
            Leider newLeider = new Leider(rs.getString(1),
                                          rs.getString(2),
                                          0);
                                          //Integer.parseInt(rs.getString(3).replace('.', ',')));
            output.add(newLeider);
        }
        reestablishConnection();
        return output;
    }

    public List<Drank> getAllDrank() throws SQLException {
        ResultSet rs = search("SELECT * FROM drank");
        ArrayList<Drank> output = new ArrayList<>();
        while(rs.next()){
            Drank newDrinksj = new Drank(rs.getString(1),
                                         Double.parseDouble(rs.getString(3)),
                                         Integer.parseInt(rs.getString(2)));
            output.add(newDrinksj);
        }
        return output;
    }
}
