package miniregatta.display;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import miniregatta.reader.*;
import miniregatta.settings.*;

public class Display implements Runnable {
    JFrame window = new JFrame();
    DisplayPanel displayPanel = new DisplayPanel();
    KeyReader keyReader = new KeyReader();
    ButtonReader buttonReader = new ButtonReader();
    MouseReader mouseReader = new MouseReader();
    
    @Override
    public void run() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Settings.windowWidth, Settings.windowHeight);
        window.setLocationRelativeTo(null);
        window.setTitle(Settings.title);
        try {
            window.setIconImage(ImageIO.read(getClass().getResource("/resources/textures/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
