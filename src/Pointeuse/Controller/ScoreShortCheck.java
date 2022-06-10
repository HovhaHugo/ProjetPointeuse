package Pointeuse.Controller;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Light version of a Score. The full version is only used in the application StarkManagement
 */
public class ScoreShortCheck {

    private PersonnShortCheck personne;
    private HoursCheck heure;

    private static ArrayList<ScoreShortCheck> ScoreList = new ArrayList<>();

    /**
     * Constructor of ScoreShort
     * @param pPerson the employee who checked
     * @param pHours the hours of the checking
     */
    public ScoreShortCheck(PersonnShortCheck pPerson, HoursCheck pHours){
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

    public static ArrayList<ScoreShortCheck> getScoreList() {
        return ScoreList;
    }


}
