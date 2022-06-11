package StarkManagement.View;

import StarkManagement.Model.Employee;
import StarkManagement.Model.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.time.LocalDateTime;

public class ScorePanel extends JPanel {

    JPanel panelGauche;
    JPanel panelDroite;
    JPanel panelInfo;
    JButton buttonSearch;
    JButton buttonUpdate;
    JRadioButton buttonDay;
    JRadioButton buttonAll;
    JTable table;
    Employee selectedEmployee ;
    JScrollPane scroll;


    int selectedRow;
    ScorePanel() {

        setLayout(new BorderLayout());

        panelGauche = new JPanel(new BorderLayout());

        //panelGauche.setSize((int) (Window.width*0.7f), Window.height);

        panelInfo = new JPanel();



        GroupLayout groupLayout = new GroupLayout(panelInfo);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        panelInfo.setLayout(groupLayout);

        JLabel labelName = new JLabel("Name: ");
        JTextField textFieldName = new JTextField();

        JLabel labelDepartment = new JLabel("Department: ");
        JTextField textFieldDepartment = new JTextField();

        buttonSearch = new JButton("Search");

        buttonUpdate = new JButton("Update");
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Score.SaveListScore();
                loadJtableScore();
            }
        });

        buttonDay = new JRadioButton("Day");
        buttonDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJtableScoreoftheday();
            }
        });

        buttonAll = new JRadioButton("All");
        buttonAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJtableScore();
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(buttonDay);
        group.add(buttonAll);


        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelName)
                        .addComponent(labelDepartment))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textFieldName)
                        .addComponent(textFieldDepartment)
                        .addComponent(buttonSearch))
                .addComponent(buttonUpdate)
                .addComponent(buttonDay)
                .addComponent(buttonAll)


        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelName)
                        .addComponent(textFieldName))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDepartment)
                        .addComponent(textFieldDepartment))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(buttonSearch)
                        .addComponent(buttonUpdate)
                        .addComponent(buttonDay)
                        .addComponent(buttonAll))
        );


        panelGauche.add(panelInfo, BorderLayout.NORTH);

        String[] columns = new String[]{
                "Name", "Heure", "Date"
        };

        //données pour JTable dans un tableau 2D
        Object[][] data = new Object[][]{};

        //crée un JTable avec des données
        table = new JTable();
        loadJtableScore();

        scroll = new JScrollPane(table);
        //table.setFillsViewportHeight(true);

        //scroll.setPreferredSize();
        panelGauche.add(scroll, BorderLayout.CENTER);


        //Panel droit
        panelDroite = new JPanel();

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
        JLabel houseDiff = new JLabel();
        JLabel lastSeen = new JLabel();


        groupLayoutDroite.setHorizontalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel)
                        .addComponent(departmentLabel)
                        .addComponent(houseDiffLabel)
                        .addComponent(lastSeenLabel))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(employeeName)
                        .addComponent(departmentName)
                        .addComponent(houseDiff)
                        .addComponent(lastSeen))
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
                        .addComponent(houseDiff))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lastSeenLabel)
                        .addComponent(lastSeen))
        );
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    selectedEmployee = Score.historique.get(row).getEmployee();
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
                    lastSeen.setText(heures+":"+minutes);
                }
            }
        });
        panelDroite.setPreferredSize(new Dimension(300, 400));
        panelDroite.setBackground(new Color(199, 199, 199));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        add(panelGauche, BorderLayout.CENTER);
        add(panelDroite, BorderLayout.EAST);

    }

    public void loadJtableScore(){
        String[] entete = {"Name","Hours","Date"};
        Object[][] datascore = new Object[Score.historique.size()][3];
        int index = 0;
        for(Score s : Score.historique){
            datascore[index][0] = s.getEmployee().getNameEmployee();
            datascore[index][1] = s.getHeure().toString();
            datascore[index][2] = s.getHeure().getDate().toString();

            index++;
        }
        ((DefaultTableModel)table.getModel()).setDataVector(datascore,entete);

    }

    public void loadJtableScoreoftheday(){
        String[] entete = {"Name","Hours","Date"};
        Object[][] datascore = new Object[Score.historique.size()][3];
        int index = 0;
        LocalDateTime now = LocalDateTime.now();
        for(Score s : Score.historique){
            if(s.getHeure().getDate() == now.toLocalDate()) {
                datascore[index][0] = s.getEmployee().getNameEmployee();
                datascore[index][1] = s.getHeure().toString();
                datascore[index][2] = s.getHeure().getDate().toString();// date
                index++;
            }
        }
        ((DefaultTableModel)table.getModel()).setDataVector(datascore,entete);

    }


}

