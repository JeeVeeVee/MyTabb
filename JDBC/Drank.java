package JDBC;

/*
klasse die een drank voorstelt, die wordt uitgelezen uit de db
 */

public class Drank {
    private String naam;
    private double prijs;
    private int voorraad;

    public Drank(String naam,double prijs, int voorraad) {
        this.naam = naam;
        this.prijs = prijs;
        this.voorraad = voorraad;
    }

    public double getPrijs(){
        return this.prijs;
    }

    public int getVoorraad(){
        return this.voorraad;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public int hashCode() {
        return naam.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        try{
            Drank otherOne = (Drank) obj;
            return this.getNaam() == otherOne.getNaam();
        } catch (Exception e){
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    @Override
    public String toString() {
        return naam;
    }
}
