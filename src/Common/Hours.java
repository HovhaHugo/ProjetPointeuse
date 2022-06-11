package Common;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Hours implements Serializable {

    private int hours;
    private int minutes;
    LocalDate date;

    /**
     * Constructor of Hours, initialise hours and minutes with LocalDatTime.now() values
     */
    public Hours() {
        LocalDateTime now = LocalDateTime.now();
        hours = now.getHour();
        minutes = now.getMinute();
        date = now.toLocalDate();
    }

    /**
     * Copy constructor of Hours
     *
     * @param toCopy the object to duplicate
     */
    public Hours(Hours toCopy) {
        hours = toCopy.hours;
        minutes = toCopy.minutes;
        date = toCopy.date;
    }

    /**
     * Update the hours and minutes variables with LocalDateTime.now()
     */
    public void update() {
        LocalDateTime now = LocalDateTime.now();
        hours = now.getHour();
        minutes = now.getMinute();
        date = now.toLocalDate();
    }

    /**
     * Round the current time to the nearest fifteen minutes
     */
    public void roundNextQuarter() {

        while (minutes % 15 != 0) {
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
                if (hours == 24)
                    hours = 0;
            }
        }
    }

    public int minutesBetween(Hours scheduleHours) {
        return (scheduleHours.hours - hours) * 60 + (scheduleHours.minutes - minutes);
    }

    @Override
    public String toString() {

        String hourString = "";
        String minuteString = "";

        if (minutes < 10) {
            minuteString += "0";
        }
        minuteString += minutes;

        if (hours < 10) {
            hourString += "0";
        }
        hourString += hours;

        return hourString + ":" + minuteString;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
    public LocalDate getDate(){ return date;}
}