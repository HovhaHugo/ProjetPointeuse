package StarkManagement;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainScene extends JPanel {

    Image backgroundImage;
    Image test;
    Image stark;
    Image wifi;
    Image meteo;

    JLabel heure;
    JLabel ville;
    JLabel temperature;
    JLabel heureEnviron;

    JTextField textField;
    JLabel btn;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();

    int tick;
    private final int tickrate = 200;

    public MainScene() throws IOException {

        setLayout(null);

        /*backgroundImage = ImageIO.read(new File("img.png"));
        stark = ImageIO.read(new File("stark.png"));

        wifi = ImageIO.read(new File("wifi.png"));
        meteo = ImageIO.read(new File("meteo.jpg"));

        test = Toolkit.getDefaultToolkit().createImage("jarvis.gif");*/

        ville = new JLabel("Tours");
        ville.setForeground(Color.WHITE);
        ville.setBounds(365, 16, 100,50);
        ville.setFont(new Font("SansSerif", Font.BOLD, 27));

        temperature = new JLabel("21°C");
        temperature.setForeground(Color.WHITE);
        temperature.setBounds(263, 85, 100,50);
        temperature.setFont(new Font("SansSerif", Font.BOLD, 20));

        heure = new JLabel(dtf.format(now));
        heure.setForeground(Color.WHITE);
        heure.setBounds(85, 145, 150,50);
        heure.setFont(new Font("SansSerif", Font.BOLD, 42));

        heureEnviron = new JLabel();
        heureEnviron.setForeground(Color.WHITE);
        heureEnviron.setBounds(73, 190, 150,50);
        heureEnviron.setFont(new Font("SansSerif", Font.BOLD, 30));

        textField = new JTextField();
        textField.setBounds(45,275,200,35);
        textField.setBackground(Color.DARK_GRAY);
        textField.setBorder(new LineBorder(Color.CYAN,1));
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("SansSerif", Font.BOLD, 18));

        btn = new JLabel();
        /*Image a = ImageIO.read(new File("button_check.png"));
        a = a.getScaledInstance(60,35,Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(a));*/
        btn.setBounds(265, 267, 150,50);
        btn.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Vérification donnée de "+textField.getText());
            }
        });

        add(btn);
        add(textField);
        add(heureEnviron);
        add(heure);
        add(temperature);
        add(ville);

        update();


    }

    private void update(){

        tick = 0;
        now = LocalDateTime.now();
        heure.setText(dtf.format(now));

        int heure = now.getHour();
        int minute = now.getMinute();

        while(minute%15!=0){
            minute++;
            if(minute==60){
                minute = 0;
                heure++;
                if(heure==24)
                    heure=00;
            }
        }
        String heureString="";
        String minuteString="";
        if(minute<10){
            minuteString+="0";
        }
        minuteString +=minute;

        if(heure<10){
            heureString+="0";
        }
        heureString +=heure;

        heureEnviron.setText("~"+heureString+" : "+minuteString);



    }

    class RoundBtn implements Border {
        private int r;

        RoundBtn(int r) {
            this.r = r;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.r + 1, this.r + 1, this.r + 2, this.r);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, r, r);
        }
    }

    public void paintComponent(Graphics g) {

        tick++;
        if(tick == tickrate)
            update();

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, Window.WIDTH, Window.HEIGHT, this);
        g.drawImage(test, (int) (Window.WIDTH*0.5f), (int) (Window.HEIGHT*0.16f), 230,230,this);
        g.drawImage(stark, (int) (Window.WIDTH*0.74f), (int) (Window.HEIGHT*0.81f), 140,55,this);

        g.drawImage(meteo, (int) (Window.WIDTH*0.5f), (int) (Window.HEIGHT*0.15f), 40,40,this);
        g.drawImage(wifi, (int) (Window.WIDTH*0.82f), (int) (Window.HEIGHT*0.15f), 40,40,this);


        super.paintComponent(g);

    }

}
