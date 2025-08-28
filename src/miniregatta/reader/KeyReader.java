package miniregatta.reader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyReader implements KeyListener {
    ArrayList<Integer> pressedKeys = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Integer) e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public ArrayList<Integer> getPressedKeys() {
        return pressedKeys;
    }
}
