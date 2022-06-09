package Pointeuse.Model;

import java.io.Serializable;

public class Settings implements Serializable {

    private String ipMainApplication;
    private int portMainAppplication;

    /**
     * Constructor of Settings, stores ip and port of the StarkManagement application
     * @param ip
     * @param port
     */
    public Settings(String ip, int port){
        this.ipMainApplication = ip;
        this.portMainAppplication = port;
    }

    public String getIpMainApplication() {
        return ipMainApplication;
    }

    public int getPortMainAppplication() {
        return portMainAppplication;
    }

    public void setIpMainApplication(String ipMainApplication) {
        this.ipMainApplication = ipMainApplication;
    }

    public void setPortMainAppplication(int portMainAppplication) {
        this.portMainAppplication = portMainAppplication;
    }
}
