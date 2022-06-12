package Common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileManipulatorCommon {

    public static Image getImage(String nameAndFormat){
        try {
            return ImageIO.read(new File("data"+File.separator+"img"+File.separator+""+nameAndFormat));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Image getGif(String nameAndFormat){
        return Toolkit.getDefaultToolkit().createImage("data"+File.separator+"img"+File.separator+""+nameAndFormat);
    }
}
