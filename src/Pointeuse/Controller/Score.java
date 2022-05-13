package Pointeuse.Controller;

import java.util.ArrayList;

public class Score {

    PersonnShort personne;
    Hours heure;

    static ArrayList<Score> ScoreList = new ArrayList<>();

    public Score(PersonnShort p, Hours h){
        personne = p;
        heure = h;

        //Envoie TCP
        //Si timeOut, enregistrement local
        ScoreList.add(this);
    }

}
