package StarkManagement.Controller;

import StarkManagement.Model.FileManipulator;
import StarkManagement.Model.Settings;

public class SettingControler {
    /**
     * create an instance of the settings for the Main app
     */
    static Settings pointeuse = new Settings("localhost", 8000);
    /**
     * update the settings of the Main app
     * @param newPort the settings to change
     * @param newAdress the settings to change
     */
    public static void update(int newPort, String newAdress){
        pointeuse.setPort(newPort);
        pointeuse.setIp(newAdress);
    }
    /**
     * Export settings and store it in a file
     */
    public static void exporteSetting(){
        FileManipulator.exportMainAppSetting(pointeuse);
    }

}
