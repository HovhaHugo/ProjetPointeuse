package StarkManagement.View;

import StarkManagement.Model.Company;
import StarkManagement.Model.Employee;
import StarkManagement.Model.FileManipulator;
import StarkManagement.TCPCommunication.TCPServerMain;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame{

    private Company company;

    TCPServerMain tcpServerMain;


    public MainWindow()
    {

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
        //Ajouter les onglets au frame
        add(tabbedPane);
        pack();
        //setSize(width,height);
        setVisible(true);
    }

    public void close(){
        FileManipulator.exportCompany(company);
        tcpServerMain.shutdown();
        dispose();
    }




}
