package scenes;

import main.Main;
import scenes.objects.*;

public class MainMenu extends SceneTemplate {
    public MainMenu() {
        objects.add(new Button(100, 100, "res/textures/1.png", "res/textures/2.png", 32, 32, () -> {Main.changeCurrentScene(new LevelGame());}));
    }
}
