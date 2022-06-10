package Pointeuse.Controller;

import java.util.ArrayList;

public class PersonnShortCheck {

    String identity;
    int id;

    private static ArrayList<PersonnShortCheck> personnShortList = new ArrayList<>();

    public PersonnShortCheck(String pIdentity, int pId){
        identity =pIdentity;
        id = pId;
        personnShortList.add(this);
    }

    public static void clearList(){
        personnShortList.clear();
    }

    public static PersonnShortCheck getPersonn(String pIdentity){

        for(PersonnShortCheck p : personnShortList){
            if(p.identity.equals(pIdentity))
                return p;
        }

        return null;
    }

    public static PersonnShortCheck getPersonn(int pId){

        for(PersonnShortCheck p : personnShortList){
            if(p.id == pId)
                return p;
        }

        return null;
    }

    public static void setPersonnShortList(ArrayList<PersonnShortCheck> pList){
        personnShortList = pList;
    }

    public static ArrayList<String> getPersonnListString(){
        ArrayList<String> list = new ArrayList<>();

        for(PersonnShortCheck p : personnShortList)
            list.add(p.toString());

        return list;
    }

    @Override
    public String toString(){
        return "-"+id+" "+identity;
    }


}
