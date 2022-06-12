package StarkManagement.Model;

import java.io.*;
import java.util.ArrayList;
/**
 * Class to save/import the data of the compagny and the settings
 */
public class FileManipulator {
    /**
     * Method to import the setting of the main App form a serialized file
     * @return settings
     */
    public static Settings importMainAppSetting(){

        Settings settings = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(
                "data"+File.separator+"serializable"+File.separator+"settingsMainApp.dat"))
        ){
            settings = (Settings) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return settings;
    }
    /**
     * Method to export the setting of the main App to a serialized file
     */
    public static void exportMainAppSetting(Settings settings){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(
                "data"+File.separator+"serializable"+File.separator+"settingsMainApp.dat"))
        ){
            output.writeObject(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to export the compagny's data into a serialized file
     */

    public static void exportCompany(Company company){

        try(FileOutputStream fos = new FileOutputStream(
                "data"+File.separator+"serializable"+File.separator+"company.dat");
            ObjectOutputStream output = new ObjectOutputStream(fos)) {

            output.writeObject(company);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * Method to import the compagny's data from a serialized file
     * @return  company
     */

    public static Company importCompany(){

        Company company = null;

        try( FileInputStream file = new FileInputStream(
                "data"+File.separator+"serializable"+File.separator+"company.dat");
             ObjectInputStream in = new ObjectInputStream(file)) {

            // Method for deserialization of object
            company = (Company) in.readObject();

        }

        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return company;
    }

}



