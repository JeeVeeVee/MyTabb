package JDBC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bestelling {
    private Leider leider;
    private HashMap<Drank, Integer> map;

    public Bestelling(Leider leider, List<Drank> allDrank){
        this.leider = leider;
        this.map = new HashMap<>();
        for (Drank drank : allDrank) {
            map.put(drank, 0);
        }
    }

    public void addDrank(Drank drank){
        Integer oldValue = map.get(drank);
        map.put(drank, oldValue++);
    }

    public Leider getLeider() {
        return leider;
    }

    public HashMap<Drank, Integer> getMap() {
        return map;
    }
}
