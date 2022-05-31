package Pointeuse.View;

import Pointeuse.Model.FileManipulator;
import Pointeuse.Model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Window extends JFrame {

    private MainScene mainScene;
    private SettingsScene settingsScene;

    public final static int WIDTH = 580;
    public final static int HEIGHT = 350;

    static int positionX;
    static int positionY;

    Settings settings;

    public Window(){


        this.setSize(WIDTH, HEIGHT);

        try {
            settings = FileManipulator.importSetting();
            this.setContentPane(mainScene =new MainScene(this));
            settingsScene = new SettingsScene(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void close(){
        FileManipulator.exportSetting(settings);
        dispose();
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettingsScene(){
        this.setContentPane(settingsScene);
        validate();
    }

    public void setMainScene(){
        this.setContentPane(mainScene);
        validate();
        mainScene.forceUpdate();
        settingsScene.clear();
        settingsScene.save();
    }

    static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
            System.out.println(e.getX()+" "+e.getY());
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            positionX = currCoords.x - mouseDownCompCoords.x;
            positionY = currCoords.y - mouseDownCompCoords.y;
            frame.setLocation(positionX,positionY);
        }
    }



}
