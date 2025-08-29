package miniregatta.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import miniregatta.main.Main;
import miniregatta.scenes.objects.ObjectTemplate;
import miniregatta.settings.Settings;

public class DisplayPanel extends JPanel {
    BufferedImage drawingSurface = new BufferedImage(
        Settings.windowWidth,
        Settings.windowHeight,
        BufferedImage.TYPE_INT_ARGB
    );
    Graphics2D drawingSurfaceG2 = drawingSurface.createGraphics();
    float scale = 0;
    Dimension windowSize = new Dimension();

    public DisplayPanel() {
        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    public void drawImage(String pathToImage, int positionX, int positionY) {
        try {
            URL imageURL = getClass().getResource(pathToImage);
            Image image = ImageIO.read(imageURL);

            drawingSurfaceG2.drawImage(
                image,
                positionX - image.getWidth(null) / 2,
                positionY - image.getHeight(null) / 2,
                image.getWidth(null),
                image.getWidth(null),
                null
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        windowSize = Main.getDisplay().getWindow().getSize();
        scale = Math.min(
            (float) windowSize.width / Settings.windowWidth,
            (float) windowSize.height / Settings.windowHeight
        );

        LinkedHashMap<String, ArrayList<ObjectTemplate>> objects = Main.getCurrentScene().getObjects();

        ArrayList<String> objectKeys = new ArrayList<String>(objects.keySet());

        for (String objectKey : objectKeys) {
            try {
                for (int i = 0; i < objects.get(objectKey).size(); i++) {
                    objects.get(objectKey).get(i).draw();
                }
            } catch (java.lang.NullPointerException e) {
            }
        }

        g2.drawImage(
            drawingSurface,
            Math.round((windowSize.width - Settings.windowWidth * scale) / 2), 
            Math.round((windowSize.height - Settings.windowHeight * scale) / 2),
            Math.round(Settings.windowWidth * scale),
            Math.round(Settings.windowHeight * scale),
            null
        );
    }

    public float getScale() {
        return scale;
    }

    public Dimension getWindowSize() {
        return windowSize;
    }
}
