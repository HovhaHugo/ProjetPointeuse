package StarkManagement.UDPCommunication;

import java.io.IOException;

public class UDPServiceInfo extends UDPServerBuilder implements Runnable{
    public void run() {
        try  {
            setConnection();
            socketInfo("server sets the connection",s);
            s.close();
            socketInfo("server closes the connection",s);
        }
        catch(IOException e) {
            System.out.println("IOException UDPServerInfo");
        }
    }
}
