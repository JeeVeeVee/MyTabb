package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Updater extends DatabaseCommunicator{
    public Updater(Connection connection){
        super(connection);
    }
     /*
     *
     * #TODO
     *   implement these methods so they return updated versions of the object they received
     *
     */
    public Leider getuUpdatedLeider(Leider leider) throws SQLException {
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM leiding WHERE first = ? AND last = ?");
            ps.setString(1, leider.getFirst());
            ps.setString(2, leider.getLast());
            ResultSet rs = ps.executeQuery();
            Leider output = new Leider(rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3));
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return leider;
        }
    }

    public Drank getUpdatedDrank(Drank drank){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM drank WHERE naam = ");
            ps.setString(1, drank.getNaam());
            ResultSet rs = ps.executeQuery();
            Drank output = new Drank(rs.getString(1),
                    rs.getDouble(2),
                    rs.getInt(3));
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return drank;
        }
    }
}
