package StarkManagement.View;

import StarkManagement.Model.Company;
import StarkManagement.Model.Department;
import StarkManagement.Model.Employee;
import StarkManagement.Model.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to create a frame to add a departement inside to compagny
 */

public class AddDepartmentWindow extends JFrame {

    JLabel nameLabel;
    JTextField nameTextField;
    JTextField updateNameTextField;

    JButton addDepartmentButton;
    JButton deleteDepartmentButton;
    JButton updateDepartmentButton;
    JTable table;
    JScrollPane scroll;
    Company company;
    Department currentDepartment ;

    /**
     * Constructor of the window
     * @param pCompany
     */
    public AddDepartmentWindow(Company pCompany){

        company = pCompany;
        currentDepartment = null;
        setSize(400, 440);
        setLocation(200,200);

        setTitle("Add a department");

        setLayout(null);

        nameLabel = new JLabel("Department: ");
        nameLabel.setBounds(10,0,80,50);

        nameTextField = new JTextField(20);
        nameTextField.setLocation(120,15);
        nameTextField.setSize(100,20);

        addDepartmentButton = new JButton("Add");
        addDepartmentButton.setBounds(300,10,80,25);
        addDepartmentButton.addActionListener(new ActionListener() {
            /**
             * Button that add the departement to the compagny (after creating it)
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                if(name!=""){
                    Department d = new Department(name);
                    company.getListDepartment().add(d);
                }
                close();
            }

        });

        table = new JTable();
        loadJtable();
        scroll = new JScrollPane(table);
        scroll.setBounds(100,80,200,200);

        deleteDepartmentButton = new JButton("Delete");
        deleteDepartmentButton.setBounds(300,350,80,25);
        deleteDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectedRow();
                if (index != -1){
                    company.getListDepartment().remove(index);
                    updateNameTextField.setText("");
                    loadJtable();
                }
            }
        });

        updateNameTextField = new JTextField(20);
        updateNameTextField.setLocation(120,300);
        updateNameTextField.setSize(100,25);

        updateDepartmentButton = new JButton("Update");
        updateDepartmentButton.setBounds(300,300,80,25);
        updateDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentDepartment !=null && !updateNameTextField.getText().equals("")){
                    currentDepartment.setNameDepartment(updateNameTextField.getText());
                    loadJtable();
                }

            }
        });


        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = table.rowAtPoint(evt.getPoint());
                currentDepartment = company.getListDepartment().get(row);
                if (row >= 0) {
                    updateNameTextField.setText(currentDepartment.getNameDepartment());
                }
            }
        });


        add(scroll);
        add(nameLabel);
        add(nameTextField);
        add(addDepartmentButton);
        add(updateNameTextField);
        add(deleteDepartmentButton);
        add(updateDepartmentButton);

        setVisible(true);

    }

    public void close(){
        dispose();
    }

    /**
     * Class to update the table
     */
    public void loadJtable(){
        String[] entete = {"Department"};
        Object[][] data = new Object[company.getListDepartment().size()][1];
        int index = 0;
        for(Department d : company.getListDepartment()){
            data[index][0] = d.getNameDepartment();
            index++;
        }
        ((DefaultTableModel)table.getModel()).setDataVector(data,entete);
    }
}
