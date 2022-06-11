package StarkManagement.TCPCommunication;

import Common.EmployeeShort;
import StarkManagement.Model.Employee;
import StarkManagement.Model.Settings;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class TCPClientMain implements Runnable {

    Socket socket;
    ArrayList<EmployeeShort> listToSend;
    Settings settings;

    public TCPClientMain(ArrayList<Employee> pList, Settings pSettings){
        listToSend = new ArrayList<>();
        for(Employee e : pList){
            listToSend.add(new EmployeeShort(e.getSurnameEmployee()+" "+e.getNameEmployee(), e.getIdentifiant()));
        }
        settings = pSettings;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(settings.getIp(), settings.getPort());
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            try{
                oos.writeObject(listToSend);
            } catch (IOException e) {

            }

            oos.close();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

