package display;

import javax.swing.JFrame;

import settings.*;

import reader.*;

public class Display {
    JFrame window = new JFrame();
    DisplayPanel displayPanel = new DisplayPanel();
    Reader reader = new Reader();
    
    public Display() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Settings.windowWidth, Settings.windowHeight);
        window.setLocationRelativeTo(null);
        window.setTitle(Settings.title);
        window.add(displayPanel);
        window.addKeyListener(reader);
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
}
