package JDBC;

import errorHandling.MyError;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCommunicator {

    protected Connection connection;

    public DatabaseCommunicator(Connection connection){
        this.connection = connection;
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
            System.out.println("Couldn't update schuld");
        }
    }

    public Leider getUpdatedLeider(Leider leider) throws SQLException {
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
            System.out.println("Could not return updated leider");
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
            System.out.println("Could not return updated drank");
            return drank;
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
            System.out.printf("could not execute following query: " + sqlStatement);
            return null;
        }
    }

    public List<Leider> getAllLeiding() throws SQLException {
        ResultSet rs = search("SELECT * FROM leiding");
        ArrayList<Leider> output = new ArrayList<>();
        while(rs.next()){
            Leider newLeider = new Leider(rs.getString(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    rs.getInt(4));
            output.add(newLeider);
        }
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

    public void vulBij(int aantalBakken, Drank drank){
        try(PreparedStatement ps = connection.prepareStatement(
                "UPDATE drank SET voorraad = ? WHERE naam = ?"
        )){
            ps.setString(1, String.valueOf(drank.getVoorraad() + aantalBakken * 24));
            ps.setString(2, drank.getNaam());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong, " + drank.getNaam() + "was not refilled");
            e.printStackTrace();
        }
    }
}
