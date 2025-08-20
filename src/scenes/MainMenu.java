package scenes;

import scenes.objects.*;

public class MainMenu extends SceneTemplate {
    public MainMenu() {
        objects.add(new Image(0, 0, "res/textures/1.png"));
        objects.add(new Button(100, 100, "res/textures/1.png", "res/textures/2.png", 32, 32));
    }
}
