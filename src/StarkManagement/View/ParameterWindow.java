package StarkManagement.View;

import StarkManagement.View.SettingView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ParameterWindow extends JFrame {

    SettingView scene;

    public final static int WIDTH = 580;
    public final static int HEIGHT = 350;

    static int positionX;
    static int positionY;


    public ParameterWindow() {

        this.setSize(WIDTH, HEIGHT);

        try {
            this.setContentPane(scene = new SettingView(this));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 255));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {

            mouseDownCompCoords = e.getPoint();
            System.out.println(e.getX() + " " + e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            positionX = currCoords.x - mouseDownCompCoords.x;
            positionY = currCoords.y - mouseDownCompCoords.y;
            frame.setLocation(positionX, positionY);
        }
    }
}