package JDBC;

import java.sql.DriverManager;

public class Leider {
    private String first;
    private String last;
    private double schuld;

    public Leider(String first, String last, double schuld){
        this.first = first;
        this.last = last;
        this.schuld = schuld;
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

    @Override
    public String toString() {
        return "Leider{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", schuld=" + schuld +
                '}';
    }
}
