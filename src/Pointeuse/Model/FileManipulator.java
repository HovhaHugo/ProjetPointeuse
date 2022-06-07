package Pointeuse.Model;

import java.io.*;

public class FileManipulator {

    /**
     * Import the stored settings, serialized in a file
     * @return the settings stored
     */
    public static Settings importSetting(){

        Settings settings = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializable/settings.dat"))
        ){
            settings = (Settings) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return settings;

    }

    /**
     * Export settings and store it in a file
     * @param settings the settings to export
     */
    public static void exportSetting(Settings settings){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/serializable/settings.dat"))
        ){
            output.writeObject(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
