package Pointeuse.Model;

import java.io.Serializable;

public class Settings implements Serializable {

    private String ip;
    private int port;

    /**
     * Constructor of Settings, stores ip and port of the StarkManagement application
     * @param ip
     * @param port
     */
    public Settings(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
