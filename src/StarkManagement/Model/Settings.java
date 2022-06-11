package StarkManagement.Model;

import java.io.Serializable;

public class Settings implements Serializable {

    private int port;
    private String ip;
    /**
     * Constructor of Settings, initialise Settings with
     *@param newIp the ip
     *@param newPort the ip
     */
    public Settings(String newIp, int newPort){
        port = newPort;
        ip = newIp;
    }
    /**
     * Setter of Ip, change the Ip
     *@param newIp the ip
     */
    public void setIp(String newIp) {
        ip = newIp;
    }
    /**
     * Setter of Port, change the port
     *@param newPort the port
     */
    public void setPort(int newPort) {
        port = newPort;
    }
    /**
     * getter of Ip
     * @return the Ip
     */
    public String getIp() {
        return ip;
    }
    /**
     * getter of Port
     * @return the port
     */
    public int getPort() {
        return port;
    }
}
