package scenes;

import main.Main;
import scenes.objects.*;
import settings.Settings;

public class MainMenu extends SceneTemplate {
    public MainMenu() {
        addKey("background");
        addKey("gui");

        objects.get("background").add(new Image(Settings.windowWidth / 2, Settings.windowHeight / 2, "/res/textures/background.png"));
        objects.get("gui").add(new Button(100, 100, "/res/textures/1.png", "/res/textures/2.png", 64, 64, () -> {Main.changeCurrentScene(new Transition(new Game()));}));
    }
}
