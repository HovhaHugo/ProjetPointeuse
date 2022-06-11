package StarkManagement.View;

import StarkManagement.Model.Company;
import StarkManagement.Model.Employee;
import StarkManagement.Model.FileManipulator;
import StarkManagement.Model.Settings;
import StarkManagement.TCPCommunication.TCPClientMain;
import StarkManagement.TCPCommunication.TCPServerMain;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame{

    private Company company;

    static TCPServerMain tcpServerMain;
    static TCPClientMain tcpClientMain;

    static Settings settings;

    public MainWindow() {


        /*settings = new Settings("localhost",8080);
        FileManipulator.exportMainAppSetting(settings);*/

        settings = FileManipulator.importMainAppSetting();
        company = FileManipulator.importCompany();
        Employee.setEmployeeList(company);

        new Thread(tcpServerMain = new TCPServerMain()).start();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                close();
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();

        EmployeePanel employeePanel = new EmployeePanel(company);
        ScorePanel scorePanel = new ScorePanel();

        tabbedPane.add("Check",scorePanel);
        tabbedPane.add("Employees", employeePanel);
        add(tabbedPane);
        pack();
        setVisible(true);

        sendEmployeeList();
    }

    /**
     * Send with TCP to the checker the employee list
     */
    public static void sendEmployeeList(){
        new Thread(tcpClientMain = new TCPClientMain(Employee.listEmployee,settings)).start();
    }

    public void close(){
        FileManipulator.exportCompany(company);
        tcpServerMain.shutdown();
        dispose();
    }




}
