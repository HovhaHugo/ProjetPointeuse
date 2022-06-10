package StarkManagement.TCPCommunication;

import StarkManagement.Score;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServerMain implements Runnable{

    Socket socket;

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
            int listeningPort = 8080;
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
            ArrayList<Score> obj1;
            /*try {
                obj1 =(ArrayList<Score>) ois.readObject();
                for(Score o : obj1){
                    System.out.println(o.getHeure());
                }
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }*/

        }
    }
}