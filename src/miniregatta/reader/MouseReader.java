package miniregatta.reader;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import miniregatta.main.Main;
import miniregatta.settings.Settings;

public class MouseReader implements MouseMotionListener {
    int positionX = 0;
    int positionY = 0;

    @Override
    public void mouseDragged(MouseEvent e) {
        positionX = e.getX();
        positionY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        positionX = e.getX();
        positionY = e.getY();
    }

    public int getPositionX() {
        Dimension windowSize = Main.getDisplay().getDisplayPanel().getWindowSize();
        float scale = Main.getDisplay().getDisplayPanel().getScale();

        return Math.round((positionX - (windowSize.width - Settings.windowWidth * scale) / 2) / scale);
    }

    public int getPositionY() {
        Dimension windowSize = Main.getDisplay().getDisplayPanel().getWindowSize();
        float scale = Main.getDisplay().getDisplayPanel().getScale();

        return Math.round((positionY - (windowSize.height - Settings.windowHeight * scale) / 2) / scale);
    }
}
