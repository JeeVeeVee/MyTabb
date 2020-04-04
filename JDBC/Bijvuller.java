package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bijvuller extends DatabaseCommunicator {
    public Bijvuller(Connection connection) {
        super(connection);
    }

    public void vulBij(Drank drank, int aantalBakken){
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE drank SET voorraad = ? WHERE naam = ?"
        )){
            ps.setString(1, String.valueOf(drank.getVoorraad() + aantalBakken * 24));
            ps.setString(2, drank.getNaam());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
