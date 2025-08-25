package scenes.objects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Main;
import settings.Settings;
import calc.*;

public class Yacht extends ObjectTemplate {
    ArrayList<String> pathToHullImages = new ArrayList<String>();
    ArrayList<String> pathToSailImages = new ArrayList<String>();
    ArrayList<String> pathToWindImages = new ArrayList<String>();

    float precisePositionX;
    float precisePositionY;

    int degreesPerHullImage;
    int degreesPerSailImage;
    int degreesPerWindImage;

    int width = 0;
    int height = 0;

    int hullRotationDegrees = 0;
    int sailRotationDegrees = 0;
    float sailEase = 0;

    int windRotationDegrees;
    float windSpeed;

    float speed = 0;

    int hullRotationSpeed = Settings.hullRotationSpeed;

    int tack;

    public Yacht(int positionX, int positionY, ArrayList<String> pathToHullImages, ArrayList<String> pathToSailImages, ArrayList<String> pathToWindImages, int height, int width, int hullRotationDegrees, int sailRotationDegrees, float windSpeed, int windRotationDegrees) {
        super(positionX, positionY);

        this.precisePositionX = (float) positionX;
        this.precisePositionY = (float) positionY;

        this.pathToHullImages = pathToHullImages;
        this.pathToSailImages = pathToSailImages;
        this.pathToWindImages = pathToWindImages;

        this.hullRotationDegrees = hullRotationDegrees;
        this.sailRotationDegrees = sailRotationDegrees;

        this.width = width;
        this.height = height;

        this.windRotationDegrees = windRotationDegrees;
        this.windSpeed = windSpeed;

        degreesPerHullImage = 360 / pathToHullImages.size();
        degreesPerSailImage = 360 / pathToSailImages.size();
        degreesPerWindImage = 360 / pathToWindImages.size();
    }

    public void tick() {
        ArrayList<Integer> pressedKeys = Main.getDisplay().getKeyReader().getPressedKeys();

        int lastTack = tack;
        tack = Calc.getRoundedDegrees(hullRotationDegrees - windRotationDegrees) <= 180 ? 1 : -1;

        if (pressedKeys.contains(KeyEvent.VK_W)) {sailEase += Settings.sailEasingSpeed;}
        if (pressedKeys.contains(KeyEvent.VK_S)) {sailEase -= Settings.sailEasingSpeed;}
        if (pressedKeys.contains(KeyEvent.VK_A)) {hullRotationDegrees -= hullRotationSpeed;}
        if (pressedKeys.contains(KeyEvent.VK_D)) {hullRotationDegrees += hullRotationSpeed;}

        sailEase = Math.max(0f, Math.min(1f, sailEase));

        if (lastTack != tack) {
            sailRotationDegrees = hullRotationDegrees + 180;
        } else {
            sailRotationDegrees = hullRotationDegrees - (int) (-80f * sailEase * (float) tack) + 180;
        }
        
        float optimalEase = 1 - (float) Math.abs(Calc.getRoundedDegrees(hullRotationDegrees - windRotationDegrees - 180) - 180) / Settings.notHeadToWindDegrees;

        hullRotationSpeed = Settings.hullRotationSpeed;

        if (- optimalEase + 1 >= 1) {
            optimalEase = -0.8f;
            if (sailEase < 0.5) {
                hullRotationSpeed = Settings.headToWindHullRotationSpeed;
            }
        }

        speed = (1 - Math.abs(optimalEase - sailEase)) * windSpeed;

        if (- optimalEase + 1 >= 1) {
            speed /= 2;
        }
        
        hullRotationDegrees = Calc.getRoundedDegrees(hullRotationDegrees);
        sailRotationDegrees = Calc.getRoundedDegrees(sailRotationDegrees);

        precisePositionX += Calc.findB(hullRotationDegrees, speed);
        precisePositionY += Calc.findA(hullRotationDegrees, speed);

        positionX = Math.round(precisePositionX);
        positionY = Math.round(precisePositionY);
    }

    public String getImageFromRotation(int rotationDegrees, int degreesPerImage, ArrayList<String> pathTomages) {
        return pathTomages.get(Math.min(pathTomages.size() - 1, Calc.getRoundedDegrees(rotationDegrees + degreesPerImage / 2) / degreesPerImage));
    }

    public void draw() {
        Main.getDisplay().getDisplayPanel().drawImage(getImageFromRotation(windRotationDegrees, degreesPerWindImage, pathToWindImages), positionX, positionY);
        Main.getDisplay().getDisplayPanel().drawImage(getImageFromRotation(hullRotationDegrees, degreesPerHullImage, pathToHullImages), positionX, positionY);
        Main.getDisplay().getDisplayPanel().drawImage(getImageFromRotation(sailRotationDegrees, degreesPerSailImage, pathToSailImages), positionX, positionY);
    }

    public void setPathToActiveImage(String pathToActiveImage) {
    }

    public int getHullRotationDegrees() {
        return hullRotationDegrees;
    }

    public int getSailRotationDegrees() {
        return sailRotationDegrees;
    }
}
