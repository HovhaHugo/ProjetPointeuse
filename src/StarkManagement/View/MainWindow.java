package StarkManagement.View;

import Common.FileManipulatorCommon;
import StarkManagement.Model.*;
import StarkManagement.TCPCommunication.TCPClientMain;
import StarkManagement.TCPCommunication.TCPServerMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class to create the main window of the application
 */
public class MainWindow extends JFrame{

    private Company company;

    static TCPServerMain tcpServerMain;
    static TCPClientMain tcpClientMain;

    static Settings settings;

    ScorePanel scorePanel;
    EmployeePanel employeePanel;
    /**
     * Constructor of the window
     */
    public MainWindow() {

        setTitle("Stark Management");
        setIconImage(FileManipulatorCommon.getImage("stark.png"));

        getContentPane().setBackground(Color.DARK_GRAY);

        settings = FileManipulator.importMainAppSetting();
        company = FileManipulator.importCompany();
        Employee.setEmployeeList(company);
        Score.setScoreList(company);

        new Thread(tcpServerMain = new TCPServerMain(this)).start();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                close();
            }
        });

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.GRAY);

        employeePanel = new EmployeePanel(company);
        scorePanel = new ScorePanel();
        SettingPanel settingPanel = new SettingPanel(settings);

        tabbedPane.add("Check", scorePanel);
        tabbedPane.add("Employees", employeePanel);
        tabbedPane.add("Parameter", settingPanel);
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

    public void forceUpdate(){
        scorePanel.loadJTable();
    }

    public void forceSave(){
        FileManipulator.exportCompany(company);
    }




}
