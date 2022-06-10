package StarkManagement.Model;

import Pointeuse.Model.Settings;

import java.io.*;

public class FileManipulator {

    public static Setting importPointeuseSetting(){

        Setting settings = new Setting("192.168.0.0",1);

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializable/pointeuseSettings.dat"))
        ){
            settings = (Setting) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return settings;

    }

    public static void exportPointeuseSetting(Setting settings){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/serializable/pointeuseSettings.dat"))
        ){
            output.writeObject(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
