package Pointeuse.Controller;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Light version of a Score. The full version is only used in the application StarkManagement
 */
public class ScoreShort {

    private PersonnShort personne;
    private Hours heure;
    private static boolean testSendThisHour = false;

    private static ArrayList<ScoreShort> ScoreList = new ArrayList<>();

    /**
     * Constructor of ScoreShort
     * @param pPerson the employee who checked
     * @param pHours the hours of the checking
     */
    public ScoreShort(PersonnShort pPerson, Hours pHours){
        personne = pPerson;
        heure = pHours;

        //Envoie TCP
        //Si timeOut, enregistrement local
        ScoreList.add(this);
    }

    /**
     * Send with TCP to the server all the temporary ScoreShort stocked
     * Must be called regularly to empty the cache filled
     * with previous send failures
     */
    public static void sendAllTemp(){

        ArrayList<ScoreShort> toRemove = new ArrayList<>();

        for(ScoreShort ss : ScoreList){

            boolean success = true;
            /*try{

                //Utiliser UDPClient pour envoyer les données

            }catch(IOException e){
                success = false;
            }

            if(success==true){
                toRemove.add(ss);
            }*/

        }

        ScoreList.removeAll(toRemove);

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

    public static void setTestSendThisHour(boolean t) {
        testSendThisHour = t;
    }

    public static boolean isTestSendThisHour() {
        return testSendThisHour;
    }
}
