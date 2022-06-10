import Pointeuse.Model.FileManipulatorCheck;
import Pointeuse.Model.SettingsCheck;
import Pointeuse.View.Window;
import StarkManagement.Model.FileManipulator;
import StarkManagement.Model.Setting;
import StarkManagement.View.MainWindow;

public class Main {

    public static void main(String[] args) {

        /*Setting s = new Setting("localhost", 25565);
        SettingsCheck sPointeuse = new SettingsCheck("localhost", 25565);

        FileManipulator.exportPointeuseSetting(s);
        FileManipulatorCheck.exportSetting(sPointeuse);*/

        new Thread(() -> new MainWindow()).start();
        new Thread(() -> new Window()).start();

    }
}
