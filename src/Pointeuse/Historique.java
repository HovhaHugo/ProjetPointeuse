package Pointeuse;

import java.util.ArrayList;

public class Historique {

    String personne;
    String heure;

    static ArrayList<Historique> liste = new ArrayList<>();

    public Historique(String p, String h){

        personne = p;
        heure = p;

        calculHeureSup(this);

        liste.add(this);
    }

    private void calculHeureSup(Historique test){

        boolean trouve = false;

        for(Historique histo : liste){

            if(histo.personne.equals(test.personne)){
                if(trouve==true){
                    //Erreur, il y a déjà 2 pointages dans la liste
                }
                trouve = true;
            }
        }

        if(trouve){
            //Calcul heure sup selon heure sortie
        }else{
            //Calcul heure sup selon heure arrivee
        }

    }

    public ArrayList<Historique> getListeParPersonne(String p){

        ArrayList<Historique> listesortie = new ArrayList<>();

        for(Historique histo : liste) {
            if(histo.personne.equals(p))
                listesortie.add(histo);
        }

        return listesortie;

    }

}
