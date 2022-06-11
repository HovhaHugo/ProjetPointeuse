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
import java.util.ArrayList;

public class EmployeePanel extends JPanel {

    Company company;

    JPanel panelGauche;
    JPanel panelInfo;

    JButton buttonSearch;
    JButton reloadButton;
    JButton buttonAdd;

    JButton buttonUpdate;
    JButton buttonDelete;

    JScrollPane scroll;
    JTable table;

    //temp to avoid bug if the company dep list is change between an other change
    ArrayList<Department> depListTemp;
    Employee selectedEmployee;
    int selectedRow;

    boolean updateMode = false;

    JLabel nameLabel = new JLabel("Name:");
    JLabel firstnameLabel = new JLabel("Firstname:");
    JLabel departmentLabel = new JLabel("Department:");
    JLabel houseDiffLabel = new JLabel("Hours difference:");
    JLabel lastSeenLabel = new JLabel("LastSeen:");

    JTextField employeeNameTextfield;
    JTextField employeeFirstnameTextfield;
    JComboBox departmentNameCombo;
    JLabel hoursDiff;
    JLabel lastSeen;

    EmployeePanel(Company pCompany){

        company = pCompany;

        setLayout(new BorderLayout());

        panelGauche = new JPanel(new BorderLayout());
        panelInfo = new JPanel();

        GroupLayout groupLayoutPanelDroite = new GroupLayout(panelInfo);
        groupLayoutPanelDroite.setAutoCreateGaps(true);
        groupLayoutPanelDroite.setAutoCreateContainerGaps(true);
        panelInfo.setLayout(groupLayoutPanelDroite);

        JLabel labelName = new JLabel("Name: ");
        JTextField textFieldName = new JTextField();

        JLabel labelDepartment = new JLabel("Department: ");
        JTextField textFieldDepartment = new JTextField();

        buttonSearch = new JButton("Search");
        buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmployeeWindow(company);
            }
        });
        reloadButton = new JButton("Reload");
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJtable();
                depListTemp = new ArrayList<>(company.getListDepartment());
            }
        });

        groupLayoutPanelDroite.setHorizontalGroup(groupLayoutPanelDroite.createSequentialGroup()
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelName)
                        .addComponent(labelDepartment))
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldName)
                        .addComponent(textFieldDepartment)
                        .addComponent(buttonSearch))
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonAdd))
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(reloadButton))

        );

        groupLayoutPanelDroite.setVerticalGroup(groupLayoutPanelDroite.createSequentialGroup()
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelName)
                        .addComponent(textFieldName))
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDepartment)
                        .addComponent(textFieldDepartment))
                .addGroup(groupLayoutPanelDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSearch)
                        .addComponent(buttonAdd)
                        .addComponent(reloadButton))
        );





        panelGauche.add(panelInfo,BorderLayout.NORTH);

        table = new JTable();
        loadJtable();
        scroll = new JScrollPane(table);

        panelGauche.add(scroll,BorderLayout.CENTER);

        //Panel droit
        JPanel panelDroite = new JPanel();

        GroupLayout groupLayoutDroite = new GroupLayout(panelDroite);
        groupLayoutDroite.setAutoCreateGaps(true);
        groupLayoutDroite.setAutoCreateContainerGaps(true);
        panelDroite.setLayout(groupLayoutDroite);

        employeeNameTextfield = new JTextField();
        employeeNameTextfield.setEditable(false);

        employeeFirstnameTextfield = new JTextField();
        employeeFirstnameTextfield.setEditable(false);

        departmentNameCombo = new JComboBox();
        departmentNameCombo.setEnabled(false);

        hoursDiff = new JLabel();
        lastSeen = new JLabel();

        depListTemp = new ArrayList<>(company.getListDepartment());

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    selectedEmployee = Employee.getEmplyeeParId((int)table.getValueAt(row,0));
                    selectedRow = row;

                    int heures = 0;
                    int minutes = 0;
                    Score lastScore = selectedEmployee.getLastScore();
                    if(lastScore!=null){
                        heures = lastScore.getHeure().getHours();
                        minutes = lastScore.getHeure().getMinutes();
                    }

                    employeeNameTextfield.setText(selectedEmployee.getNameEmployee());
                    employeeFirstnameTextfield.setText(selectedEmployee.getSurnameEmployee());
                    hoursDiff.setText(selectedEmployee.getStockHoure()+"");
                    lastSeen.setText(heures+":"+minutes);

                    int currentRow = 0;
                    for(int i = 0; i< depListTemp.size(); i++){
                        Department d = depListTemp.get(i);
                        departmentNameCombo.addItem(d.getNameDepartment());
                        if(d == selectedEmployee.getDepartment())
                            currentRow = i;
                    }
                    departmentNameCombo.setSelectedIndex(currentRow);
                    if(updateMode==true)
                        toggleUpdateMode();
                }
            }
        });

        buttonUpdate = new JButton("Update");
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(updateMode==false){
                    toggleUpdateMode();
                }else{
                    toggleUpdateMode();
                    loadJtable();
                    selectedEmployee.setNameEmployee(employeeNameTextfield.getText());
                    selectedEmployee.setSurnameEmployee(employeeFirstnameTextfield.getText());
                    selectedEmployee.setDepartment(depListTemp.get(departmentNameCombo.getSelectedIndex()));
                    loadJtable();
                }
            }
        });

        buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedRow>=table.getRowCount())
                    return;

                Employee.listEmployee.remove(selectedEmployee);
                selectedEmployee.getDepartment().getListEmployee().remove(selectedEmployee);
                ((DefaultTableModel)table.getModel()).removeRow(selectedRow);
            }
        });

        groupLayoutDroite.setHorizontalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(firstnameLabel)
                        .addComponent(departmentLabel)
                        .addComponent(houseDiffLabel)
                        .addComponent(lastSeenLabel)
                        .addComponent(buttonUpdate))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(employeeNameTextfield)
                        .addComponent(employeeFirstnameTextfield)
                        .addComponent(departmentNameCombo)
                        .addComponent(hoursDiff)
                        .addComponent(lastSeen)
                        .addComponent(buttonDelete))
        );

        groupLayoutDroite.setVerticalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(employeeNameTextfield))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(firstnameLabel)
                        .addComponent(employeeFirstnameTextfield))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(departmentLabel)
                        .addComponent(departmentNameCombo))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(houseDiffLabel)
                        .addComponent(hoursDiff))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lastSeenLabel)
                        .addComponent(lastSeen))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonUpdate)
                        .addComponent(buttonDelete))
        );

        panelDroite.setPreferredSize(new Dimension(300,400));
        panelDroite.setBackground(new Color(199, 199, 199));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(5,5 ,5 , 10));
        add(panelGauche, BorderLayout.CENTER);
        add(panelDroite, BorderLayout.EAST);
    }

    private void loadJtable(){
        String[] entete = {"ID","Name","Firstname","Department"};
        Object[][] data = new Object[Employee.listEmployee.size()][4];
        int index = 0;
        for(Employee e : Employee.listEmployee){
            data[index][0] = e.getIdentifiant();
            data[index][1] = e.getNameEmployee() ;
            data[index][1] = e.getNameEmployee();
            data[index][2] = e.getSurnameEmployee();
            data[index][3] = e.getDepartment().getNameDepartment();

            index++;
        }
        ((DefaultTableModel)table.getModel()).setDataVector(data,entete);
    }

    private void toggleUpdateMode(){
        if(updateMode==false){
            updateMode = true;
            buttonUpdate.setText("Confirm");
            employeeNameTextfield.setEditable(true);
            employeeFirstnameTextfield.setEditable(true);
            departmentNameCombo.setEnabled(true);
        }else{
            updateMode = false;
            buttonUpdate.setText("Update");
            employeeNameTextfield.setEditable(false);
            employeeFirstnameTextfield.setEditable(false);
            departmentNameCombo.setEnabled(false);
        }


    }


}
