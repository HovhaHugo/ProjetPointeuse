import Pointeuse.Model.FileManipulatorCheck;
import Pointeuse.Model.SettingsCheck;
import Pointeuse.View.Window;
import StarkManagement.Model.*;
import StarkManagement.View.MainWindow;

public class Main {

    public static void main(String[] args) {

        /*Company d = new Company();
        d.setNameCompany("Stark Industries");
        Department de = new Department("Informatique");
        de.getListEmployee().add(new Employee("Jean","Mi",de));
        de.getListEmployee().add(new Employee("Jean","M2",de));
        d.getListDepartment().add(de);

        FileManipulator.exportCompany(d);*/


        /*Setting s = new Setting("localhost", 25565);
        SettingsCheck sPointeuse = new SettingsCheck("localhost", 25565);

        FileManipulator.exportPointeuseSetting(s);
        FileManipulatorCheck.exportSetting(sPointeuse);*/

        new Thread(() -> new Window()).start();
        new Thread(() -> new MainWindow()).start();

    }
}
