package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;

public class UDPServiceHello extends UDPServerBuilder implements Runnable{
    public void run() {
        try {
            setConnection();
            req = new DatagramPacket(new byte[size],size);
            s.receive(req);
            System.out.println("request received");
            rep = new DatagramPacket(new byte[size],0,size,req.getSocketAddress());
            s.send(rep);
            System.out.println("reply sent");
            s.close();
        }
        catch(IOException e) {
            System.out.println("IOException UDPServer");
        }
    }
}
