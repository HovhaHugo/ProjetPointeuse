package StarkManagement.Controller;

import StarkManagement.Model.FileManipulator;
import StarkManagement.Model.Settings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SettingControler {
    static Settings server = new Settings("localhost", 8000);
    static Settings pointeuse = new Settings("localhost", 8000);

    public static String getPointeuseAdress(){
        return pointeuse.getIp();
    }

    public static int getPointeusePort(){
        return pointeuse.getPort();
    }

    public static void update(int newPort, String newAdress){
        pointeuse.setPort(newPort);
        pointeuse.setIp(newAdress);
    }

    public static void exporteSetting(){
        FileManipulator.exportMainAppSetting(pointeuse);
    }

    public static void importeSetting(){
        pointeuse = FileManipulator.importMainAppSetting();
    }
}
