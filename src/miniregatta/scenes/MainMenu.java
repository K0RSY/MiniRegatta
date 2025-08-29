package miniregatta.scenes;

import miniregatta.main.Main;
import miniregatta.scenes.objects.*;
import miniregatta.settings.Settings;

import java.awt.event.KeyEvent;
import java.util.Random;

public class MainMenu extends SceneTemplate {
    public MainMenu() {
        addKey("background");
        addKey("gui");

        objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "/resources/textures/background.png"));
        objects.get("gui").add(new TextButton(Settings.windowWidth / 2, Settings.windowHeight / 2 - 16, "random map", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {
            Random randomizer = new Random();
            String map = Main.getLoadedMaps().get(randomizer.nextInt(Main.getLoadedMaps().size()));
            Main.changeCurrentScene(new Transition(new Game(map)));
        }));
        objects.get("gui").add(new TextButton(Settings.windowWidth / 2, Settings.windowHeight / 2 + 16, "choice map", "/resources/textures/font/", "/resources/textures/font/background/not_active.png", "/resources/textures/font/background/active.png", 8, () -> {Main.changeCurrentScene(new Transition(new MapsMenu()));}));
    }

    public void tick() {
        super.tick();

        if (Main.getDisplay().getKeyReader().getPressedKeys().contains(KeyEvent.VK_ESCAPE)) {
            Main.stopMainLoop();
        }
    }
}
