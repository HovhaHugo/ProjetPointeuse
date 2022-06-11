package StarkManagement.TCPCommunication;

import Common.ScoreShort;
import StarkManagement.Model.Score;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerMain implements Runnable{

    Socket socket;
    final int listeningPort = 8081;

    private boolean running = true;

    /**
     * Constructor of the server of the main application
     * Listening on 'listeningPort'
     * Valid data are only ArrayList<ScoreShort> class
     */
    public TCPServerMain(){
    }

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(listeningPort);
            serverSocket.setSoTimeout(0);

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

    /**
     * Shutdown the server
     */
    public void shutdown(){
        running = false;
    }

    /**
     * Parallel thread to read the data sent
     */
    private static class threadAcceptConnection implements Runnable{

        ObjectInputStream ois;

        public threadAcceptConnection(ObjectInputStream pObjInputStream){
            ois = pObjInputStream;
        }

        @Override
        public void run() {
            ArrayList<ScoreShort> liste;
            try {
                liste =(ArrayList<ScoreShort>) ois.readObject();
                for(ScoreShort o : liste)
                    new Score(o);
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}