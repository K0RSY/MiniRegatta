package scenes;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import main.Main;
import maps.data.*;
import scenes.objects.*;
import settings.Settings;

public class Game extends SceneTemplate {
    ArrayList<Integer> buoyIndexOrder = new ArrayList<Integer>();
    int currentBuoyOrderIndex = 0;
    boolean crossedCurrentBuoyStart = false;
    boolean needToFinish = false;
    boolean needToStart = true;

    int lastYachtQuarterX;
    int lastYachtQuarterY;

    boolean yachtInStartZone;
    boolean yachtInPreFinishZone;

    boolean falseStartWarned = false;

    HashMap<String, ArrayList<String>> buoyAnimations;
    HashMap<String, Boolean> buoyLoops;
    HashMap<String, ArrayList<String>> startBuoyAnimations;
    HashMap<String, Boolean> startBuoyLoops;

    public Game() {
        addKey("background");
        addKey("start");
        addKey("buoys");
        addKey("yacht");
        addKey("gui");

        loadAnimations();
        loadMap("one_loop.dat");
    }

    public void loadAnimations() {
        buoyAnimations = new HashMap<String, ArrayList<String>>();
        buoyLoops = new HashMap<String, Boolean>();
        startBuoyAnimations = new HashMap<String, ArrayList<String>>();
        startBuoyLoops = new HashMap<String, Boolean>();

        buoyLoops.put("flag", false);
        buoyLoops.put("unflag", false);
        buoyLoops.put("flagged", true);

        startBuoyLoops.put("flag", false);
        startBuoyLoops.put("unflag", true);
        startBuoyLoops.put("flagged", true);

        buoyAnimations.put("flag", new ArrayList<String>());
        buoyAnimations.put("unflag", new ArrayList<String>());
        buoyAnimations.put("flagged", new ArrayList<String>());

        startBuoyAnimations.put("flag", new ArrayList<String>());
        startBuoyAnimations.put("unflag", new ArrayList<String>());
        startBuoyAnimations.put("flagged", new ArrayList<String>());

        Collections.addAll(buoyAnimations.get("flag"), "res/textures/buoy/crossing/flag/0.png", "res/textures/buoy/crossing/flag/1.png", "res/textures/buoy/crossing/flag/2.png", "res/textures/buoy/crossing/flag/3.png", "res/textures/buoy/crossing/flag/4.png");
        Collections.addAll(buoyAnimations.get("unflag"), "res/textures/buoy/crossing/unflag/0.png", "res/textures/buoy/crossing/unflag/1.png", "res/textures/buoy/crossing/unflag/2.png", "res/textures/buoy/crossing/unflag/3.png", "res/textures/buoy/crossing/unflag/4.png");
        Collections.addAll(buoyAnimations.get("flagged"), "res/textures/buoy/crossing/flagged/0.png", "res/textures/buoy/crossing/flagged/1.png", "res/textures/buoy/crossing/flagged/2.png", "res/textures/buoy/crossing/flagged/3.png");

        Collections.addAll(startBuoyAnimations.get("flag"), "res/textures/buoy/start/0.png", "res/textures/buoy/start/1.png", "res/textures/buoy/start/2.png", "res/textures/buoy/start/3.png");
        Collections.addAll(startBuoyAnimations.get("unflag"), "res/textures/buoy/start/0.png", "res/textures/buoy/start/1.png", "res/textures/buoy/start/2.png", "res/textures/buoy/start/3.png");
        Collections.addAll(startBuoyAnimations.get("flagged"), "res/textures/buoy/start/0.png", "res/textures/buoy/start/1.png", "res/textures/buoy/start/2.png", "res/textures/buoy/start/3.png");
    }

    public void loadMap(String map) {
        try {
            FileInputStream fos = new FileInputStream(Settings.mapPath + map);
            BufferedInputStream bos = new BufferedInputStream(fos);
            ObjectInputStream oos = new ObjectInputStream(bos);

            MapTemplate mapData = (MapTemplate) oos.readObject();

            buoyIndexOrder = mapData.order;

            for (maps.data.objects.Buoy buoy : mapData.buoys) {
                int quarterX = buoy.crossQuarter == 2 || buoy.crossQuarter == 3 ? -1 : 1;
                int quarterY = buoy.crossQuarter == 0 || buoy.crossQuarter == 3 ? -1 : 1;

                objects.get("buoys").add(new Buoy(buoy.positionX, buoy.positionY, 28, 20, buoyAnimations, buoyLoops, 2, quarterX, quarterY, buoy.crossByLeftSide));
            }

            String pathToFlaggedImage;
            String pathToUnflaggedImage;

            if (mapData.start.startX != 0) {
                pathToUnflaggedImage = "res/textures/start/unflagged/vertical.png";
                pathToFlaggedImage = "res/textures/start/flagged/vertical.png";
            } else {
                pathToUnflaggedImage = "res/textures/start/unflagged/horisontal.png";
                pathToFlaggedImage = "res/textures/start/flagged/horisontal.png";
            }

            objects.get("start").add(new StartLine(mapData.start.positionX, mapData.start.positionY, pathToUnflaggedImage, pathToFlaggedImage, 8, mapData.start.lenght / 8, mapData.start.startX, mapData.start.startY, mapData.start.invertFinish));
        
            objects.get("buoys").add(new Buoy(mapData.start.positionX - (mapData.start.lenght / 2) * mapData.start.startY, mapData.start.positionY - (mapData.start.lenght / 2) * mapData.start.startX, 24, 16, startBuoyAnimations, startBuoyLoops, 2, 1, 1, false));
            objects.get("buoys").add(new Buoy(mapData.start.positionX + (mapData.start.lenght / 2) * mapData.start.startY, mapData.start.positionY + (mapData.start.lenght / 2) * mapData.start.startX, 24, 16, startBuoyAnimations, startBuoyLoops, 2, -1, 1, false));    

            objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "res/textures/background.png"));

