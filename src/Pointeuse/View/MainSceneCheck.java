package Pointeuse.View;

import Pointeuse.Controller.HoursCheck;
import Pointeuse.Controller.PersonnShortCheck;
import Pointeuse.Controller.ScoreShortCheck;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MainSceneCheck extends JPanel {

    private Image backgroundImage;
    private Image jarvisGif;
    private Image starkLogoImage;
    private Image wifiImage;
    private Image weatherImage;

    private JLabel currentHourLabel;
    private JLabel cityLabel;
    private JLabel temperatureLabel;
    private JLabel roundedHourLabel;

    private JComboBox combobox;
    private LineBorder inputTextFieldBorder;

    private JLabel checkButton;
    private JLabel settingsButton;
    private JLabel exitButton;

    private HoursCheck currentHours;
    private HoursCheck hoursRounded;

    private final static int tickrate = 70; //environ 10 secondes
    private int tick = tickrate;

    //Error animation

    private int tickErrorAnimation = 0;
    private int tickBlink = 0;
    private final int tickBlinkLenght = 1;
    private final int tickErrorAnimationLenght = 14;

    private Window ownerWindow;

    /**
     * Constructor of the MainScene panel
     * @param pOwner the owner window (to call the switch panel function)
     * @throws IOException if an image was not found
     */
    public MainSceneCheck(Window pOwner) throws IOException {

        setLayout(null);
        setBackground(new Color(0,0,0,0));

        ownerWindow = pOwner;

        currentHours = new HoursCheck();
        hoursRounded = new HoursCheck();


        //Image loading
        backgroundImage = ImageIO.read(new File("data/img/img.png"));
        starkLogoImage = ImageIO.read(new File("data/img/stark.png"));

        wifiImage = ImageIO.read(new File("data/img/wifi.png"));
        weatherImage = ImageIO.read(new File("data/img/meteo.jpg"));

        jarvisGif = Toolkit.getDefaultToolkit().createImage("data/img/jarvis.gif");


        //City
        cityLabel = new JLabel("Tours");
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setBounds(365, 16, 100,50);
        cityLabel.setFont(new Font("SansSerif", Font.BOLD, 27));

        //Temperature
        temperatureLabel = new JLabel("21°C");
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setBounds(263, 85, 100,50);
        temperatureLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        //CurrentHout
        currentHourLabel = new JLabel(currentHours.toString());
        currentHourLabel.setForeground(Color.WHITE);
        currentHourLabel.setBounds(85, 145, 150,50);
        currentHourLabel.setFont(new Font("SansSerif", Font.BOLD, 42));

        //RoundedHour
        roundedHourLabel = new JLabel();
        roundedHourLabel.setForeground(Color.WHITE);
        roundedHourLabel.setBounds(82, 190, 150,50);
        roundedHourLabel.setFont(new Font("SansSerif", Font.BOLD, 30));

        //Combobox
        combobox = new JComboBox(PersonnShortCheck.getPersonnListString().toArray());
        combobox.setEditable(true);
        combobox.setMaximumRowCount(5);
        combobox.setSelectedIndex(-1);
        combobox.setBounds(45,275,200,35);
        combobox.setBackground(Color.DARK_GRAY);
        inputTextFieldBorder = new LineBorder(Color.CYAN,1,true);
        combobox.setBorder(inputTextFieldBorder);
        combobox.setForeground(Color.WHITE);
        combobox.setFont(new Font("SansSerif", Font.BOLD, 17));

        //Check Button
        checkButton = new JLabel();
        Image checkImage = ImageIO.read(new File("data/img/button_check.png"));
        checkImage = checkImage.getScaledInstance(60,35,Image.SCALE_SMOOTH);
        checkButton.setIcon(new ImageIcon(checkImage));
        checkButton.setBounds(265, 267, 150,50);

        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                checkText();
            }
        });

        //Settings button
        settingsButton = new JLabel();
        Image settingImg = ImageIO.read(new File("data/img/settings.png"));
        settingImg = settingImg.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        settingsButton.setIcon(new ImageIcon(settingImg));
        settingsButton.setBounds(510, 180, 100,100);

        settingsButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ownerWindow.setSettingsScene();
            }
        });

        //Exit button
        exitButton = new JLabel();
        Image exitImage = ImageIO.read(new File("data/img/exit.png"));
        exitImage = exitImage.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(exitImage));
        exitButton.setBounds(510, 110, 100,100);

        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ownerWindow.close();
            }
        });

        //Add the elements to the panel
        add(exitButton);
        add(settingsButton);
        add(checkButton);
        add(combobox);
        add(roundedHourLabel);
        add(currentHourLabel);
        add(temperatureLabel);
        add(cityLabel);

        update();

    }

    /**
     * Force the update of the window
     */
    public void forceUpdate(){
        tick = tickrate;
        update();
    }

    /**
     * Update some values in the windows, depending of the current tick value
     * Used for the error animation and the quarter-rounded hour
     */
    private void update(){

        tick++;

        //Error animation
        if(tickErrorAnimation>0){
            tickErrorAnimation++;
            if(tickErrorAnimation==tickErrorAnimationLenght){
                tickErrorAnimation=0;
                inputTextFieldBorder = new LineBorder(Color.cyan,1,true);
                combobox.setBorder(inputTextFieldBorder);
                return;
            }
            tickBlink++;
            if(tickBlink==tickBlinkLenght){
                tickBlink=0;
                if(inputTextFieldBorder.getLineColor()==Color.CYAN)
                    inputTextFieldBorder = new LineBorder(Color.RED,2,true);
                else
                    inputTextFieldBorder = new LineBorder(Color.CYAN,1,true);
                combobox.setBorder(inputTextFieldBorder);
            }
        }

        //rounded hours
        if(tick >= tickrate){

            tick = 0;
            currentHours.update();
            hoursRounded.update();
            hoursRounded.roundNextQuarter();

            currentHourLabel.setText(currentHours.toString());
            roundedHourLabel.setText("~"+hoursRounded.toString());

            if(currentHours.getMinutes() % ownerWindow.minutesBetweentest == 0){
                if(ownerWindow.isTestSendDone() == false && ScoreShortCheck.getScoreList().isEmpty()==false){
                    ownerWindow.sendAllScore();
                    ownerWindow.setTestSendDone(true);
                }
            }
            if(currentHours.getMinutes() == 1 && ownerWindow.isTestSendDone() == true){
                ownerWindow.setTestSendDone(false);
            }

        }

    }

    /**
     * Check if the entered text matches someone existing
     * Create the score if the person is identified
     */
    private void checkText(){

        String text = (String)combobox.getEditor().getItem();
        if(text.equals("") || !ScoreShortCheck.isDayValid()){
            tickErrorAnimation++;
            return;
        }

        System.out.println(text);

        PersonnShortCheck exist = null;

        String extractId = text.replaceAll("[^0-9]", "");
        String extractName = text.replaceAll("[^a-zA-Z].*", "");

        if(!extractId.equals("")){
            exist = PersonnShortCheck.getPersonn(Integer.parseInt(extractId));
        }
        if(exist==null)
            exist = PersonnShortCheck.getPersonn(extractName);

        if(exist!=null){
            new ScoreShortCheck(exist, new HoursCheck(currentHours));
            ownerWindow.sendAllScore();
        }else{
            combobox.setSelectedIndex(-1);
            tickErrorAnimation++;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        update();

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, Window.WIDTH,  Window.HEIGHT, this);
        g.drawImage(jarvisGif, (int) (Window.WIDTH *0.5f), (int) (Window.HEIGHT *0.16f), 230,230,this);
        g.drawImage(starkLogoImage, (int) (Window.WIDTH *0.74f), (int) (Window.HEIGHT *0.81f), 140,55,this);

        g.drawImage(weatherImage, (int) (Window.WIDTH *0.5f), (int) (Window.HEIGHT *0.15f), 40,40,this);
        g.drawImage(wifiImage, (int) (Window.WIDTH *0.82f), (int) (Window.HEIGHT *0.15f), 40,40,this);

    }

}
