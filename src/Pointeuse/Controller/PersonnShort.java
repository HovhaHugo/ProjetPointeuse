package Pointeuse.Controller;

import java.util.ArrayList;

public class PersonnShort {

    String identity;
    int id;

    private static ArrayList<PersonnShort> personnShortList = new ArrayList<>();

    public PersonnShort(String pIdentity, int pId){
        identity =pIdentity;
        id = pId;
        personnShortList.add(this);
    }

    public static void clearList(){
        personnShortList.clear();
    }

    public static PersonnShort getPersonn(String pIdentity){

        for(PersonnShort p : personnShortList){
            if(p.identity.equals(pIdentity))
                return p;
        }

        return null;
    }

    public static PersonnShort getPersonn(int pId){

        for(PersonnShort p : personnShortList){
            if(p.id == pId)
                return p;
        }

        return null;
    }


}
