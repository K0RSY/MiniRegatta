package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.Main;
import scenes.objects.ObjectTemplate;
import settings.Settings;

public class DisplayPanel extends JPanel {
    BufferedImage drawingSurface = new BufferedImage(
        Settings.windowWidth,
        Settings.windowHeight,
        BufferedImage.TYPE_INT_ARGB
    );
    Graphics2D drawingSurfaceG2 = drawingSurface.createGraphics();

    public DisplayPanel() {
        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    public void drawImage(String pathToImage, int positionX, int positionY) {
        try {
            Image image = ImageIO.read(new File(pathToImage));

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

        Dimension windowSize = Main.getDisplay().getWindow().getSize();
        float scale = Math.min(
            (float) windowSize.width / Settings.windowWidth,
            (float) windowSize.height / Settings.windowHeight
        );

        for (ObjectTemplate object : Main.getCurrentScene().getObjects()) {
            object.draw();
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
}
