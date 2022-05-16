package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;

public class UDPServiceTimeOut extends UDPServerBuilder implements Runnable {

    UDPServiceTimeOut(int port, String ip) {
        super(port, ip);
    }

    public void run() {
        try {
            s.setSoTimeout(timeOut);

            rep = new DatagramPacket(new byte[size], size);
            //On envoie rien
            //s.receive(rep);

            s.close();
            System.out.println("Serveur fermé");
        } catch (IOException e) {
            System.out.println("IOException UDPServerTimeout");
        }
    }
}
