package miniregatta.maps;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import miniregatta.maps.data.MapInput;
import miniregatta.maps.data.MapTemplate;
import miniregatta.settings.Settings;

public class Convert {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("src" + Settings.mapPath + "tango.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            MapTemplate data = new MapInput();

            oos.writeObject(data);

            oos.close();
            bos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
