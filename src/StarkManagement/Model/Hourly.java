package StarkManagement.Model;

import Common.Hours;

import java.io.Serializable;

/**
 * Class to represent a day of work inside the schedule
 */
public class Hourly implements Serializable {
    private Hours startTime;
    private Hours endTime;

    public Hourly() {
        startTime = null;
        endTime = null;
    }

    /**
     * Constructor of Hourly
     * @param startTime
     * @param endTime
     */
    public Hourly(Hours startTime, Hours endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Hours getStartTime() {
        return startTime;
    }

    public void setStartTime(Hours startTime) {
        this.startTime = startTime;
    }

    public Hours getEndTime() {
        return endTime;
    }

    public void setEndTime(Hours endTime) {
        this.endTime = endTime;
    }
}
