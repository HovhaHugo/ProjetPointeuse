package StarkManagement.View;

import Common.Hours;
import StarkManagement.Model.*;

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

    JTextField mondayStartTimeTextfield;
    JTextField mondayEndTimeTextfield;
    JTextField tuesdayStartTimeTextfield;
    JTextField tuesdayEndTimeTextfield;
    JTextField wednesdayStartTimeTextfield;
    JTextField wednesdayEndTimeTextfield;
    JTextField thursdayStartTimeTextfield;
    JTextField thursdayEndTimeTextfield;
    JTextField fridayStartTimeTextfield;
    JTextField fridayEndTimeTextfield;
    JComboBox departmentNameCombo;
    JLabel hoursDiff;
    JLabel lastSeen;
    JLabel planningLabel;
    JLabel mondayLabel;
    JLabel tuesdayLabel;
    JLabel wednesdayLabel;
    JLabel thursdayLabel;
    JLabel fridayLabel;



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

        planningLabel = new JLabel("Planning :");
        mondayLabel = new JLabel("  - Monday :");
        mondayStartTimeTextfield = new JTextField();
        mondayEndTimeTextfield= new JTextField();
        mondayStartTimeTextfield.setEditable(false);
        mondayEndTimeTextfield.setEditable(false);

        tuesdayLabel = new JLabel("  - Tuesday :");
        tuesdayStartTimeTextfield= new JTextField();
        tuesdayEndTimeTextfield= new JTextField();
        tuesdayStartTimeTextfield.setEditable(false);
        tuesdayEndTimeTextfield.setEditable(false);

        wednesdayLabel = new JLabel("  - Wednesday :");
        wednesdayStartTimeTextfield= new JTextField();
        wednesdayEndTimeTextfield= new JTextField();
        wednesdayStartTimeTextfield.setEditable(false);
        wednesdayEndTimeTextfield.setEditable(false);

        thursdayLabel = new JLabel("  - Thursday :");
        thursdayStartTimeTextfield= new JTextField();
        thursdayEndTimeTextfield= new JTextField();
        thursdayStartTimeTextfield.setEditable(false);
        thursdayEndTimeTextfield.setEditable(false);

        fridayLabel = new JLabel("  - Friday :");
        fridayStartTimeTextfield= new JTextField();
        fridayEndTimeTextfield= new JTextField();
        fridayStartTimeTextfield.setEditable(false);
        fridayEndTimeTextfield.setEditable(false);


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

                    mondayStartTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Monday).getStartTime().toString());
                    mondayEndTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Monday).getEndTime().toString());
                    tuesdayStartTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Tuesday).getStartTime().toString());
                    tuesdayEndTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Tuesday).getEndTime().toString());
                    wednesdayStartTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Wednesday).getStartTime().toString());
                    wednesdayEndTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Wednesday).getEndTime().toString());
                    thursdayStartTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Thursday).getStartTime().toString());
                    thursdayEndTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Thursday).getEndTime().toString());
                    fridayStartTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Friday).getStartTime().toString());
                    fridayEndTimeTextfield.setText(selectedEmployee.getPlanning().getPlanning().get(Days.Friday).getEndTime().toString());

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
                    if (isDateFormat()){
                    toggleUpdateMode();
                    loadJtable();
                    selectedEmployee.setNameEmployee(employeeNameTextfield.getText());
                    selectedEmployee.setSurnameEmployee(employeeFirstnameTextfield.getText());
                    selectedEmployee.setDepartment(depListTemp.get(departmentNameCombo.getSelectedIndex()));

                    Hours hs1 = getHourFromText(mondayStartTimeTextfield.getText());
                    Hours he1 = getHourFromText(mondayEndTimeTextfield.getText());
                    selectedEmployee.getPlanning().getPlanning().get(Days.Monday).setStartTime(hs1);
                    selectedEmployee.getPlanning().getPlanning().get(Days.Monday).setEndTime(he1);

                    Hours hs2 = getHourFromText(tuesdayStartTimeTextfield.getText());
                    Hours he2 = getHourFromText(tuesdayEndTimeTextfield.getText());
                    selectedEmployee.getPlanning().getPlanning().get(Days.Tuesday).setStartTime(hs2);
                    selectedEmployee.getPlanning().getPlanning().get(Days.Tuesday).setEndTime(he2);

                    Hours hs3 = getHourFromText(wednesdayStartTimeTextfield.getText());
                    Hours he3 = getHourFromText(wednesdayEndTimeTextfield.getText());
                    selectedEmployee.getPlanning().getPlanning().get(Days.Wednesday).setStartTime(hs3);
                    selectedEmployee.getPlanning().getPlanning().get(Days.Wednesday).setEndTime(he3);

                    Hours hs4 = getHourFromText(thursdayStartTimeTextfield.getText());
                    Hours he4 = getHourFromText(thursdayEndTimeTextfield.getText());
                    selectedEmployee.getPlanning().getPlanning().get(Days.Thursday).setStartTime(hs4);
                    selectedEmployee.getPlanning().getPlanning().get(Days.Thursday).setEndTime(he4);

                    Hours hs5 = getHourFromText(fridayStartTimeTextfield.getText());
                    Hours he5 = getHourFromText(fridayEndTimeTextfield.getText());
                    selectedEmployee.getPlanning().getPlanning().get(Days.Friday).setStartTime(hs5);
                    selectedEmployee.getPlanning().getPlanning().get(Days.Friday).setEndTime(he5);

                    loadJtable();}
                    else JOptionPane.showMessageDialog(new JFrame(), "mé bonn datt fdp.");
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
                MainWindow.sendEmployeeList();
            }
        });

        groupLayoutDroite.setHorizontalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(firstnameLabel)
                        .addComponent(departmentLabel)
                        .addComponent(houseDiffLabel)
                        .addComponent(lastSeenLabel)
                        .addComponent(planningLabel)
                        .addComponent(mondayLabel)
                        .addComponent(tuesdayLabel)
                        .addComponent(wednesdayLabel)
                        .addComponent(thursdayLabel)
                        .addComponent(fridayLabel)
                        .addComponent(buttonUpdate))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(employeeNameTextfield)
                        .addComponent(employeeFirstnameTextfield)
                        .addComponent(departmentNameCombo)
                        .addComponent(hoursDiff)
                        .addComponent(lastSeen)
                        .addGroup(groupLayoutDroite.createSequentialGroup()
                                .addComponent(mondayStartTimeTextfield)
                                .addComponent(mondayEndTimeTextfield))
                        .addGroup(groupLayoutDroite.createSequentialGroup()
                                .addComponent(tuesdayStartTimeTextfield)
                                .addComponent(tuesdayEndTimeTextfield))
                        .addGroup(groupLayoutDroite.createSequentialGroup()
                                .addComponent(wednesdayStartTimeTextfield)
                                .addComponent(wednesdayEndTimeTextfield))
                        .addGroup(groupLayoutDroite.createSequentialGroup()
                                .addComponent(thursdayStartTimeTextfield)
                                .addComponent(thursdayEndTimeTextfield))
                        .addGroup(groupLayoutDroite.createSequentialGroup()
                                .addComponent(fridayStartTimeTextfield)
                                .addComponent(fridayEndTimeTextfield))
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
                        .addComponent(planningLabel))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(mondayLabel)
                        .addComponent(mondayStartTimeTextfield)
                        .addComponent(mondayEndTimeTextfield))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tuesdayLabel)
                        .addComponent(tuesdayStartTimeTextfield)
                        .addComponent(tuesdayEndTimeTextfield))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(wednesdayLabel)
                        .addComponent(wednesdayStartTimeTextfield)
                        .addComponent(wednesdayEndTimeTextfield))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(thursdayLabel)
                        .addComponent(thursdayStartTimeTextfield)
                        .addComponent(thursdayEndTimeTextfield))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fridayLabel)
                        .addComponent(fridayStartTimeTextfield)
                        .addComponent(fridayEndTimeTextfield))
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

            mondayStartTimeTextfield.setEditable(true);
            mondayEndTimeTextfield.setEditable(true);
            tuesdayStartTimeTextfield.setEditable(true);
            tuesdayEndTimeTextfield.setEditable(true);
            wednesdayStartTimeTextfield.setEditable(true);
            wednesdayEndTimeTextfield.setEditable(true);
            thursdayStartTimeTextfield.setEditable(true);
            thursdayEndTimeTextfield.setEditable(true);
            fridayStartTimeTextfield.setEditable(true);
            fridayEndTimeTextfield.setEditable(true);
        }else{
            updateMode = false;
            buttonUpdate.setText("Update");
            employeeNameTextfield.setEditable(false);
            employeeFirstnameTextfield.setEditable(false);
            departmentNameCombo.setEnabled(false);

            mondayStartTimeTextfield.setEditable(false);
            mondayEndTimeTextfield.setEditable(false);
            tuesdayStartTimeTextfield.setEditable(false);
            tuesdayEndTimeTextfield.setEditable(false);
            wednesdayStartTimeTextfield.setEditable(false);
            wednesdayEndTimeTextfield.setEditable(false);
            thursdayStartTimeTextfield.setEditable(false);
            thursdayEndTimeTextfield.setEditable(false);
            fridayStartTimeTextfield.setEditable(false);
            fridayEndTimeTextfield.setEditable(false);
        }
    }

    public Hours getHourFromText(String stringHour){
        Hours h = null;

        int hour =Integer.parseInt(stringHour.substring(0,stringHour.indexOf(':')));
        int minutes =Integer.parseInt(stringHour.substring(stringHour.indexOf(':')+1));
        h = new Hours(hour,minutes);

        return h;
    }

    public boolean isDateFormat(){

        String format = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        if (mondayStartTimeTextfield.getText().matches(format)
                && mondayEndTimeTextfield.getText().matches(format)
                && tuesdayStartTimeTextfield.getText().matches(format)
                && tuesdayEndTimeTextfield.getText().matches(format)
                && wednesdayStartTimeTextfield.getText().matches(format)
                && wednesdayEndTimeTextfield.getText().matches(format)
                && thursdayStartTimeTextfield.getText().matches(format)
                && thursdayEndTimeTextfield.getText().matches(format)
                && fridayStartTimeTextfield.getText().matches(format)
                && fridayEndTimeTextfield.getText().matches(format))
            return true;

        return false;
    }


}
