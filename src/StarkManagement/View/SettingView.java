package StarkManagement.View;

import StarkManagement.Controller.SettingControler;
import StarkManagement.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SettingView extends JPanel {

    Image starkLogoImage;
    String IPV4 = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
    Boolean epileptique = true;
    Integer countEpileptique = 0;

    JLabel tabLabel;
    JButton exitButton;

    JButton easterButton;
    JTextField inputTextfield;

    JTextField portTextfield;
    JLabel portLabel;

    JTextField adressTextfield;
    JLabel adressLabel;

    JLabel errorLabel;

    JLabel checkButton;


    public SettingView(Window window) throws IOException {

        setLayout(null);
        setBackground(Color.BLACK);
        SettingControler.importeSetting();

        starkLogoImage = ImageIO.read(new File("data/img/stark.png"));

        // Label
        tabLabel = new JLabel("Parametre de la pointeuse");
        tabLabel.setForeground(Color.WHITE);
        tabLabel.setBounds(18, 19, 350,50);
        tabLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        portLabel = new JLabel("Port : ");
        portLabel.setForeground(Color.WHITE);
        portLabel.setBounds(45, 100, 150,50);
        portLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        adressLabel = new JLabel("Adresse : ");
        adressLabel.setForeground(Color.WHITE);
        adressLabel.setBounds(45, 180, 150,50);
        adressLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(165, 45, 350,50);
        errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        //TextField
        inputTextfield = new JTextField();
        inputTextfield.setBounds(45,275,200,35);
        inputTextfield.setBackground(Color.BLACK);
        inputTextfield.setBorder(new LineBorder(Color.CYAN,1));
        inputTextfield.setForeground(Color.WHITE);
        inputTextfield.setFont(new Font("SansSerif", Font.BOLD, 18));

        portTextfield = new JTextField();
        portTextfield.setBounds(100,105,200,35);
        portTextfield.setBackground(Color.BLACK);
        portTextfield.setText(""+SettingControler.getPointeusePort());
        portTextfield.setBorder(new LineBorder(Color.CYAN,1));
        portTextfield.setForeground(Color.WHITE);
        portTextfield.setFont(new Font("SansSerif", Font.BOLD, 18));

        adressTextfield = new JTextField();
        adressTextfield.setBounds(130,190,200,35);
        adressTextfield.setBackground(Color.BLACK);
        adressTextfield.setText(SettingControler.getPointeuseAdress());
        adressTextfield.setBorder(new LineBorder(Color.CYAN,1));
        adressTextfield.setForeground(Color.WHITE);
        adressTextfield.setFont(new Font("SansSerif", Font.BOLD, 18));

        //Button
        checkButton = new JLabel();
        Image a = ImageIO.read(new File("data/img/button_check.png"));
        a = a.getScaledInstance(60,35,Image.SCALE_SMOOTH);
        checkButton.setIcon(new ImageIcon(a));
        checkButton.setBounds(265, 267, 150,50);

        exitButton = new JButton("x");
        exitButton.setBounds(550,0, 30,30);
        exitButton.setBackground(Color.RED);


        easterButton = new JButton("!");
        easterButton.setBounds(550,320, 30,30);
        easterButton.setBackground(Color.BLACK);


        //Method
        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                checkText();
            }
        });

        exitButton.addActionListener(e -> {
            System.out.println("Fin du programme");
            window.dispose();
        });

        easterButton.addActionListener(e -> {
            if(countEpileptique == 0){
                epileptique = false;
                countEpileptique++;
            }else{
                epileptique = true;
                countEpileptique--;
                Color d = new Color(0,0,0);
                setBackground(d);
            }
        });

        //Add
        add(checkButton);
        add(easterButton);
        add(errorLabel);
        //add(inputTextfield);
        add(portTextfield);
        add(adressTextfield);
        add(tabLabel);
        add(portLabel);
        add(adressLabel);
        add(exitButton);
    }

    private void checkText(){
        System.out.println("Vérification des données");
        int port;
        String adress = adressTextfield.getText();
        try{
            port = Integer.parseInt(portTextfield.getText());
            portTextfield.setBorder(new LineBorder(Color.CYAN,1));
        }catch (NumberFormatException error){
            port = -1;
            portTextfield.setBorder(new LineBorder(Color.RED,1));
            errorLabel.setText("Error : Nombre non/mal saisie");
            System.out.println("Nombre nécessaire");
        }

        //Element to compare
        String localhost = "localhost";

        if(!adress.equals(localhost)){
            if(!adress.matches(IPV4)){
                adress = "";
                System.out.println("Adresse IP non valide");
                errorLabel.setText("Error : Adresse IP non valide");
                adressTextfield.setBorder(new LineBorder(Color.RED,1));
            }else{
                adressTextfield.setBorder(new LineBorder(Color.CYAN,1));
            }
        }else{
            adressTextfield.setBorder(new LineBorder(Color.CYAN,1));
        }

        if(port != -1 && !adress.equals("")){
            errorLabel.setText("");
            System.out.println("Tout est bon");
            SettingControler.setSettings(port, adress);
            SettingControler.exporteSetting();
        }
    }

    public void paintComponent(Graphics g) {

        // Draw the background image.
        g.drawImage(starkLogoImage, (int) (Pointeuse.View.Window.WIDTH *0.74f), (int) (Pointeuse.View.Window.HEIGHT *0.81f), 140,55,this);


        if(!epileptique){
            Color t = new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int)(Math.random() * 255));
            setBackground(t);
        }

        super.paintComponent(g);

    }

}
