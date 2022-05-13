package Pointeuse.Controller;

import java.time.LocalDateTime;

public class Hours {

    private int hours;
    private int minutes;

    public Hours(){
        LocalDateTime now = LocalDateTime.now();
        hours = now.getHour();
        minutes = now.getMinute();
    }

    public Hours(Hours toCopy){
        hours = toCopy.hours;
        minutes = toCopy.minutes;
    }

    public void update(){
        LocalDateTime now = LocalDateTime.now();
        hours = now.getHour();
        minutes = now.getMinute();
    }

    public void passTime(int hours, int minutes){
        passTime(minutes+60*hours);
    }

    public void passTime(int minutes){

    }

    public void roundNextQuarter(){
        Hours toReturn = new Hours();

        while(minutes%15!=0){
            minutes++;
            if(minutes==60){
                minutes = 0;
                hours++;
                if(hours==24)
                    hours=0;
            }
        }

    }

    public static Hours timeBetween(Hours early, Hours later){
        Hours toReturn = new Hours();

        return toReturn;
    }

    @Override
    public String toString(){

        String hourString="";
        String minuteString="";

        if(minutes<10){
            minuteString+="0";
        }
        minuteString +=minutes;

        if(hours<10){
            hourString+="0";
        }
        hourString +=hours;

        return hourString+":"+minuteString;
    }
}
