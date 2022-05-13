package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;

public class UDPClientHello  extends UDPClientBuilder implements Runnable {

    public void run() {
        try {
            setConnection();
            req = new DatagramPacket(new byte[size],0,size,isA.getAddress(),isA.getPort());
            s.send(req);
            System.out.println("request sent");
            rep = new DatagramPacket(new byte[size],size);
            s.receive(rep);
            System.out.println("reply received");
            s.close();
        }
        catch(IOException e) {
            System.out.println("IOException UDPClient");
        }
    }
}
