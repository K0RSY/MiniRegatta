package reader;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonReader implements MouseListener {
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
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }

    public boolean getPressed() {
        return pressed;
    }
}
