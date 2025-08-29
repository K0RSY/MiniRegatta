package miniregatta.scenes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.event.KeyEvent;

import miniregatta.main.Main;
import miniregatta.maps.data.*;
import miniregatta.maps.save.Save;
import miniregatta.scenes.objects.*;
import miniregatta.settings.Settings;

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
    boolean startGiven;

    boolean falseStartWarned = false;

    int currentTime;
    int previousTime;

    String map;

    HashMap<String, ArrayList<String>> buoyAnimations;
    HashMap<String, Boolean> buoyLoops;
    HashMap<String, ArrayList<String>> startBuoyAnimations;
    HashMap<String, Boolean> startBuoyLoops;

    public Game(String map) {
        addKey("background");
        addKey("start");
        addKey("buoys");
        addKey("yacht");
        addKey("gui");

        loadAnimations();
        this.map = map;
        loadMap();
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

        Collections.addAll(buoyAnimations.get("flag"), "/resources/textures/buoy/crossing/flag/0.png", "/resources/textures/buoy/crossing/flag/1.png", "/resources/textures/buoy/crossing/flag/2.png", "/resources/textures/buoy/crossing/flag/3.png", "/resources/textures/buoy/crossing/flag/4.png");
        Collections.addAll(buoyAnimations.get("unflag"), "/resources/textures/buoy/crossing/unflag/0.png", "/resources/textures/buoy/crossing/unflag/1.png", "/resources/textures/buoy/crossing/unflag/2.png", "/resources/textures/buoy/crossing/unflag/3.png", "/resources/textures/buoy/crossing/unflag/4.png");
        Collections.addAll(buoyAnimations.get("flagged"), "/resources/textures/buoy/crossing/flagged/0.png", "/resources/textures/buoy/crossing/flagged/1.png", "/resources/textures/buoy/crossing/flagged/2.png", "/resources/textures/buoy/crossing/flagged/3.png");

        Collections.addAll(startBuoyAnimations.get("unflag"), "/resources/textures/buoy/start/0.png", "/resources/textures/buoy/start/1.png", "/resources/textures/buoy/start/2.png", "/resources/textures/buoy/start/3.png");
    }

    public void loadMap() {
        try {
            BufferedInputStream bos = new BufferedInputStream(getClass().getResourceAsStream(Settings.mapPath + map));
            ObjectInputStream oos = new ObjectInputStream(bos);

            MapTemplate mapData = (MapTemplate) oos.readObject();

            buoyIndexOrder = mapData.order;

            for (miniregatta.maps.data.objects.Buoy buoy : mapData.buoys) {
                int quarterX = buoy.crossQuarter == 2 || buoy.crossQuarter == 3 ? -1 : 1;
                int quarterY = buoy.crossQuarter == 0 || buoy.crossQuarter == 3 ? -1 : 1;

                objects.get("buoys").add(new Buoy(buoy.positionX, buoy.positionY, 24, 20, buoyAnimations, buoyLoops, 2, quarterX, quarterY, buoy.crossByLeftSide));
            }

            String pathToFlaggedImage;
            String pathToUnflaggedImage;
            String pathToMainShipImage;

            if (mapData.start.startX != 0) {
                pathToUnflaggedImage = "/resources/textures/start/unflagged/vertical.png";
                pathToFlaggedImage = "/resources/textures/start/flagged/vertical.png";
                if (mapData.start.startX == 1) {
                    pathToMainShipImage = "/resources/textures/buoy/main_ship/270.png";
                } else {
                    pathToMainShipImage = "/resources/textures/buoy/main_ship/90.png";
                }
            } else {
                pathToUnflaggedImage = "/resources/textures/start/unflagged/horisontal.png";
                pathToFlaggedImage = "/resources/textures/start/flagged/horisontal.png";
                if (mapData.start.startY == 1) {
                    pathToMainShipImage = "/resources/textures/buoy/main_ship/0.png";
                } else {
                    pathToMainShipImage = "/resources/textures/buoy/main_ship/180.png";
                }
            }

            Collections.addAll(startBuoyAnimations.get("flag"), pathToMainShipImage);
            Collections.addAll(startBuoyAnimations.get("flagged"), pathToMainShipImage);

            objects.get("start").add(new StartLine(mapData.start.positionX, mapData.start.positionY, pathToUnflaggedImage, pathToFlaggedImage, 8, mapData.start.lenght / 8, mapData.start.startX, mapData.start.startY, mapData.start.invertFinish));
        
            objects.get("buoys").add(new Buoy(mapData.start.positionX - (mapData.start.lenght / 2) * mapData.start.startY, mapData.start.positionY - (mapData.start.lenght / 2) * mapData.start.startX, 24, 20, startBuoyAnimations, startBuoyLoops, 2, 1, 1, false));
            objects.get("buoys").add(new Buoy(mapData.start.positionX + (mapData.start.lenght / 2) * mapData.start.startY, mapData.start.positionY + (mapData.start.lenght / 2) * mapData.start.startX, 16, 16, startBuoyAnimations, startBuoyLoops, 2, -1, 1, false));    

            objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "/resources/textures/background.png"));

            objects.get("yacht").add(new Yacht(mapData.yacht.positionX, mapData.yacht.positionY, "/resources/textures/yacht/hull/", "/resources/textures/yacht/sail/", "/resources/textures/yacht/wind/", 16, 16, 16, 3, mapData.yacht.hullRotationDegrees, mapData.yacht.sailEase, mapData.yacht.windSpeed, mapData.yacht.windRotationDegrees));

            objects.get("gui").add(new Timer(Settings.windowWidth / 2, 10, 10, mapData.startTime, "/resources/textures/font/", 8));

            ((StartLine) objects.get("start").get(0)).toogleFlagged();

            ((Buoy) objects.get("buoys").get(objects.get("buoys").size() - 1)).toogleFlagged();

            oos.close();
            bos.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTime() {
        try {
            FileInputStream fis = new FileInputStream(Settings.saveFilePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            Save save = (Save) ois.readObject();

            int time = ((Timer) objects.get("gui").get(0)).getTime();

            currentTime = time;
            previousTime = save.save.get(map) == null ? Settings.worstTime : save.save.get(map);

            if (save.save.get(map) == null || save.save.get(map) > time) {
                save.save.put(map, time);
            }
    
            ois.close();
            bis.close();
            fis.close();

            FileOutputStream fos = new FileOutputStream(Settings.saveFilePath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            
            oos.writeObject(save);

            oos.close();
            bos.close();
            fos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (Main.getDisplay().getKeyReader().getPressedKeys().contains(KeyEvent.VK_ESCAPE)) {
            Main.changeCurrentScene(new Transition(new MainMenu()));
        }

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

                Main.getSpeaker().addSoundToQueue("/resources/sounds/buoy_crossed.wav");

            } else if (!currentBuoy.getInCrossQuarter(yachtQuarterX, yachtQuarterY)) {
                crossedCurrentBuoyStart = false;
            }
        } else if (needToFinish) {
            if (yachtInPreFinishZone && ((StartLine) objects.get("start").get(0)).getInFinishZone(yachtPositionX, yachtPositionY)) {
                Main.getSpeaker().addSoundToQueue("/resources/sounds/start_whisle.wav");
                saveTime();
                Main.changeCurrentScene(new Transition(new GameFinish(currentTime, previousTime)));
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
                    ((Timer) objects.get("gui").get(0)).addTime(Settings.buoyCollisionAddTime, "buoy collision");
                    Main.getSpeaker().addSoundToQueue("/resources/sounds/warning_whisle.wav");
                }
                
                int newYachtPositionX = Math.round(positionX + (yachtPositionX - positionX) * Settings.buoyCollisionDistaceMultiplier);
                int newYachtPositionY = Math.round(positionY + (yachtPositionY - positionY) * Settings.buoyCollisionDistaceMultiplier);

                objects.get("yacht").get(0).setPositionX(newYachtPositionX);
                objects.get("yacht").get(0).setPositionY(newYachtPositionY);
            }
        }
        
        if (((Timer) objects.get("gui").get(0)).startTimeOut()) {
            if (needToStart && ((StartLine) objects.get("start").get(0)).getInPreStartZone(yachtPositionX, yachtPositionY)) {
                ((StartLine) objects.get("start").get(0)).toogleFlagged();
                ((Buoy) objects.get("buoys").get(buoyIndexOrder.get(0))).toogleFlagged();
    
                needToStart = false;
                
                if (!falseStartWarned && !yachtInStartZone) {
                    ((Timer) objects.get("gui").get(0)).addTime(Settings.falseStartCollisionAddTime, "false start");
                    Main.getSpeaker().addSoundToQueue("/resources/sounds/warning_whisle.wav");
                    falseStartWarned = true;
                }
            }

            if (!startGiven && !falseStartWarned) {
                Main.getSpeaker().addSoundToQueue("/resources/sounds/start_whisle.wav");
                startGiven = true;
            }
        }

        yachtInStartZone = ((StartLine) objects.get("start").get(0)).getInStartZone(yachtPositionX, yachtPositionY);
        yachtInPreFinishZone = ((StartLine) objects.get("start").get(0)).getInPreFinishZone(yachtPositionX, yachtPositionY);
    }
}
