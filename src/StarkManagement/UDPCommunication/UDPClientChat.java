package StarkManagement.UDPCommunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Scanner;

public class UDPClientChat extends UDPClientBuilder implements Runnable {

    private final Scanner sc;

    public UDPClientChat(int port, String ip) {
        super(port, ip);
        sc = new Scanner(System.in);
    }

    @Override
    public void run() {

        System.out.println("Lancement du chat pour le client");

        try {
            while (true) {
                String toSend = sc.nextLine();
                DatagramPacket req = getTextSendingPacket(isA, toSend);
                s.send(req);
                if (toSend.equals("/quit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
        s.close();

    }
}

