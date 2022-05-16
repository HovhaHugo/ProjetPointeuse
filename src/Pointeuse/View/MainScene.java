package Pointeuse.View;

import Pointeuse.Controller.Hours;
import Pointeuse.Controller.PersonnShort;
import Pointeuse.Controller.ScoreShort;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MainScene extends JPanel {

    Image backgroundImage;
    Image jarvisGif;
    Image starkLogoImage;
    Image wifiImage;
    Image weatherImage;

    JLabel currentHourLabel;
    JLabel cityLabel;
    JLabel temperatureLabel;
    JLabel roundedHourLabel;

    JTextField inputTextfield;
    LineBorder inputTextFieldBorder;
    JLabel checkButton;

    Hours currentHours;
    Hours hoursRounded;

    int tick;
    private final static int tickrate = 30;     //Je ne sais pas quel est le fps de base. Ici 30 ticks = environ 4 ou 5s

    //Error animation
    int tickErrorAnimation = 0;
    int tickBlink = 0;
    final int tickBlinkLenght = 1;
    final int tickErrorAnimationLenght = 14;

    public MainScene() throws IOException {

        setLayout(null);

        currentHours = new Hours();
        hoursRounded = new Hours();

        backgroundImage = ImageIO.read(new File("data/img/img.png"));
        starkLogoImage = ImageIO.read(new File("data/img/stark.png"));

        wifiImage = ImageIO.read(new File("data/img/wifi.png"));
        weatherImage = ImageIO.read(new File("data/img/meteo.jpg"));

        jarvisGif = Toolkit.getDefaultToolkit().createImage("data/img/jarvis.gif");

        cityLabel = new JLabel("Tours");
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setBounds(365, 16, 100,50);
        cityLabel.setFont(new Font("SansSerif", Font.BOLD, 27));

        temperatureLabel = new JLabel("21°C");
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setBounds(263, 85, 100,50);
        temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        currentHourLabel = new JLabel(currentHours.toString());
        currentHourLabel.setForeground(Color.WHITE);
        currentHourLabel.setBounds(85, 145, 150,50);
        currentHourLabel.setFont(new Font("SansSerif", Font.BOLD, 42));

        roundedHourLabel = new JLabel();
        roundedHourLabel.setForeground(Color.WHITE);
        roundedHourLabel.setBounds(82, 190, 150,50);
        roundedHourLabel.setFont(new Font("SansSerif", Font.BOLD, 30));

        inputTextfield = new JTextField();
        inputTextfield.setBounds(45,275,200,35);
        inputTextfield.setBackground(Color.DARK_GRAY);
        inputTextFieldBorder = new LineBorder(Color.CYAN,1);
        inputTextfield.setBorder(inputTextFieldBorder);
        inputTextfield.setForeground(Color.WHITE);
        inputTextfield.setFont(new Font("SansSerif", Font.BOLD, 17));

        checkButton = new JLabel();
        Image a = ImageIO.read(new File("data/img/button_check.png"));
        a = a.getScaledInstance(60,35,Image.SCALE_SMOOTH);
        checkButton.setIcon(new ImageIcon(a));
        checkButton.setBounds(265, 267, 150,50);

        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                checkText();
            }
        });

        add(checkButton);
        add(inputTextfield);
        add(roundedHourLabel);
        add(currentHourLabel);
        add(temperatureLabel);
        add(cityLabel);

        update();

    }

    private void update(){

        //Error animation
        if(tickErrorAnimation>0){
            tickErrorAnimation++;
            if(tickErrorAnimation==tickErrorAnimationLenght){
                tickErrorAnimation=0;
                inputTextFieldBorder = new LineBorder(Color.cyan,1);
                inputTextfield.setBorder(inputTextFieldBorder);
                return;
            }
            tickBlink++;
            if(tickBlink==tickBlinkLenght){
                tickBlink=0;
                if(inputTextFieldBorder.getLineColor()==Color.CYAN)
                    inputTextFieldBorder = new LineBorder(Color.RED,2);
                else
                    inputTextFieldBorder = new LineBorder(Color.CYAN,1);
                inputTextfield.setBorder(inputTextFieldBorder);
            }
        }

        if(tick == tickrate){
            tick = 0;
            currentHours.update();
            hoursRounded.update();
            hoursRounded.roundNextQuarter();

            currentHourLabel.setText(currentHours.toString());
            roundedHourLabel.setText("~"+hoursRounded.toString());

            if(currentHours.getMinutes() == 0){
                if(ScoreShort.isTestSendThisHour() == false){
                    ScoreShort.sendAllTemp();
                    ScoreShort.setTestSendThisHour(true);
                }
            }
            if(currentHours.getMinutes() == 1 && ScoreShort.isTestSendThisHour() == true){
                ScoreShort.setTestSendThisHour(false);
            }

        }

    }

    private void checkText(){
        System.out.println("Vérification donnée de "+ inputTextfield.getText());
        int id = -1;
        String identity = "";
        PersonnShort exist = null;

        try{
            id = Integer.parseInt(inputTextfield.getText());
            exist = PersonnShort.getPersonn(id);

        }catch (NumberFormatException error){
            id = -1;
        }

        if(id==-1){
            identity = inputTextfield.getText();
            exist = PersonnShort.getPersonn(identity);
        }

        if(exist!=null){
            new ScoreShort(exist, new Hours(currentHours));
        }else{
            inputTextfield.setText("Non reconnu");
            tickErrorAnimation++;
        }

    }

    public void paintComponent(Graphics g) {

        tick++;
        update();

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, Window.WIDTH,  Window.HEIGHT, this);
        g.drawImage(jarvisGif, (int) (Window.WIDTH *0.5f), (int) (Window.HEIGHT *0.16f), 230,230,this);
        g.drawImage(starkLogoImage, (int) (Window.WIDTH *0.74f), (int) (Window.HEIGHT *0.81f), 140,55,this);

        g.drawImage(weatherImage, (int) (Window.WIDTH *0.5f), (int) (Window.HEIGHT *0.15f), 40,40,this);
        g.drawImage(wifiImage, (int) (Window.WIDTH *0.82f), (int) (Window.HEIGHT *0.15f), 40,40,this);

        super.paintComponent(g);

    }

}
