package StarkManagement.Model;

public class Parametre {

    String port;
    String adresseIp;

    void setAdresseIp(String newAdresseIp){
        adresseIp = newAdresseIp ;
    }

    void setPort(String newPort){
        port = newPort ;
    }

    String getAdresseIp(){
        return adresseIp;
    }

    String getPort(){
        return port;
    }


}
