package Pointeuse.Model;

import Pointeuse.Controller.PersonnShort;

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

    public TCPServer(){
    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(listeningPort);

            InputStream is = socket.getInputStream();

            ObjectInputStream ois = new ObjectInputStream(is);

            while(running){
                socket = serverSocket.accept();
                threadAcceptConnection acceptConnection = new threadAcceptConnection(ois);
                acceptConnection.run();
            }

            is.close();
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
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
