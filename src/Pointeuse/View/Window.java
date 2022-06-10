package Pointeuse.View;

import Pointeuse.Controller.ScoreShortCheck;
import Pointeuse.Model.FileManipulatorCheck;
import Pointeuse.Model.SettingsCheck;
import Pointeuse.Model.TCPClientCheck;
import Pointeuse.Model.TCPServerCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Check application window
 * Can switch between the mainScene panel and the settingsScene panel
 */
public class Window extends JFrame {

    private MainSceneCheck mainScene;
    private SettingsSceneCheck settingsScene;

    public final static int WIDTH = 580;
    public final static int HEIGHT = 350;

    static int positionX;
    static int positionY;

    TCPServerCheck server;
    TCPClientCheck client;

    SettingsCheck settings;

    private boolean testSendThisHour = false;

    /**
     * Constructor of the window
     * Import the serialized settings
     */
    public Window(){

        this.setSize(WIDTH, HEIGHT);

        try {
            settings = FileManipulatorCheck.importSetting();
            this.setContentPane(mainScene =new MainSceneCheck(this));
            settingsScene = new SettingsSceneCheck(this);

            new Thread(server = new TCPServerCheck()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
               close();
            }
        });
        this.setVisible(true);

    }

    /**
     * Send with TCP to the server all the temporary ScoreShort stocked
     * Must be called regularly to empty the cache filled
     * with previous send failures
     */
    public void sendAllScore(){
        ArrayList<ScoreShortCheck> scores = ScoreShortCheck.getScoreList();
        new Thread(client = new TCPClientCheck(scores,settings));
    }
    public void setTestSendThisHour(boolean t) {
        testSendThisHour = t;
    }

    public boolean isTestSendThisHour() {
        return testSendThisHour;
    }

    /**
     * Export Close the application
     */
    public void close(){
        server.shutdown();
        FileManipulatorCheck.exportSetting(settings);
        dispose();
    }

    /**
     * getter of the application settings
     * @return the settings
     */
    public SettingsCheck getSettings() {
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
