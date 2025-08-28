package scenes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import main.Main;
import scenes.objects.*;
import settings.Settings;

public class Transition extends SceneTemplate {
    SceneTemplate newScene;
    boolean turn = false;

    public Transition(SceneTemplate newScene) {
        this.newScene = newScene;
        addTransition(objects);
    }

    public void addTransition(LinkedHashMap<String, ArrayList<ObjectTemplate>> objects) {
        objects.put("transition", new ArrayList<ObjectTemplate>());

        objects.get("transition").add(new Image(-Settings.windowWidth / 2, Settings.windowHeight / 2, "res/textures/transition.png"));
    }

    public void tick() {
        int positionX = objects.get("transition").get(0).getPositionX();

        if (positionX == Settings.windowWidth * 0.5) {
            objects = newScene.objects;
            addTransition(objects);
            objects.get("transition").get(0).setPositionX((int) (Settings.windowWidth * 0.5 + 1));
            turn = true;
        } else if (positionX < Settings.windowWidth * 0.5 && !turn) {
            positionX += Settings.transitionSpeed;
            objects.get("transition").get(0).setPositionX(positionX);
        } else if (positionX > -Settings.windowWidth * 0.5 && turn) {
            positionX -= Settings.transitionSpeed;
            objects.get("transition").get(0).setPositionX(positionX);
        } else {
            objects.remove("transition");
            Main.changeCurrentScene(newScene);
        }
    }
}
