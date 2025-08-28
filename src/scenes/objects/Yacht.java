package scenes.objects;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.Main;
import settings.Settings;
import calc.*;

public class Yacht extends ObjectTemplate {
    String pathToHullImages;
    String pathToSailImages;
    String pathToWindImages;

    int hullImagesCount;
    int sailImagesCount;
    int windImagesCount;

    int windStagesCount;

    float precisePositionX;
    float precisePositionY;

    int hullRotationDegrees;
    int sailRotationDegrees;
    float sailEase = 0;

    int windRotationDegrees;
    float windSpeed;

    float speed = 0;

    int hullRotationSpeed = Settings.hullRotationSpeed;

    int tack;

    int windStage;

    public Yacht(int positionX, int positionY, String pathToHullImages, String pathToSailImages, String pathToWindImages, int hullImagesCount, int sailImagesCount, int windImagesCount, int windStagesCount, int hullRotationDegrees, float sailEase, float windSpeed, int windRotationDegrees) {
        super(positionX, positionY);

        this.precisePositionX = (float) positionX;
        this.precisePositionY = (float) positionY;

        this.pathToHullImages = pathToHullImages;
        this.pathToSailImages = pathToSailImages;
        this.pathToWindImages = pathToWindImages;

        this.hullImagesCount = hullImagesCount;
        this.sailImagesCount = sailImagesCount;
        this.windImagesCount = windImagesCount;

        this.windStagesCount = windStagesCount;

        this.hullRotationDegrees = hullRotationDegrees;
        this.sailEase = sailEase;

        this.windRotationDegrees = windRotationDegrees;
        this.windSpeed = windSpeed;
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

        float easeСloseness = Math.abs(optimalEase - sailEase);

        windStage = Math.min(windStagesCount - 1, Math.round(easeСloseness * windStagesCount));

        speed = (1 - easeСloseness) * windSpeed;

        if (1 - optimalEase >= 1) {
            speed /= 2;
        }
        
        hullRotationDegrees = Calc.getRoundedDegrees(hullRotationDegrees);
        sailRotationDegrees = Calc.getRoundedDegrees(sailRotationDegrees);

        precisePositionX += Calc.findB(hullRotationDegrees, speed);
        precisePositionY += Calc.findA(hullRotationDegrees, speed);

        precisePositionX = Math.min(Settings.windowWidth, Math.max(precisePositionX, 0));
        precisePositionY = Math.min(Settings.windowHeight, Math.max(precisePositionY, 0));

        positionX = Math.round(precisePositionX);
        positionY = Math.round(precisePositionY);
    }

    public String getImageFromRotation(int rotationDegrees, String pathToImages, int imagesCount) {
        String imageName = pathToImages;
        float degreesPerImage = (360f / (float) imagesCount);
        imageName += Calc.getRoundedDegrees((int) Math.ceil(rotationDegrees + degreesPerImage / 2 - (rotationDegrees + degreesPerImage / 2) % degreesPerImage));
        imageName += Settings.imageFile;

        return imageName;
    }

    public void draw() {
        Main.getDisplay().getDisplayPanel().drawImage(getImageFromRotation(windRotationDegrees, pathToWindImages + windStage + "/", windImagesCount), positionX, positionY);
        Main.getDisplay().getDisplayPanel().drawImage(getImageFromRotation(hullRotationDegrees, pathToHullImages, hullImagesCount), positionX, positionY);
        Main.getDisplay().getDisplayPanel().drawImage(getImageFromRotation(sailRotationDegrees, pathToSailImages, sailImagesCount), positionX, positionY);
    }

    public void setPathToActiveImage(String pathToActiveImage) {
    }

    public int getHullRotationDegrees() {
        return hullRotationDegrees;
    }

    public int getSailRotationDegrees() {
        return sailRotationDegrees;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
        this.precisePositionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
        this.precisePositionY = positionY;
    }
}
