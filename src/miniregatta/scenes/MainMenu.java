package miniregatta.scenes;

import miniregatta.main.Main;
import miniregatta.scenes.objects.*;
import miniregatta.settings.Settings;

public class MainMenu extends SceneTemplate {
    public MainMenu() {
        addKey("background");
        addKey("gui");

        objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "/resources/textures/background.png"));
        objects.get("gui").add(new Button(100, 100, "/resources/textures/1.png", "/resources/textures/2.png", 64, 64, () -> {Main.changeCurrentScene(new Transition(new Game()));}));
    }
}
