package display;

import javax.swing.JFrame;

import settings.*;

import reader.*;

public class Display {
    JFrame window = new JFrame();
    DisplayPanel displayPanel = new DisplayPanel();
    KeyReader keyReader = new KeyReader();
    ButtonReader buttonReader = new ButtonReader();
    MouseReader mouseReader = new MouseReader();
    
    public Display() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Settings.windowWidth, Settings.windowHeight);
        window.setLocationRelativeTo(null);
        window.setTitle(Settings.title);
        window.add(displayPanel);
        window.addKeyListener(keyReader);
        window.addMouseListener(buttonReader);
        window.addMouseMotionListener(mouseReader);
        window.setFocusable(true);
        window.setVisible(true);
    }

    public void tick() {
        displayPanel.updateUI();
    }

    public JFrame getWindow() {
        return window;
    }

    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    public KeyReader getKeyReader() {
        return keyReader;
    }

    public ButtonReader getButtonReader() {
        return buttonReader;
    }

    public MouseReader getMouseReader() {
        return mouseReader;
    }
}
