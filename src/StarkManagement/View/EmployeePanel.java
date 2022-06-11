package StarkManagement.View;

import StarkManagement.Model.Company;
import StarkManagement.Model.Employee;
import StarkManagement.Model.Score;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    Employee selectedEmployee;
    int selectedRow;

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

        JLabel nameLabel = new JLabel("Name:");
        JLabel departmentLabel = new JLabel("Department:");
        JLabel houseDiffLabel = new JLabel("Hours difference:");
        JLabel lastSeenLabel = new JLabel("LastSeen:");

        JLabel employeeName = new JLabel();
        JLabel departmentName = new JLabel();
        JLabel hoursDiff = new JLabel();
        JLabel lastSeen = new JLabel();

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

                    employeeName.setText(selectedEmployee.getNameEmployee() +" "+ selectedEmployee.getSurnameEmployee());
                    departmentName.setText(selectedEmployee.getDepartment().getNameDepartment());
                    hoursDiff.setText(selectedEmployee.getStockHoure()+"");
                    lastSeen.setText(heures+":"+minutes);
                }
            }
        });

        buttonUpdate = new JButton("Update");
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJtable();
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
                        .addComponent(departmentLabel)
                        .addComponent(houseDiffLabel)
                        .addComponent(lastSeenLabel)
                        .addComponent(buttonUpdate))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(employeeName)
                        .addComponent(departmentName)
                        .addComponent(hoursDiff)
                        .addComponent(lastSeen)
                        .addComponent(buttonDelete))
        );

        groupLayoutDroite.setVerticalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(employeeName))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(departmentLabel)
                        .addComponent(departmentName))
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

    public void loadJtable(){
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

}
