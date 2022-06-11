package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Light version of a Score. The full version is only used in the application StarkManagement
 */
public class ScoreShort implements Serializable {

    private EmployeeShort employee;
    private Hours hours;

    private static ArrayList<ScoreShort> ScoreList = new ArrayList<>();

    /**
     * Constructor of ScoreShort
     * @param pPerson the employee who checked
     * @param pHours the hours of the checking
     */
    public ScoreShort(EmployeeShort pPerson, Hours pHours){
        employee = pPerson;
        hours = pHours;

        ScoreList.add(this);
    }

    /**
     * Check if the current day is valid for a work day
     * @return false if it's saturday or sunday, else true
     */
    public static boolean isDayValid(){
        //int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        //if(day==1 || day == 7)
       //     return false;
        return true;
    }

    public static ArrayList<ScoreShort> getScoreList() {
        return ScoreList;
    }

    public Hours getHours(){
        return this.hours;
    }


    public int getEmployeeId() {
        return employee.getId();
    }
}
