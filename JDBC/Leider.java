package JDBC;

public class Leider {
    private String first;
    private String last;
    private double schuld;
    private int hash;

    public Leider(String first, String last, double schuld){
        this.first = first;
        this.last = last;
        this.schuld = schuld;
        hash = 0;
    }

    public Leider(String first, String last, double schuld, int hash){
        this.first = first;
        this.last = last;
        this.schuld = schuld;
        this.hash = hash;
    }


    public void consume(Drank drank){
        schuld += drank.getPrijs();
    }

    public double getSchuld() {
        return schuld;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public int getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Leider{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", schuld=" + schuld +
                '}';
    }
}
