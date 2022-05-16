package StarkManagement;

import StarkManagement.Model.Parametre;
import StarkManagement.UDPCommunication.UDPClientChat;
import StarkManagement.UDPCommunication.UDPServerChat;

import java.io.IOException;

public class ParametreControler {
    static Parametre param = new Parametre("localhost", 8000);

    public static void main (String[] args) {

        lancerServeur();

        lancerClient();

    }

    public static void lancerClient(){
        System.out.println("Lancement du client.");

        UDPClientChat client = new UDPClientChat(param.getPort(), param.getAdresseIp());
        try {
            client.setConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(client).start();
    }

    public static void lancerServeur(){
        System.out.println("Lancement du serveur.");

        UDPServerChat ucb = new UDPServerChat(param.getPort(), param.getAdresseIp());
        try {
            ucb.setConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(ucb).start();
    }
}
