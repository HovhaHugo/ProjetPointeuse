package Pointeuse.Controller;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Light version of a Score. The full version is only used in the application StarkManagement
 */
public class ScoreShort {

    private PersonnShort personne;
    private Hours heure;

    private static ArrayList<ScoreShort> ScoreList = new ArrayList<>();

    /**
     * Constructor of ScoreShort
     * @param pPerson the employee who checked
     * @param pHours the hours of the checking
     */
    public ScoreShort(PersonnShort pPerson, Hours pHours){
        personne = pPerson;
        heure = pHours;

        ScoreList.add(this);
    }

    /**
     * Check if the current day is valid for a work day
     * @return false if it's saturday or sunday, else true
     */
    public static boolean isDayValid(){
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if(day==1 || day == 7)
            return false;
        return true;
    }

    public static ArrayList<ScoreShort> getScoreList() {
        return ScoreList;
    }


}
