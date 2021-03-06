package StarkManagement.View;

import StarkManagement.Model.Employee;
import StarkManagement.Model.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
/***
 * Method to create the panel where we can see and sort the Checks from the pointeuse
 */
public class ScorePanel extends JPanel {

    JPanel panelGauche;
    JPanel panelDroite;
    JPanel panelInfo;
    JButton buttonSearch;
    JButton buttonUpdate;

    JRadioButton radioBtnDay;
    JRadioButton radioBtnAllDay;
    JRadioButton radioBtnIn;
    JRadioButton radioBtnOut;
    JRadioButton radioBtnInOut;

    JTable table;
    Employee selectedEmployee;
    JScrollPane scroll;

    Object[][] datascore;
    int selectedRow;
    /**
     * consturctor of the class
     */
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
            /**
             * Method to Update the data displayed
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTable();
            }
        });

        radioBtnIn = new JRadioButton("In");
        radioBtnIn.addActionListener(new ActionListener() {
            /**
             * Radio button to sort the checks by Type :In
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTable();
            }
        });
        radioBtnOut = new JRadioButton("Out");
        radioBtnOut.addActionListener(new ActionListener() {
            /**
             * Radio button to sort the checks by Type :Out
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTable();
            }
        });
        radioBtnInOut = new JRadioButton("Both", true);
        radioBtnInOut.addActionListener(new ActionListener() {
            /**
             * Radio button to display all the checks
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTable();
            }
        });
        ButtonGroup group2 = new ButtonGroup();
        group2.add(radioBtnIn);
        group2.add(radioBtnOut);
        group2.add(radioBtnInOut);
        radioBtnDay = new JRadioButton("Day");
        radioBtnDay.addActionListener(new ActionListener() {
            /**
             * Radio button to display the check of the day
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTable();
            }
        });

        radioBtnAllDay = new JRadioButton("All", true);
        radioBtnAllDay.addActionListener(new ActionListener() {
            /**
             * Radio button to display all the checks
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loadJTable();
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(radioBtnDay);
        group.add(radioBtnAllDay);


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
                        .addComponent(radioBtnInOut))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(radioBtnIn)
                        .addComponent(radioBtnDay))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(radioBtnOut)
                        .addComponent(radioBtnAllDay))


        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelName)
                        .addComponent(textFieldName))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(labelDepartment)
                        .addComponent(textFieldDepartment)
                        .addComponent(radioBtnInOut)
                        .addComponent(radioBtnIn)
                        .addComponent(radioBtnOut))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(buttonSearch)
                        .addComponent(buttonUpdate)
                        .addComponent(radioBtnDay)
                        .addComponent(radioBtnAllDay))
        );


        panelGauche.add(panelInfo, BorderLayout.NORTH);

        String[] columns = new String[]{
                "Name", "Heure", "Date"
        };

        //donn??es pour JTable dans un tableau 2D
        Object[][] data = new Object[][]{};

        //cr??e un JTable avec des donn??es
        table = new JTable();
        loadJTable();

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
        JLabel houseDiffLabel = new JLabel("Minutes difference:");
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
                    houseDiff.setText(""+selectedEmployee.getStockHoure());
                    employeeName.setText(selectedEmployee.getNameEmployee() +" "+ selectedEmployee.getSurnameEmployee());
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

    /**
     * Method to update the information showed in the table
     * the method take in consideration the state of the radio buttons
     */
    public void loadJTable(){
        String[] entete = {"Name", "Hours", "Date"};

        LocalDateTime now = LocalDateTime.now();
        ArrayList<Score> scoreDayParameter= new ArrayList<>();

        if(radioBtnDay.isSelected()){
            for(Score s : Score.historique){
                if(s.getHeure().getDate().equals(now.toLocalDate())){
                    scoreDayParameter.add(s);
                }
            }
        }else
            scoreDayParameter.addAll(Score.historique);

        ArrayList<Score> scoreInOutParameter= new ArrayList<>();
        if(radioBtnInOut.isSelected() == false){
            for(Score s : Score.historique){
                if(radioBtnIn.isSelected()){
                    if(s.getType() == Score.Type.IN)
                        scoreInOutParameter.add(s);
                }else{
                    if(s.getType() == Score.Type.OUT)
                        scoreInOutParameter.add(s);
                }
            }
        }else
            scoreInOutParameter.addAll(Score.historique);

        scoreDayParameter.retainAll(scoreInOutParameter);

        datascore = new Object[scoreDayParameter.size()][3];
        int index = 0;
        for (Score s : scoreDayParameter) {
            datascore[index][0] = s.getEmployee().getNameEmployee();
            datascore[index][1] = s.getHeure().toString();
            datascore[index][2] = s.getHeure().getDate().toString();

            index++;
        }

        ((DefaultTableModel) table.getModel()).setDataVector(datascore, entete);

    }

}


