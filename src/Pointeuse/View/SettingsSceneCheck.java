package Pointeuse.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SettingsSceneCheck extends JPanel {

    Image backgroundImage;
    Image starkLogoImage;

    JLabel exitButton;
    JLabel checkButton;

    JLabel passwordLabel;
    JLabel ipLabel;
    JLabel portLabel;

    JTextField passwordTextField;
    JTextField ipTextField;
    JTextField portTextField;

    LineBorder inputTextFieldBorder;

    Window ownerWindow;

    /**
     * Constructor of the SettingsScene panel
     * @param pOwner the owner window
     * @throws IOException if an image was not found (to call the switch panel function)
     */
    public SettingsSceneCheck(Window pOwner) throws IOException {

        setLayout(null);
        setBackground(new Color(0,0,0,0));

        ownerWindow = pOwner;

        backgroundImage = ImageIO.read(new File("data/img/img.png"));
        starkLogoImage = ImageIO.read(new File("data/img/stark.png"));

        exitButton = new JLabel();
        Image settingImg = ImageIO.read(new File("data/img/exit.png"));
        settingImg = settingImg.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        exitButton.setIcon(new ImageIcon(settingImg));
        exitButton.setBounds(510, 180, 100,100);

        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ownerWindow.setMainScene();
            }
        });

        checkButton = new JLabel();
        Image checkImage = ImageIO.read(new File("data/img/button_check.png"));
        checkImage = checkImage.getScaledInstance(60,35,Image.SCALE_SMOOTH);
        checkButton.setIcon(new ImageIcon(checkImage));
        checkButton.setBounds(470, 100, 150,50);

        checkButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(passwordTextField.getText().equals("admin")){
                    inputTextFieldBorder = new LineBorder(Color.CYAN,1,true);
                    ipTextField.setBorder(inputTextFieldBorder);
                    portTextField.setBorder(inputTextFieldBorder);
                    ipTextField.setForeground(Color.WHITE);
                    portTextField.setForeground(Color.WHITE);
                    ipTextField.setEditable(true);
                    portTextField.setEditable(true);
                }
        }});

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(300, 70, 150,50);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 25));

        ipLabel = new JLabel("IP:");
        ipLabel.setForeground(Color.WHITE);
        ipLabel.setBounds(60, 135, 150,50);
        ipLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        portLabel = new JLabel("Port:");
        portLabel.setForeground(Color.WHITE);
        portLabel.setBounds(60, 220, 150,50);
        portLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        inputTextFieldBorder = new LineBorder(Color.red,2,true);

        passwordTextField = new JTextField();
        passwordTextField.setBounds(300,110,160,35);
        passwordTextField.setBackground(Color.DARK_GRAY);
        passwordTextField.setBorder(new LineBorder(Color.CYAN,1));
        passwordTextField.setForeground(Color.WHITE);
        passwordTextField.setFont(new Font("SansSerif", Font.BOLD, 17));

        ipTextField = new JTextField();
        ipTextField.setBounds(45,180,200,35);
        ipTextField.setBackground(Color.DARK_GRAY);
        ipTextField.setBorder(inputTextFieldBorder);
        ipTextField.setForeground(Color.GRAY);
        ipTextField.setFont(new Font("SansSerif", Font.BOLD, 17));
        ipTextField.setEditable(false);
        ipTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isLetter(c) && c != '.') {
                    e.consume();
                }
            }
        });

        portTextField = new JTextField();
        portTextField.setBounds(45,265,200,35);
        portTextField.setBackground(Color.DARK_GRAY);
        portTextField.setBorder(inputTextFieldBorder);
        portTextField.setForeground(Color.GRAY);
        portTextField.setFont(new Font("SansSerif", Font.BOLD, 17));
        portTextField.setEditable(false);
        portTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)==false) {
                    e.consume();
                }
            }
        });

        ipTextField.setText(ownerWindow.getSettings().getIpMainApplication());
        portTextField.setText(ownerWindow.getSettings().getPortMainAppplication()+"");

        add(checkButton);
        add(exitButton);
        add(passwordTextField);
        add(ipTextField);
        add(portTextField);

        add(passwordLabel);
        add(ipLabel);
        add(portLabel);

    }

    /**
     * Update the application settings
     */
    public void save(){
        ownerWindow.getSettings().setIpMainApplication(ipTextField.getText());
        ownerWindow.getSettings().setPortMainAppplication(Integer.parseInt(portTextField.getText()));
    }

    /**
     * Clear the password textfield and reset the ip and port textfields
     */
    public void clear(){
        passwordTextField.setText("");

        inputTextFieldBorder = new LineBorder(Color.red,2,true);
        ipTextField.setBorder(inputTextFieldBorder);
        portTextField.setBorder(inputTextFieldBorder);
        ipTextField.setForeground(Color.gray);
        portTextField.setForeground(Color.gray);
        ipTextField.setEditable(false);
        portTextField.setEditable(false);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, Window.WIDTH,  Window.HEIGHT, this);
        g.drawImage(starkLogoImage, (int) (Window.WIDTH *0.74f), (int) (Window.HEIGHT *0.81f), 140,55,this);

    }


}
