package scenes;

import java.util.ArrayList;
import java.util.Arrays;

import scenes.objects.*;

public class LevelGame extends SceneTemplate {
    public LevelGame() {
        ArrayList<String> pathToHullImages = new ArrayList<String>();
        ArrayList<String> pathToSailImages = new ArrayList<String>();
        ArrayList<String> pathToWindImages = new ArrayList<String>();
        pathToHullImages.addAll(Arrays.asList("res/textures/yacht/hull/0.png", "res/textures/yacht/hull/45.png", "res/textures/yacht/hull/45.png", "res/textures/yacht/hull/90.png", "res/textures/yacht/hull/90.png", "res/textures/yacht/hull/135.png", "res/textures/yacht/hull/135.png", "res/textures/yacht/hull/180.png", "res/textures/yacht/hull/180.png", "res/textures/yacht/hull/225.png", "res/textures/yacht/hull/225.png", "res/textures/yacht/hull/270.png", "res/textures/yacht/hull/270.png", "res/textures/yacht/hull/315.png", "res/textures/yacht/hull/315.png", "res/textures/yacht/hull/0.png"));
        pathToSailImages.addAll(Arrays.asList("res/textures/yacht/sail/0.png", "res/textures/yacht/sail/45.png", "res/textures/yacht/sail/45.png", "res/textures/yacht/sail/90.png", "res/textures/yacht/sail/90.png", "res/textures/yacht/sail/135.png", "res/textures/yacht/sail/135.png", "res/textures/yacht/sail/180.png", "res/textures/yacht/sail/180.png", "res/textures/yacht/sail/225.png", "res/textures/yacht/sail/225.png", "res/textures/yacht/sail/270.png", "res/textures/yacht/sail/270.png", "res/textures/yacht/sail/315.png", "res/textures/yacht/sail/315.png", "res/textures/yacht/sail/0.png"));
        pathToWindImages.addAll(Arrays.asList("res/textures/1.png"));

        objects.add(new Yacht(100, 100, pathToHullImages, pathToSailImages, pathToWindImages, 100, 100, 0, 270, 2f, 0));
    }
}
