package StarkManagement.Model;


import Common.Hours;

import java.io.Serializable;
import java.util.HashMap;

public class Planning implements Serializable {
    private HashMap<Days, Hourly> planning;

    public Planning() {
        planning = new HashMap<>();
        Hours hs1 = new Hours(8,0);
        Hours he1 = new Hours(16,0);
        planning.put(Days.Monday,new Hourly(hs1, he1));

        Hours hs2 = new Hours(8,0);
        Hours he2 = new Hours(16,0);
        planning.put(Days.Tuesday,new Hourly(hs2, he2));

        Hours hs3 = new Hours(8,0);
        Hours he3 = new Hours(16,0);
        planning.put(Days.Wednesday,new Hourly(hs3, he3));

        Hours hs4 = new Hours(8,0);
        Hours he4 = new Hours(16,0);
        planning.put(Days.Thursday,new Hourly(hs4, he4));

        Hours hs5 = new Hours(8,0);
        Hours he5 = new Hours(16,0);
        planning.put(Days.Friday,new Hourly(hs5, he5));
    }
    public Planning(HashMap<Days, Hourly> planning) {
        this.planning = planning;
    }

    public HashMap<Days, Hourly> getPlanning() {
        return planning;
    }

    public void setPlanning(HashMap<Days, Hourly> planning) {
        this.planning = planning;
    }

    public void setHourlyToDay(Days day, Hourly hourly){
        this.getPlanning().replace(day,hourly);
    }
}
