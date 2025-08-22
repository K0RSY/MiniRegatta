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
    int tillerRotationDegrees = 0;
    int sheetRotationDegrees = 0;

    int windRotationDegrees;
    float windSpeed;

    float speed = 0;

    public Yacht(int positionX, int positionY, ArrayList<String> pathToHullImages, ArrayList<String> pathToSailImages, ArrayList<String> pathToWindImages, int height, int width, int hullRotationDegrees, int sailRotationDegrees, float windSpeed, int windRotationDegrees) {
        super(positionX, positionY);
        this.precisePositionX = (float) positionX;
        this.precisePositionY = (float) positionY;
        this.pathToHullImages = pathToHullImages;
        this.pathToSailImages = pathToSailImages;
        this.pathToWindImages = pathToWindImages;

        this.hullRotationDegrees = hullRotationDegrees;
        this.sailRotationDegrees = sailRotationDegrees;
        this.tillerRotationDegrees = hullRotationDegrees;
        this.sheetRotationDegrees = sailRotationDegrees;

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

        int relativeTillerX = 0;
        int relativeTillerY = 0;
        int relativeSheetX = 0;
        int relativeSheetY = 0;

        if (pressedKeys.contains(KeyEvent.VK_W)) {relativeTillerY -= 100;}
        if (pressedKeys.contains(KeyEvent.VK_S)) {relativeTillerY += 100;}
        if (pressedKeys.contains(KeyEvent.VK_A)) {relativeTillerX -= 100;}
        if (pressedKeys.contains(KeyEvent.VK_D)) {relativeTillerX += 100;}

        if (pressedKeys.contains(KeyEvent.VK_UP)) {relativeSheetY -= 100;}
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) {relativeSheetY += 100;}
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) {relativeSheetX -= 100;}
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) {relativeSheetX += 100;}

        if (!(relativeTillerX == 0 && relativeTillerY == 0)) {
            tillerRotationDegrees = Calc.findDegrees((float) relativeTillerY, (float) relativeTillerX);

            int hullRotationDifference = Math.round(Calc.findDifference(tillerRotationDegrees, hullRotationDegrees) * (speed * Settings.rotationSpeedToSpeedRatio));
            System.out.println(Calc.findDifference(tillerRotationDegrees, hullRotationDegrees));

            hullRotationDegrees += hullRotationDifference;
            sailRotationDegrees += hullRotationDifference;
        }

        if (!(relativeSheetX == 0 && relativeSheetY == 0)) {
            sheetRotationDegrees = Calc.findDegrees((float) relativeSheetY, (float) relativeSheetX);
    
            int sailRotationDifference = Math.round(Calc.findDifference(sheetRotationDegrees, sailRotationDegrees) * Settings.sheetSpeed);
            
            sailRotationDegrees += sailRotationDifference;
        }
        
        int windSailRotationDegrees = Math.abs(Calc.findDifference(Calc.getRoundedDegrees(sailRotationDegrees - windRotationDegrees), 90)) < Math.abs(Calc.findDifference(Calc.getRoundedDegrees(sailRotationDegrees - windRotationDegrees), -90)) ? sailRotationDegrees - 90 : sailRotationDegrees + 90;
        speed = (1 - (float) Math.abs(Math.abs(Calc.findDifference(sailRotationDegrees, windSailRotationDegrees) - 90)) / 90) * (1 - (float) Math.abs(Math.abs(Calc.findDifference(windSailRotationDegrees, hullRotationDegrees))) / 90) * windSpeed;
        speed = Math.abs(Calc.findDifference(windSailRotationDegrees, hullRotationDegrees)) < Math.abs(Calc.findDifference(windSailRotationDegrees, Calc.getRoundedDegrees(hullRotationDegrees - 180))) ? speed : -speed;

        hullRotationDegrees = Calc.getRoundedDegrees(hullRotationDegrees);
        sailRotationDegrees = Calc.getRoundedDegrees(sailRotationDegrees);

        precisePositionX += Calc.findB(hullRotationDegrees, speed);
        precisePositionY += Calc.findA(hullRotationDegrees, speed);

        positionX = Math.round(precisePositionX);
        positionY = Math.round(precisePositionY);
    }

    public void draw() {
        // Main.getDisplay().getDisplayPanel().drawImage(pathToWindImages.get(Math.min(pathToWindImages.size() - 1, windRotationDegrees / degreesPerWindImage)), positionX, positionY);
        Main.getDisplay().getDisplayPanel().drawImage(pathToHullImages.get(Math.min(pathToHullImages.size() - 1, hullRotationDegrees / degreesPerHullImage)), positionX, positionY);
        Main.getDisplay().getDisplayPanel().drawImage(pathToSailImages.get(Math.min(pathToSailImages.size() - 1, sailRotationDegrees / degreesPerSailImage)), positionX, positionY);
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
