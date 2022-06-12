package Pointeuse.Model;

import Common.ScoreShort;

import java.io.*;
import java.util.ArrayList;
/**
 * Class to import/export the data from seizalized files for the "pointeuse"
 */
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

    /**
     * Import the stored temporary score whose sending had failed, serialized in a file
     * @return the settings stored
     */
    public static ArrayList<ScoreShort> importRemainingScoreShort(){

        ArrayList<ScoreShort> remain = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializable/checkScoreRemaining.dat"))
        ){
            remain = (ArrayList<ScoreShort>) input.readObject();
        }catch(EOFException ignored){

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return remain;

    }

    /**
     * Export the temporary scores whose sending had failed and store it in a file
     * @param remain the scores to export
     */
    public static void exportRemainingScoreShort(ArrayList<ScoreShort> remain){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data/serializable/checkScoreRemaining.dat"))
        ){
            output.writeObject(remain);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
