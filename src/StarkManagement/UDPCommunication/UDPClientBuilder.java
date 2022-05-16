package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPClientBuilder extends UDPRWText {

    InetSocketAddress isA;
    DatagramSocket s;
    DatagramPacket req, rep;
    final int size = 2048;
    int timeOut = 30000;

    int port;
    String ip;

    UDPClientBuilder(int port, String ip) {
        isA = null;
        s = null;
        req = rep = null;

        this.ip = ip;
        this.port = port;
    }

    public void setConnection() throws IOException {
        isA = new InetSocketAddress(ip,port);
        s = new DatagramSocket();   //Le port pour envoyer est aleatoire
        s.setSoTimeout(timeOut);
        System.out.println("Client initialisé");
    }
}
