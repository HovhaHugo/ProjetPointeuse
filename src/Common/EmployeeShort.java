package Common;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeShort implements Serializable {

    String identity;
    int id;

    private static ArrayList<EmployeeShort> employeeShortList = new ArrayList<>();

    public EmployeeShort(String pIdentity, int pId){
        identity =pIdentity;
        id = pId;
        employeeShortList.add(this);
    }

    public static void clearList(){
        employeeShortList.clear();
    }

    public static EmployeeShort getPersonn(String pIdentity){

        for(EmployeeShort p : employeeShortList){
            if(p.identity.equals(pIdentity))
                return p;
        }

        return null;
    }

    public static EmployeeShort getPersonn(int pId){

        for(EmployeeShort p : employeeShortList){
            if(p.id == pId)
                return p;
        }

        return null;
    }

    public static void setPersonnShortList(ArrayList<EmployeeShort> pList){
        employeeShortList = pList;
    }

    public static ArrayList<String> getPersonnListString(){
        ArrayList<String> list = new ArrayList<>();

        for(EmployeeShort p : employeeShortList)
            list.add(p.toString());

        return list;
    }

    @Override
    public String toString(){
        return id+" - "+identity;
    }

    public int getId(){return id;}
}
