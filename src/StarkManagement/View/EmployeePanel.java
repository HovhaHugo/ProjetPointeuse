package StarkManagement.View;

import StarkManagement.Model.Company;
import StarkManagement.Model.Employee;

import javax.swing.*;
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

    JTable table;

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
                table.removeAll();
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

        String[] columns = new String[] {
                "Id", "Name", "First Name","Department"
        };

        //données pour JTable dans un tableau 2D
        Object[][] data = new Object[Employee.listEmployee.size()][4];

        int index = 0;
        for(Employee e : Employee.listEmployee){
            data[index][0] = e.getIdentifiant();
            data[index][1] = e.getNameEmployee();
            data[index][2] = e.getSurnameEmployee();
            data[index][3] = e.getDepartment().getNameDepartment();

            index++;
        }

        //crée un JTable avec des données
        table = new JTable(data, columns);
        table.setDefaultEditor(Object.class, null);
        JScrollPane scroll = new JScrollPane(table);
        //table.setFillsViewportHeight(true);

        //scroll.setPreferredSize();
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
                if (row >= 0 ) {
                    Employee e = Employee.getEmplyeeParId((int)table.getValueAt(row,0));

                    int heures = e.getLastScore().getHeure().getHours();
                    int minutes = e.getLastScore().getHeure().getMinutes();

                    employeeName.setText(e.getNameEmployee() + e.getSurnameEmployee());
                    departmentName.setText(e.getDepartment().getNameDepartment());
                    hoursDiff.setText(e.getStockHoure()+"");
                    lastSeen.setText(heures+":"+minutes);
                }
            }
        });

        JButton buttonUpdate = new JButton("Update");
        JButton buttonDelete = new JButton("Delete");

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

/*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int r = (int) (Math.random() * 255);
        int gr = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);

        setBackground(new Color(r,gr,b));
    }*/
}
