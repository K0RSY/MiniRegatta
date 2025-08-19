package scenes;

import scenes.objects.Image;

public class MainMenu extends SceneTemplate {
    public MainMenu() {
        objects.add(
            new Image(0, 0, "res/textures/asphalt.png")
        );
    }
}
