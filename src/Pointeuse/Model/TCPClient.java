package Pointeuse.Model;

import Pointeuse.Controller.ScoreShort;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClient implements Runnable{

    Socket socket;
    ArrayList<ScoreShort> listToSend;
    Settings settings;

    public TCPClient(ArrayList<ScoreShort> pList, Settings pSettings){
        listToSend = pList;
        settings = pSettings;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(settings.getIpMainApplication(), settings.getPortMainAppplication());
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            //If data are wrote in the object between writeObject and clear, they will be lost
            //So we create a temp list in an other ArrayList
            ArrayList<ScoreShort> listTempToSend = new ArrayList<>(listToSend);

            boolean success = true;
            try{
                oos.writeObject(listTempToSend);
            } catch (IOException e) {
                success = false;
            }
            if(success)
                listToSend.removeAll(listTempToSend);

            oos.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
