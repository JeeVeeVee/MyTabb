package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Zuiper extends DatabaseCommunicator{
    public Zuiper(){
        super();
    }

    public void zuip(Leider leider, Drank drank){
        try{
            PreparedStatement ps = connection.prepareStatement("" +
                    "UPDATE leiding"
                    + "SET schul = " + Double.toString(calcNewSchuld(leider, drank))
                    + "WHERE first ILIKE " + leider.getFirst()
                    + "AND last ILIKE " + leider.getLast()
            );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calcNewSchuld(Leider leider,Drank drank){
        return leider.getSchuld() + drank.getPrijs();
    }
}
