package StarkManagement.View;

import Pointeuse.Controller.Hours;
import Pointeuse.Controller.PersonnShort;
import StarkManagement.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class Parametre extends JPanel {

    Image starkLogoImage;
    String IPV4 = "^(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?)."+
            "(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?).(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?)" +
            ".(25[0–5]|2[0–4][0–9]|[01]?[0–9][0–9]?)$";
    Boolean epileptique = true;
    Integer countEpileptique = 0;

    JLabel tabLabel;
    JButton exitButton;

    JButton easterButton;
    JTextField inputTextfield;

    JTextField portTextfield;
    JLabel portLabel;

    JLabel checkButton;

    JTextField adressTextfield;
    JLabel adressLabel;

    public Parametre(Window window) throws IOException {

        setLayout(null);
        setBackground(Color.BLACK);

        starkLogoImage = ImageIO.read(new File("data/img/stark.png"));

        // Label
        tabLabel = new JLabel("Parametre");
        tabLabel.setForeground(Color.WHITE);
        tabLabel.setBounds(18, 19, 150,50);
        tabLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        portLabel = new JLabel("Port : ");
        portLabel.setForeground(Color.WHITE);
        portLabel.setBounds(45, 100, 150,50);
        portLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        adressLabel = new JLabel("Adresse : ");
        adressLabel.setForeground(Color.WHITE);
        adressLabel.setBounds(45, 180, 150,50);
        adressLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));

        //TextField
        inputTextfield = new JTextField();
        inputTextfield.setBounds(45,275,200,35);
        inputTextfield.setBackground(Color.BLACK);
        inputTextfield.setBorder(new LineBorder(Color.CYAN,1));
        inputTextfield.setForeground(Color.WHITE);
        inputTextfield.setFont(new Font("SansSerif", Font.BOLD, 18));

        //portTextfield
        portTextfield = new JTextField();
        portTextfield.setBounds(100,105,200,35);
        portTextfield.setBackground(Color.BLACK);
        portTextfield.setBorder(new LineBorder(Color.CYAN,1));
        portTextfield.setForeground(Color.WHITE);
        portTextfield.setFont(new Font("SansSerif", Font.BOLD, 18));

        //adressTextfield
        adressTextfield = new JTextField();
        adressTextfield.setBounds(130,190,200,35);
        adressTextfield.setBackground(Color.BLACK);
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


        easterButton = new JButton("");
        easterButton.setBounds(550,320, 30,30);
        easterButton.setBackground(Color.RED);


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
        int port = -1;
        String adress = adressTextfield.getText();
        try{
            port = Integer.parseInt(portTextfield.getText());
        }catch (NumberFormatException error){
            port = -1;
            System.out.println("Nombre nécessaire");
        }

        if(Pattern.matches(IPV4, adress) == false){
            adress = "";
            System.out.println("Adresse IP non valide");
        }
    }

    public void paintComponent(Graphics g) {

        // Draw the background image.
        g.drawImage(starkLogoImage, (int) (Pointeuse.View.Window.WIDTH *0.74f), (int) (Pointeuse.View.Window.HEIGHT *0.81f), 140,55,this);


        if(epileptique == false){
            Color t = new Color((int) (Math.random() * 255),(int) (Math.random() * 255),(int)(Math.random() * 255));
            setBackground(t);
        }

        super.paintComponent(g);

    }

}
