package StarkManagement.UDPCommunication;

import StarkManagement.Model.Parametre;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPServerBuilder extends UDPRWText {
    InetSocketAddress isA; // the address
    DatagramSocket s; // the socket object
    DatagramPacket req, rep; // to prepare the request and reply messages
    final int size = 2048; // the default size for the buffer array

    int port;
    int timeOut = 30000;
    String ip;

    UDPServerBuilder(int port, String ip) {
        isA = null;
        s = null;
        req = rep = null;
        this.port = port;
        this.ip = ip;
    }

    public void setConnection() throws SocketException {
        isA = new InetSocketAddress(ip, port);
        s = new DatagramSocket(isA.getPort()); //Le port pour recevoir est defini
        s.setSoTimeout(timeOut);
        System.out.println("Serveur initialisé");
    }
}
