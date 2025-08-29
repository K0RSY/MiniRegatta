package miniregatta.scenes;

import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import miniregatta.main.Main;
import miniregatta.maps.save.Save;
import miniregatta.scenes.objects.*;
import miniregatta.settings.Settings;

public class MapsMenu extends SceneTemplate {
    int currentMap = 0;
    Save save = getSave();

    public MapsMenu() {
        addKey("background");
        addKey("map_list");
        addKey("gui");

        objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "/resources/textures/background.png"));
        objects.get("map_list").add(new TextButton(Settings.windowWidth / 2, Settings.windowHeight / 2, "placeholder", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {}));
        objects.get("map_list").add(new TextButton(Settings.windowWidth / 2 - 160, Settings.windowHeight / 2, "<", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {scrollMap(-1);}));
        objects.get("map_list").add(new TextButton(Settings.windowWidth / 2 + 160, Settings.windowHeight / 2, ">", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {scrollMap(1);}));

        scrollMap(0);
    }

    public void scrollMap(int count) {
        currentMap += count;
        while (currentMap >= Main.getLoadedMaps().size() - 1) {
            currentMap -= Main.getLoadedMaps().size();
        }
        while (currentMap < 0) {
            currentMap += Main.getLoadedMaps().size();
        }
        
        final String map = Main.getLoadedMaps().get(currentMap);
        int bestTime = Settings.worstTime;
        if (save.save.get(map) != null) {
            bestTime = save.save.get(map);
        }
        String formattedTime = Timer.getFormattedTime(bestTime);

        String mapName = "";
        mapName += map;
        mapName += Settings.mapsListElementFiller.repeat(Settings.mapsListElementWidth - map.length() - formattedTime.length());
        mapName += formattedTime;
        ((TextButton) objects.get("map_list").get(0)).setText(mapName);
        ((TextButton) objects.get("map_list").get(0)).setClickFunctionInterface(() -> {Main.changeCurrentScene(new Transition(new Game(map)));});
    }

    public Save getSave() {
        try {
            FileInputStream fis = new FileInputStream(Settings.saveFilePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            Save save = (Save) ois.readObject();

            ois.close();
            bis.close();
            fis.close();

            return save;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public void tick() {
        super.tick();

        if (Main.getDisplay().getKeyReader().getPressedKeys().contains(KeyEvent.VK_ESCAPE)) {
            Main.changeCurrentScene(new Transition(new MainMenu()));
        }
    }
}
