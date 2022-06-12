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
    JRadioButton buttonIn;
    JRadioButton buttonOut;
    JRadioButton buttonBoth;
    JTable table;
    Employee selectedEmployee;
    JScrollPane scroll;

    Object[][] datascore;
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
                if (buttonDay.isSelected()) {
                    if(buttonBoth.isSelected()) {
                        loadJtableScoreofthedayBoth();
                    }
                    if(buttonIn.isSelected()) {
                        loadJtableScoreofthedayIn();
                    }
                    if(buttonOut.isSelected()) {
                        loadJtableScoreofthedayOut();
                    }
                }
                if (buttonAll.isSelected()) {
                    if(buttonBoth.isSelected()) {
                        loadJtableScoreBoth();
                    }
                    if(buttonIn.isSelected()) {
                        loadJtableScoreIn();
                    }
                    if(buttonOut.isSelected()) {
                        loadJtableScoreOut();
                    }
                }
            }
        });

        buttonIn = new JRadioButton("In");
        buttonIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonDay.isSelected()) {
                    loadJtableScoreofthedayIn();
                }
                if (buttonAll.isSelected()) {
                    loadJtableScoreIn();
                }
            }
        });
        buttonOut = new JRadioButton("Out");
        buttonOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonDay.isSelected()) {
                    loadJtableScoreofthedayOut();
                }
                if (buttonAll.isSelected()) {
                    loadJtableScoreOut();
                }
            }
        });
        buttonBoth = new JRadioButton("Both", true);
        buttonBoth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonDay.isSelected()) {
                    loadJtableScoreofthedayBoth();
                }
                if (buttonAll.isSelected()) {
                    loadJtableScoreBoth();
                }
            }
        });
        ButtonGroup group2 = new ButtonGroup();
        group2.add(buttonIn);
        group2.add(buttonOut);
        group2.add(buttonBoth);
        buttonDay = new JRadioButton("Day");
        buttonDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(buttonBoth.isSelected()) {
                    loadJtableScoreofthedayBoth();
                }
                if(buttonIn.isSelected()) {
                    loadJtableScoreofthedayIn();
                }
                if(buttonOut.isSelected()) {
                    loadJtableScoreofthedayOut();
                }
            }
        });

        buttonAll = new JRadioButton("All", true);
        buttonAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(buttonBoth.isSelected()) {
                        loadJtableScoreBoth();
                    }
                    if(buttonIn.isSelected()) {
                        loadJtableScoreIn();
                    }
                    if(buttonOut.isSelected()) {
                        loadJtableScoreOut();
                    }

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
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonUpdate)
                        .addComponent(buttonBoth))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonIn)
                        .addComponent(buttonDay))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(buttonOut)
                        .addComponent(buttonAll))


        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelName)
                        .addComponent(textFieldName))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDepartment)
                        .addComponent(textFieldDepartment)
                        .addComponent(buttonBoth)
                        .addComponent(buttonIn)
                        .addComponent(buttonOut))
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
        loadJtableScoreBoth();

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
                    if (lastScore != null) {
                        heures = lastScore.getHeure().getHours();
                        minutes = lastScore.getHeure().getMinutes();
                    }

                    employeeName.setText(selectedEmployee.getNameEmployee() + " " + selectedEmployee.getSurnameEmployee());
                    departmentName.setText(selectedEmployee.getDepartment().getNameDepartment());
                    lastSeen.setText(heures + ":" + minutes);
                }
            }
        });
        panelDroite.setPreferredSize(new Dimension(300, 400));
        panelDroite.setBackground(new Color(199, 199, 199));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
        add(panelGauche, BorderLayout.CENTER);
        add(panelDroite, BorderLayout.EAST);

    }

    public void loadJtableScoreBoth() {
        String[] entete = {"Name", "Hours", "Date"};
         datascore = new Object[Score.historique.size()][3];
        int index = 0;
        for (Score s : Score.historique) {
            datascore[index][0] = s.getEmployee().getNameEmployee();
            datascore[index][1] = s.getHeure().toString();
            datascore[index][2] = s.getHeure().getDate().toString();

            index++;
        }
        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);

    }

    public void loadJtableScoreofthedayBoth() {
        String[] entete = {"Name", "Hours", "Date"};
         datascore = new Object[Score.historique.size()][3];
        int index = 0;
        LocalDateTime now = LocalDateTime.now();
        for (Score s : Score.historique) {
            if (s.getHeure().getDate().equals(now.toLocalDate())) {
                datascore[index][0] = s.getEmployee().getNameEmployee();
                datascore[index][1] = s.getHeure().toString();
                datascore[index][2] = s.getHeure().getDate().toString();// date
                index++;
            }
        }
        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);
    }
    public void loadJtableScoreIn() {
        String[] entete = {"Name", "Hours", "Date"};
        datascore = new Object[Score.historique.size()][3];
        int index = 0;
        for (Score s : Score.historique) {
            if (s.getType() == Score.Type.IN) {
                datascore[index][0] = s.getEmployee().getNameEmployee();
                datascore[index][1] = s.getHeure().toString();
                datascore[index][2] = s.getHeure().getDate().toString();
                index++;
            }
        }
        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);

    }
    public void loadJtableScoreofthedayIn() {
        String[] entete = {"Name", "Hours", "Date"};
        datascore = new Object[Score.historique.size()][3];
        int index = 0;
        LocalDateTime now = LocalDateTime.now();
        for (Score s : Score.historique) {
            if (s.getHeure().getDate().equals(now.toLocalDate())) {
                if (s.getType() == Score.Type.IN) {
                    datascore[index][0] = s.getEmployee().getNameEmployee();
                    datascore[index][1] = s.getHeure().toString();
                    datascore[index][2] = s.getHeure().getDate().toString();// date
                    index++;
                }
            }
        }
        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);
    }
    public void loadJtableScoreOut() {
        String[] entete = {"Name", "Hours", "Date"};
        datascore = new Object[Score.historique.size()][3];
        int index = 0;
        for (Score s : Score.historique) {
            if (s.getType() == Score.Type.OUT) {
                datascore[index][0] = s.getEmployee().getNameEmployee();
                datascore[index][1] = s.getHeure().toString();
                datascore[index][2] = s.getHeure().getDate().toString();
                index++;
            }
        }
        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);

    }
    public void loadJtableScoreofthedayOut() {
        String[] entete = {"Name", "Hours", "Date"};
        datascore = new Object[Score.historique.size()][3];
        int index = 0;
        LocalDateTime now = LocalDateTime.now();
        for (Score s : Score.historique) {
            if (s.getHeure().getDate().equals(now.toLocalDate())) {
                if (s.getType() == Score.Type.OUT) {
                    datascore[index][0] = s.getEmployee().getNameEmployee();
                    datascore[index][1] = s.getHeure().toString();
                    datascore[index][2] = s.getHeure().getDate().toString();// date
                    index++;
                }
            }
        }
        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);
    }

}


