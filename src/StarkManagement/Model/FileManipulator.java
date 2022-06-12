package StarkManagement.Model;

import java.io.*;
import java.util.ArrayList;

public class FileManipulator {

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

    public static void exportMainAppSetting(Settings settings){

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(
                "data"+File.separator+"serializable"+File.separator+"settingsMainApp.dat"))
        ){
            output.writeObject(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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
