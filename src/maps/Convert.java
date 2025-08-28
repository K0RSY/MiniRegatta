package maps;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import maps.data.MapInput;
import maps.data.MapTemplate;
import settings.Settings;

public class Convert {
    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream(Settings.mapPath + "one_loop.dat");
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
