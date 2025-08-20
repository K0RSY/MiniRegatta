package reader;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.Main;
import settings.Settings;

public class ButtonReader implements MouseListener {
    int clickX = 0;
    int clickY = 0;
    boolean pressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clickX = e.getX();
        clickY = e.getY();
        pressed = false;
    }

    public int getClickX() {
        Dimension windowSize = Main.getDisplay().getDisplayPanel().getWindowSize();
        float scale = Main.getDisplay().getDisplayPanel().getScale();

        return Math.round((clickX - (windowSize.width - Settings.windowWidth * scale) / 2) / scale);
    }

    public int getClickY() {
        Dimension windowSize = Main.getDisplay().getDisplayPanel().getWindowSize();
        float scale = Main.getDisplay().getDisplayPanel().getScale();

        return Math.round((clickY - (windowSize.height - Settings.windowHeight * scale) / 2) / scale);
    }

    public boolean getPressed() {
        return pressed;
    }
}