            objects.get("yacht").add(new Yacht(mapData.yacht.positionX, mapData.yacht.positionY, "res/textures/yacht/hull/", "res/textures/yacht/sail/", "res/textures/yacht/wind/", 16, 16, 16, 3, mapData.yacht.hullRotationDegrees, mapData.yacht.sailEase, mapData.yacht.windSpeed, mapData.yacht.windRotationDegrees));

            objects.get("gui").add(new Timer(Settings.windowWidth / 2, 10, 10, mapData.startTime, "res/textures/font/", 8));

            ((StartLine) objects.get("start").get(0)).toogleFlagged();

            ((Buoy) objects.get("buoys").get(objects.get("buoys").size() - 1)).toogleFlagged();

            oos.close();
            bos.close();
            fos.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        super.tick();

        Buoy currentBuoy = (Buoy) objects.get("buoys").get(buoyIndexOrder.get(currentBuoyOrderIndex));

        int yachtQuarterX = currentBuoy.getQuarterX(objects.get("yacht").get(0).getPositionX());
        int yachtQuarterY = currentBuoy.getQuarterY(objects.get("yacht").get(0).getPositionY());

        int yachtPositionX = objects.get("yacht").get(0).getPositionX();
        int yachtPositionY = objects.get("yacht").get(0).getPositionY();

        if (!needToFinish && !needToStart) {
            if (currentBuoy.getInStartCrossQuarter(lastYachtQuarterX, lastYachtQuarterY) && currentBuoy.getInCrossQuarter(yachtQuarterX, yachtQuarterY)) {
                crossedCurrentBuoyStart = true;
            } else if (currentBuoy.getInCrossQuarter(lastYachtQuarterX, lastYachtQuarterY) && currentBuoy.getInEndCrossQuarter(yachtQuarterX, yachtQuarterY)) {
                currentBuoy.toogleFlagged();

                currentBuoyOrderIndex += 1;
                
                if (currentBuoyOrderIndex > buoyIndexOrder.size() - 1) {
                    needToFinish = true;
                    ((StartLine) objects.get("start").get(0)).toogleFlagged();
                    currentBuoy.toogleFlagged();
                    currentBuoyOrderIndex = buoyIndexOrder.size() - 1;
                }

                ((Buoy) objects.get("buoys").get(buoyIndexOrder.get(currentBuoyOrderIndex))).toogleFlagged();

                lastYachtQuarterX = 0;
                lastYachtQuarterY = 0;

                crossedCurrentBuoyStart = false;
            } else if (!currentBuoy.getInCrossQuarter(yachtQuarterX, yachtQuarterY)) {
                crossedCurrentBuoyStart = false;
            }
        } else if (needToFinish) {
            if (yachtInPreFinishZone && ((StartLine) objects.get("start").get(0)).getInFinishZone(yachtPositionX, yachtPositionY)) {
                Main.changeCurrentScene(new Transition(new Game()));
            }
        }

        lastYachtQuarterX = yachtQuarterX;
        lastYachtQuarterY = yachtQuarterY;

        for (ObjectTemplate buoy : objects.get("buoys")) {
            int positionX = buoy.getPositionX();
            int positionY = buoy.getPositionY();
            int width = ((Buoy) buoy).getWidth();
            int height = ((Buoy) buoy).getHeight();

            int positionFirstX = positionX - width / 2;
            int positionFirstY = positionY - height / 2;
            int positionSecondX = positionX + width / 2;
            int positionSecondY = positionY + height / 2;

            if (yachtPositionX > positionFirstX && yachtPositionX < positionSecondX && yachtPositionY > positionFirstY && yachtPositionY < positionSecondY) {
                if (((Timer) objects.get("gui").get(0)).getWarningTimeOut()) {
                    ((Timer) objects.get("gui").get(0)).addTime(Settings.buoyCollisionAddTime);
                }
                
                int newYachtPositionX = Math.round(positionX + (yachtPositionX - positionX) * Settings.buoyCollisionDistaceMultiplier);
                int newYachtPositionY = Math.round(positionY + (yachtPositionY - positionY) * Settings.buoyCollisionDistaceMultiplier);

                objects.get("yacht").get(0).setPositionX(newYachtPositionX);
                objects.get("yacht").get(0).setPositionY(newYachtPositionY);
            }
        }
        
        if (needToStart && ((Timer) objects.get("gui").get(0)).startTimeOut() && ((StartLine) objects.get("start").get(0)).getInPreStartZone(yachtPositionX, yachtPositionY)) {
            if (yachtInStartZone) {
                ((StartLine) objects.get("start").get(0)).toogleFlagged();
                ((Buoy) objects.get("buoys").get(buoyIndexOrder.get(0))).toogleFlagged();
    
                needToStart = false;
            } else if (!falseStartWarned) {
                ((Timer) objects.get("gui").get(0)).addTime(Settings.falseStartCollisionAddTime);
                falseStartWarned = true;
            }
        }

        yachtInStartZone = ((StartLine) objects.get("start").get(0)).getInStartZone(yachtPositionX, yachtPositionY);
        yachtInPreFinishZone = ((StartLine) objects.get("start").get(0)).getInPreFinishZone(yachtPositionX, yachtPositionY);
    }
}
