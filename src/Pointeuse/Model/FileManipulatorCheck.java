package Pointeuse.Model;

import java.io.*;

public class FileManipulatorCheck {

    /**
     * Import the stored settings, serialized in a file
     * @return the settings stored
     */
    public static SettingsCheck importSetting(){

        SettingsCheck settings = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializable/settingsPointeuse.dat"))
        ){
            settings = (SettingsCheck) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return settings;

    }

    /**
     * Export settings and store it in a file
     * @param settings the settings to export
     */
    public static void exportSetting(SettingsCheck settings){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/serializable/settingsPointeuse.dat"))
        ){
            output.writeObject(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
