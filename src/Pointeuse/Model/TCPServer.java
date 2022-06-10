package Pointeuse.Model;

import Pointeuse.Controller.PersonnShort;
import Pointeuse.Controller.ScoreShort;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer implements Runnable{

    private final int listeningPort = 8080;

    private ServerSocket serverSocket;
    Socket socket;

    private boolean running = true;

    /**
     * Constructor of the server of the check application
     * Listening on 'listeningPort'
     * Valid data are only ArrayList<PersonnShort> class
     */
    public TCPServer(){
    }

    @Override
    public void run() {

        try {

            serverSocket = new ServerSocket(listeningPort);


            while(running){
                socket = serverSocket.accept();

                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);

                new Thread(new threadAcceptConnection(ois)).start();
            }

            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void shutdown(){
        running = false;
    }

    private class threadAcceptConnection implements Runnable{

        ObjectInputStream ois;

        public threadAcceptConnection(ObjectInputStream pObjInputStream){
            ois = pObjInputStream;
        }

        @Override
        public void run() {
            try {
                ArrayList<PersonnShort> listeReceived =(ArrayList<PersonnShort>) ois.readObject();
                PersonnShort.setPersonnShortList(listeReceived);

                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
