package JDBC;

public class Drank {
    private int voorraad;
    private int prijs;

    public Drank(int voorraad, int prijs) {
        this.prijs = prijs;
        this.voorraad = voorraad;
    }

    public int getPrijs(){
        return this.prijs;
    }

    public int getVoorraad(){
        return this.voorraad;
    }

    public void consume(){
        this.voorraad--;
    }
}
