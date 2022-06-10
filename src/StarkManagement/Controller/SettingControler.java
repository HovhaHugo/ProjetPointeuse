package StarkManagement.Controller;

import StarkManagement.Model.FileManipulator;
import StarkManagement.Model.Setting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SettingControler {
    static Setting server = new Setting("localhost", 8000);
    static Setting pointeuse = new Setting("localhost", 8000);

    /**public static void main (String[] args) {

        lancerServeur();

        lancerClient();

    }*/

    public static void lancerClient(){
        System.out.println("Lancement du client.");

    }

    public static void lancerServeur(){
        System.out.println("Lancement du serveur.");

    }

    public static String getPointeuseAdress(){
        return pointeuse.getIp();
    }

    public static int getPointeusePort(){
        return pointeuse.getPort();
    }

    public static void setSettings(int newPort, String newAdress){
        pointeuse.setPort(newPort);
        pointeuse.setIp(newAdress);
    }

    public static void exporteSetting(){
        FileManipulator.exportPointeuseSetting(pointeuse);
    }

    public static void importeSetting(){
        pointeuse = FileManipulator.importMainAppSetting();
    }
}
