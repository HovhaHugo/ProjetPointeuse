package StarkManagement.Model;

import Common.Hours;

import java.io.Serializable;

public class Hourly implements Serializable {
    private Hours startTime;
    private Hours endTime;

    public Hourly() {
        startTime = null;
        endTime = null;
    }
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
