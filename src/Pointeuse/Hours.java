package Pointeuse;

public class Hours {

    private int hours;
    private int minutes;

    public Hours(){

    }

    public void passTime(int hours, int minutes){
        passTime(minutes+60*hours);
    }

    public void passTime(int minutes){

    }

    public static Hours roundNextQuarter(Hours toRound){
        Hours toReturn = new Hours();


        return toReturn;
    }

    public static Hours timeBetween(Hours early, Hours later){
        Hours toReturn = new Hours();

        return toReturn;
    }

    @Override
    public String toString(){
        return hours+":"+minutes;
    }
}
