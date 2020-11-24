package JDBC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
deze klasse houd de bestelling bij, wordt gebruikt in de map zuip_gedeelte
 */

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
        Integer newValue = map.get(drank) + 1;
        map.put(drank, newValue);
        System.out.println("updated " + drank.getNaam());
        //map.keySet().stream().forEach(e -> System.out.println(e.getNaam() + " : " + map.get(e).toString()));
    }

    public Leider getLeider() {
        return leider;
    }

    public HashMap<Drank, Integer> getMap() {
        return map;
    }

    @Override
    public String toString() {
        String output = "";
        output += leider.getFirst() + "\n";
        for (Drank drank : map.keySet()){
            output += drank.getNaam() + " : " + map.get(drank) + "\n";
        }
        return output;
    }
}
