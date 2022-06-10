package StarkManagement.TCPCommunication;

import StarkManagement.Model.Employee;
import StarkManagement.Model.Setting;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClientMain implements Runnable {

    Socket socket;
    ArrayList<Employee> listToSend;
    Setting settings;

    public TCPClientMain(ArrayList<Employee> pList, Setting pSettings){
        listToSend = pList;
        settings = pSettings;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(settings.getIp(), settings.getPort());
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            boolean success = true;
            try{
                oos.writeObject(listToSend);
            } catch (IOException e) {
                success = false;
            }
            if(success)
                listToSend.clear();

            oos.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

