package miniregatta.scenes;

import java.awt.event.KeyEvent;
import java.util.Random;

import miniregatta.main.Main;
import miniregatta.scenes.objects.*;
import miniregatta.settings.Settings;

public class GameFinish extends SceneTemplate {
    public GameFinish(int currentTime, int previousTime) {
        addKey("background");
        addKey("gui");

        String message;
        if (currentTime < previousTime) {
            message = "new record";
        } else if (currentTime == previousTime) {
            message = "reached the record";
        } else {
            message = "";
        }
        message += "\n\n";
        message += "current time........." + Timer.getFormattedTime(currentTime) + "\n";
        message += "previous best time..." + Timer.getFormattedTime(previousTime);

        objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "/resources/textures/background.png"));
        
        objects.get("gui").add(new TextButton(Settings.windowWidth / 2, Settings.windowHeight / 2 + 16, "random map", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {
            Random randomizer = new Random();
            String map = Main.getLoadedMaps().get(randomizer.nextInt(Main.getLoadedMaps().size()));
            Main.changeCurrentScene(new Transition(new Game(map)));
        }));
        objects.get("gui").add(new TextButton(Settings.windowWidth / 2, Settings.windowHeight / 2 + 48, "choice map", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {Main.changeCurrentScene(new Transition(new MapsMenu()));}));
        
        objects.get("gui").add(new Text(Settings.windowWidth / 2, Settings.windowHeight / 2 - 48, message, "/resources/textures/font/", 8));
    }

    public void tick() {
        super.tick();

        if (Main.getDisplay().getKeyReader().getPressedKeys().contains(KeyEvent.VK_ESCAPE)) {
            Main.changeCurrentScene(new Transition(new MainMenu()));
        }
    }
}
