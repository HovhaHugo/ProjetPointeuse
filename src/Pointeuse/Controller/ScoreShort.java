package Pointeuse.Controller;

import java.io.IOException;
import java.util.ArrayList;

public class ScoreShort {

    private PersonnShort personne;
    private Hours heure;
    private static boolean testSendThisHour = false;

    private static ArrayList<ScoreShort> ScoreList = new ArrayList<>();

    public ScoreShort(PersonnShort p, Hours h){
        personne = p;
        heure = h;

        //Envoie TCP
        //Si timeOut, enregistrement local
        ScoreList.add(this);
    }

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

    public static void setTestSendThisHour(boolean t) {
        testSendThisHour = t;
    }

    public static boolean isTestSendThisHour() {
        return testSendThisHour;
    }
}
