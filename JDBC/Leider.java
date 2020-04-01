package JDBC;

import java.sql.DriverManager;

public class Leider {
    private String first;
    private String last;
    private int schuld;

    public Leider(String first, String last, int schuld){
        this.first = first;
        this.last = last;
        this.schuld = schuld;
    }

    public void consume(Drank drank){
        schuld += drank.getPrijs();
    }

    public int getSchuld() {
        return schuld;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
}
