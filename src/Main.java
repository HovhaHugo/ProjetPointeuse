import Pointeuse.View.Window;
import StarkManagement.Model.*;
import StarkManagement.View.MainWindow;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        //createCompany();

        try
        {
            UIManager.put("TabbedPane.selected", Color.LIGHT_GRAY);

        } catch (Exception ee){
            ee.printStackTrace();
        }

        new Thread(() -> new MainWindow()).start();
        new Thread(() -> new Window()).start();

    }

    public static void createCompany(){

        Company company = new Company();
        company.setNameCompany("Stark Industries");

        Department depInformatique = new Department("Informatique");
        depInformatique.getListEmployee().add(new Employee("Ali","Gator",depInformatique));
        depInformatique.getListEmployee().add(new Employee("Clément","Tine",depInformatique));

        Department depAdministration = new Department("Administration");
        depAdministration.getListEmployee().add(new Employee("Jean","Eymar",depAdministration));
        depAdministration.getListEmployee().add(new Employee("Jean","Tanrien",depAdministration));

        Department depUsine = new Department("Usine");
        depUsine.getListEmployee().add(new Employee("Élie","Coptère",depUsine));
        depUsine.getListEmployee().add(new Employee("Marc","Hassin",depUsine));

        company.getListDepartment().add(depInformatique);
        company.getListDepartment().add(depAdministration);
        company.getListDepartment().add(depUsine);

        FileManipulator.exportCompany(company);

    }
}
