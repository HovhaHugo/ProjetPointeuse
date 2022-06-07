package Pointeuse.Controller;

import java.time.LocalDateTime;

public class Hours {

    private int hours;
    private int minutes;

    /**
     * Constructor of Hours, initialise hours and minutes with LocalDatTime.now() values
     */
    public Hours(){
        LocalDateTime now = LocalDateTime.now();
        hours = now.getHour();
        minutes = now.getMinute();
    }

    /**
     * Copy constructor of Hours
     * @param toCopy the object to duplicate
     */
    public Hours(Hours toCopy){
        hours = toCopy.hours;
        minutes = toCopy.minutes;
    }

    /**
     * Update the hours and minutes variables with LocalDateTime.now()
     */
    public void update(){
        LocalDateTime now = LocalDateTime.now();
        hours = now.getHour();
        minutes = now.getMinute();
    }

    /**
     * Round the current time to the nearest fifteen minutes
     */
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

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
