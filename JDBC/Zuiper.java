package JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Zuiper extends DatabaseCommunicator{
    public Zuiper(){
        super();
    }

    public void zuip(Leider leider, Drank drank){
        updateVoorraad(drank);
        updateSchuld(leider, drank);
    }

    public double calcNewSchuld(Leider leider,Drank drank){
        System.out.println(leider.getSchuld() + drank.getPrijs());
        return leider.getSchuld() + drank.getPrijs();
    }

    public void updateVoorraad(Drank drank){
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE drank SET voorraad = ? WHERE naam = ?"
        )){
            ps.setString(1, String.valueOf(drank.getVoorraad() - 1));
            ps.setString(2, drank.getNaam());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSchuld(Leider leider, Drank drank){
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE leiding SET schuld = ? WHERE first = ? AND last = ?"
        )) {
            ps.setString(1, String.valueOf(calcNewSchuld(leider, drank)));
            ps.setString(2, leider.getFirst());
            ps.setString(3, leider.getLast());
            ps.executeUpdate();
        } catch (SQLException e){
            System.out.println("some went wrong");
        }
    };
}
