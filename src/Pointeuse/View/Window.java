package Pointeuse.View;

import Pointeuse.Model.FileManipulator;
import Pointeuse.Model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Check application window
 * Can switch between the mainScene panel and the settingsScene panel
 */
public class Window extends JFrame {

    private MainScene mainScene;
    private SettingsScene settingsScene;

    public final static int WIDTH = 580;
    public final static int HEIGHT = 350;

    static int positionX;
    static int positionY;

    Settings settings;

    /**
     * Constructor of the window
     * Import the serialized settings
     */
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

    /**
     * Export Close the application
     */
    public void close(){
        FileManipulator.exportSetting(settings);
        dispose();
    }

    /**
     * getter of the application settings
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Set the current scene to settingsScene
     */
    public void setSettingsScene(){
        this.setContentPane(settingsScene);
        validate();
    }

    /**
     * Set the current scene to mainScene and update it
     */
    public void setMainScene(){
        this.setContentPane(mainScene);
        validate();
        mainScene.forceUpdate();
        settingsScene.clear();
        settingsScene.save();
    }

    /**
     * Allows you to move the window.
     * Function defined manually because we removed the decorations
     * which also allowed the movement of the window
     */
    static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            positionX = currCoords.x - mouseDownCompCoords.x;
            positionY = currCoords.y - mouseDownCompCoords.y;
            frame.setLocation(positionX,positionY);
        }
    }


}
