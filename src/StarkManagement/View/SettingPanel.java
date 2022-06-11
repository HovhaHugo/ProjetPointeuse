package StarkManagement.View;

import StarkManagement.Controller.SettingControler;
import StarkManagement.Model.Employee;
import StarkManagement.Model.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingPanel extends JPanel {

    String IPV4 = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";

    JTextField portTextfield;
    JLabel portLabel;

    JTextField adressTextfield;
    JLabel adressLabel;

    JLabel errorLabel;
    JLabel descriptionError;

    JButton checkButton;


    SettingPanel(Settings settings) {

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JPanel panelGauche = new JPanel(new BorderLayout());
        panelGauche.setBackground(Color.BLACK);

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(Color.BLACK);

        GroupLayout groupLayout = new GroupLayout(panelInfo);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        panelInfo.setLayout(groupLayout);

        portLabel = new JLabel("Port : ");
        portLabel.setForeground(Color.WHITE);

        portTextfield = new JTextField();
        portTextfield.setText(""+settings.getPort());
        portTextfield.setBackground(Color.BLACK);
        portTextfield.setBorder(new LineBorder(Color.CYAN,1));
        portTextfield.setForeground(Color.WHITE);

        adressLabel = new JLabel("Adress : ");
        adressLabel.setForeground(Color.WHITE);

        adressTextfield = new JTextField();
        adressTextfield.setText(settings.getIp());
        adressTextfield.setBackground(Color.BLACK);
        adressTextfield.setBorder(new LineBorder(Color.CYAN,1));
        adressTextfield.setForeground(Color.WHITE);


        checkButton = new JButton("Valid");


        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(portLabel)
                        .addComponent(adressLabel))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(portTextfield)
                        .addComponent(adressTextfield)
                .addComponent(checkButton)
                )

        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(portLabel)
                        .addComponent(portTextfield))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(adressLabel)
                        .addComponent(adressTextfield))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(checkButton))
        );

        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                checkText();
            }
        });

        panelGauche.add(panelInfo,BorderLayout.NORTH);

        //Panel droit
        JPanel panelDroite = new JPanel();
        panelDroite.setBackground(Color.GRAY);

        GroupLayout groupLayoutDroite = new GroupLayout(panelDroite);
        groupLayoutDroite.setAutoCreateGaps(true);
        groupLayoutDroite.setAutoCreateContainerGaps(true);
        panelDroite.setLayout(groupLayoutDroite);

        JLabel descriptionLabel = new JLabel("Description:");

        JLabel descriptionText1 = new JLabel("Cette partie permet de regler le port et l'adresse ");
        JLabel descriptionText2 = new JLabel("qui vont nous être utile pour l'envoie de données");
        JLabel descriptionPort = new JLabel(" - Port : Port d'acoute de la pointeuse");
        JLabel descriptionAdress = new JLabel(" - Adresse : Adresse de la pointeuse)");

        errorLabel = new JLabel("");
        descriptionError = new JLabel("");

        groupLayoutDroite.setHorizontalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(descriptionLabel)
                        .addComponent(errorLabel))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(descriptionText1)
                        .addComponent(descriptionText2)
                        .addComponent(descriptionPort)
                        .addComponent(descriptionAdress)
                        .addComponent(descriptionError))
        );

        groupLayoutDroite.setVerticalGroup(groupLayoutDroite.createSequentialGroup()
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(descriptionLabel)
                        .addComponent(descriptionText1))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(descriptionText2))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(descriptionPort))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(descriptionAdress))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(errorLabel))
                .addGroup(groupLayoutDroite.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(descriptionError))
        );

        panelDroite.setPreferredSize(new Dimension(400,400));
        panelGauche.setBorder(BorderFactory.createEmptyBorder(5,5 ,5 , 10));
        add(panelGauche, BorderLayout.CENTER);
        add(panelDroite, BorderLayout.EAST);
    }

    private void checkText(){
        System.out.println("Vérification des données");
        String errorText = "";
        int port;
        String adress = adressTextfield.getText();
        try{
            port = Integer.parseInt(portTextfield.getText());
            portTextfield.setBorder(new LineBorder(Color.CYAN,1));
        }catch (NumberFormatException error){
            port = -1;
            portTextfield.setBorder(new LineBorder(Color.RED,1));
            errorText = errorText + " Nombre non/mal saisie";
            System.out.println("Nombre nécessaire");
        }

        //Element to compare
        String localhost = "localhost";

        if(!adress.equals(localhost)){
            if(!adress.matches(IPV4)){
                adress = "";
                errorText = errorText + " Adresse IP non valide";
                System.out.println("Adresse IP non valide");
                //errorLabel.setText("Error : Adresse IP non valide");
                adressTextfield.setBorder(new LineBorder(Color.RED,1));
            }else{
                adressTextfield.setBorder(new LineBorder(Color.CYAN,1));
            }
        }else{
            adressTextfield.setBorder(new LineBorder(Color.CYAN,1));
        }
        errorLabel.setText("Error :");
        descriptionError.setText(errorText);

        if(port != -1 && !adress.equals("")){
            errorLabel.setText("");
            System.out.println("Tout est bon");
            SettingControler.update(port, adress);
            SettingControler.exporteSetting();
        }
    }
}
