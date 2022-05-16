package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;

public class UDPCLientTimeOut extends UDPClientBuilder implements Runnable{
    UDPCLientTimeOut(int port, String ip) {
        super(port, ip);
    }

    public void run() {
        try {

            rep = new DatagramPacket(new byte[size], size);
            s.setSoTimeout(timeOut);
            s.receive(rep);

            s.close();
        } catch (IOException e) {
            System.out.println("IOException UDPClientTimeout");
            s.close();
        }
    }

}
