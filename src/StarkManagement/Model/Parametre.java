package StarkManagement.Model;

public class Parametre {

    private int port;
    private String adresseIp;

    public void setAdresseIp(String newAdresseIp){
        adresseIp = newAdresseIp ;
    }

    public void setPort(int newPort){
        port = newPort ;
    }

    public String getAdresseIp(){
        return adresseIp;
    }

    public int getPort() { return port; }
}
