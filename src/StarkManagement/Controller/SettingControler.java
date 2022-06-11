package StarkManagement.Controller;

import StarkManagement.Model.FileManipulator;
import StarkManagement.Model.Settings;

public class SettingControler {
    static Settings pointeuse = new Settings("localhost", 8000);

    public static void update(int newPort, String newAdress){
        pointeuse.setPort(newPort);
        pointeuse.setIp(newAdress);
    }

    public static void exporteSetting(){
        FileManipulator.exportMainAppSetting(pointeuse);
    }

}
