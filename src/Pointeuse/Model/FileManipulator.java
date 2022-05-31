package Pointeuse.Model;

import java.io.*;

public class FileManipulator {

    public static Settings importSetting(){

        Settings settings = new Settings("192.168.0.0",1);

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializable/settings.dat"))
        ){
            settings = (Settings) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return settings;

    }

    public static void exportSetting(Settings settings){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/serializable/settings.dat"))
        ){
            output.writeObject(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
