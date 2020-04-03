package JDBC;

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

    public void consume(){
        this.voorraad--;
    }

    @Override
    public String toString() {
        return naam;
    }
}
