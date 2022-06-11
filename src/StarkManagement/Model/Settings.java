package StarkManagement.Model;

import java.io.Serializable;

public class Settings implements Serializable {

    private int port;
    private String ip;

    public Settings(String newIp, int newPort){
        port = newPort;
        ip = newIp;
    }

    public void setIp(String newIp) {
        ip = newIp;
    }

    public void setPort(int newPort) {
        port = newPort;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }
}
