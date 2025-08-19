package reader;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Reader implements KeyListener {
    ArrayList<Integer> pressedKeyboardKeys = new ArrayList<>();

    @Override
    public void keyPressed(KeyEvent e) {
        if (!pressedKeyboardKeys.contains(e.getKeyCode())) {
            pressedKeyboardKeys.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeyboardKeys.remove((Integer) e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public ArrayList<Integer> getPressedKeyboardKeys() {
        return pressedKeyboardKeys;
    }

    public void getPressedMouseButtons() {

    }
}
