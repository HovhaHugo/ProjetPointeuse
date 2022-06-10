package StarkManagement.Model;

import Pointeuse.Model.Settings;

import java.io.*;
import java.util.ArrayList;

public class FileManipulator {

    public static Setting importMainAppSetting(){

        Setting settings = new Setting("192.168.0.0",1);

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data/serializable/mainAppSettings.dat"))
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

    public static void exportCompany(Company company){

        try(FileOutputStream fos = new FileOutputStream("data/serializable/company.dat");
            ObjectOutputStream output = new ObjectOutputStream(fos)) {

            output.writeObject(company);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public static Company importCompany(){

        ArrayList<Department> listeDepartment= new ArrayList<Department>();
        Company company = new Company("Stark Industry",listeDepartment );

        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream("data/serializable/company.dat");
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            company = (Company) in.readObject();

            in.close();
            file.close();


        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }

        return company;
    }


}
