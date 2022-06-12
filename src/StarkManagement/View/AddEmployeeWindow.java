package StarkManagement.View;

import StarkManagement.Model.Company;
import StarkManagement.Model.Department;
import StarkManagement.Model.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Class to create a frame to add an employee inside to compagny
 */
public class AddEmployeeWindow extends JFrame {

    JLabel nameLabel;
    JLabel surnameLabel;
    JLabel departmentLabel;

    JTextField nameTextField;
    JTextField surnameTextField;

    JComboBox departementCombo;

    JButton addEmployeButton;
    JButton newDepartmentButton;

    JButton reloadButton;

    Company company;

    /**
     * Constructor of window
     * @param pCompany
     */
    public AddEmployeeWindow(Company pCompany){

        company = pCompany;

        setSize(450, 200);
        setLocation(100,100);

        setTitle("Add an employee");

        setLayout(null);

        nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(10,0,80,50);

        nameTextField = new JTextField(20);
        nameTextField.setLocation(80,15);
        nameTextField.setSize(100,20);

        surnameLabel = new JLabel("Surname: ");
        surnameLabel.setBounds(10, 30,80,50);

        surnameTextField = new JTextField(20);
        surnameTextField.setLocation(80,45);
        surnameTextField.setSize(100,20);

        departmentLabel = new JLabel("Department: ");
        departmentLabel.setBounds(10, 75,80,30);

        departementCombo = new JComboBox();
        departementCombo.setSize(150, 25);
        for(Department d : company.getListDepartment())
            departementCombo.addItem(d.getNameDepartment());
        departementCombo.setLocation(100, 75);

        reloadButton = new JButton("Reload");
        reloadButton.addActionListener(e -> {
            departementCombo.removeAllItems();
            for(Department d : company.getListDepartment())
                departementCombo.addItem(d.getNameDepartment());
        });
        reloadButton.setBounds(265,75, 80, 25);

        newDepartmentButton = new JButton("Edit");
        newDepartmentButton.addActionListener(e -> {
            //AjoutDepartement d = new AjoutDepartement();
            new AddDepartmentWindow(pCompany);
        });
        newDepartmentButton.setBounds(350,75,70,25);

        addEmployeButton = new JButton("Add");
        addEmployeButton.addActionListener(new ActionListener() {
            /**
             * Button to get the information to create the nesw employee
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                if(name!="" && surname!=""){
                    int index = departementCombo.getSelectedIndex();
                    Employee n = new Employee(name, surname, company.getListDepartment().get(index));
                    MainWindow.sendEmployeeList();
                }
                close();
            }
        });
        addEmployeButton.setBounds(350,125,70,25);

        add(nameLabel);
        add(nameTextField);
        add(surnameLabel);
        add(surnameTextField);
        add(departmentLabel);
        add(departementCombo);
        add(newDepartmentButton);
        add(reloadButton);
        add(addEmployeButton);

        setVisible(true);

    }

    public void close(){
        dispose();
    }

}
