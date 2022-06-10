package StarkManagement.View;

import StarkManagement.Controller.CompanyController;
import StarkManagement.Model.Company;
import StarkManagement.Model.Employee;
import StarkManagement.Model.FileManipulator;

import javax.swing.*;

public class MainWindow extends JFrame{
    public static int width =1000;
    public static int height = 600;
    private Company company;
    public MainWindow()
    {

        //CompanyController.exportCompany(company);
        company = FileManipulator.importCompany();
        Employee.setEmployeeList(company);

        JTabbedPane tabbedPane = new JTabbedPane();

        EmployeePanel employeePanel = new EmployeePanel();
        ScorePanel scorePanel = new ScorePanel();
        tabbedPane.add("Employees", employeePanel);
        tabbedPane.add("Les pointages",scorePanel);
        //Ajouter les onglets au frame
        add(tabbedPane);
        pack();
        //setSize(width,height);
        setVisible(true);
    }


}
