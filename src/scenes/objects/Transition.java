package scenes.objects;

import main.Main;
import settings.Settings;

public class Transition extends Image {
    int lastPositionX;

    public Transition(String pathToImage) {
        super(-Settings.windowWidth / 2, Settings.windowHeight / 2, pathToImage);
    }

    public void tick() {
        lastPositionX = positionX;
    }

    public void draw() {
        int transitionPositionX;
        if (lastPositionX != 0) {
            transitionPositionX = Math.round(lastPositionX + (positionX - lastPositionX) * Main.getInterpolationProgress());
        } else {
            transitionPositionX = positionX;
        }
        Main.getDisplay().getDisplayPanel().drawImage(pathToImage, transitionPositionX, positionY);
    }
}
