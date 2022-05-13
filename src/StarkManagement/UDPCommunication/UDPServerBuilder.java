package StarkManagement.UDPCommunication;

import StarkManagement.Model.Parametre;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPServerBuilder extends UDPInfo{
    InetSocketAddress isA;
    DatagramSocket s;
    DatagramPacket req, rep;
    final int size = 2048;

    UDPServerBuilder(){
        isA = null; s = null; req = rep = null;
    }

    protected void setConnection() throws IOException {
        isA = new InetSocketAddress("192.168.43.239",8082);
        s = new DatagramSocket(isA.getPort());
        /** we can include more setting, later … */
    }

    protected void setConnection(Parametre param) throws IOException {
        isA = new InetSocketAddress(param.getAdresseIp(),param.getPort());
        s = new DatagramSocket(isA.getPort());
        /** we can include more setting, later … */
    }
}