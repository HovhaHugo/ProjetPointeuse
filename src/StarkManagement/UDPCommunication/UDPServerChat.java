package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;

public class UDPServerChat extends UDPServerBuilder implements Runnable{

    String msg;

    public UDPServerChat(int port, String ip) {
        super(port, ip);
    }

    @Override
    public void run() {

        try{
            while(true){
                req = new DatagramPacket(new byte[size],size);
                s.receive(req);
                msg = getMsg(req);
                if(msg.equals("/quit"))
                    break;
                System.out.println("Message entrant:\n"+msg);
            }
        }catch (SocketTimeoutException e) {
            System.out.println("Time out");
        }catch(IOException e){
            System.out.println("Erreur inconnue");
        }

        s.close();

    }
}
