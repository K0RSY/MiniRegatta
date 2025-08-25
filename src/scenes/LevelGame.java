package scenes;

import java.util.ArrayList;
import java.util.Arrays;

import scenes.objects.*;

public class LevelGame extends SceneTemplate {
    public LevelGame() {
        ArrayList<String> pathToHullImages = new ArrayList<String>();
        ArrayList<String> pathToSailImages = new ArrayList<String>();
        ArrayList<String> pathToWindImages = new ArrayList<String>();
        pathToHullImages.addAll(Arrays.asList("res/textures/yacht/hull/0.png", "res/textures/yacht/hull/23.png", "res/textures/yacht/hull/45.png", "res/textures/yacht/hull/68.png", "res/textures/yacht/hull/90.png", "res/textures/yacht/hull/113.png", "res/textures/yacht/hull/135.png", "res/textures/yacht/hull/158.png", "res/textures/yacht/hull/180.png", "res/textures/yacht/hull/203.png", "res/textures/yacht/hull/225.png", "res/textures/yacht/hull/248.png", "res/textures/yacht/hull/270.png", "res/textures/yacht/hull/293.png", "res/textures/yacht/hull/315.png", "res/textures/yacht/hull/338.png"));
        pathToSailImages.addAll(Arrays.asList("res/textures/yacht/sail/0.png", "res/textures/yacht/sail/23.png", "res/textures/yacht/sail/45.png", "res/textures/yacht/sail/68.png", "res/textures/yacht/sail/90.png", "res/textures/yacht/sail/113.png", "res/textures/yacht/sail/135.png", "res/textures/yacht/sail/158.png", "res/textures/yacht/sail/180.png", "res/textures/yacht/sail/203.png", "res/textures/yacht/sail/225.png", "res/textures/yacht/sail/248.png", "res/textures/yacht/sail/270.png", "res/textures/yacht/sail/293.png", "res/textures/yacht/sail/315.png", "res/textures/yacht/sail/338.png"));
        pathToWindImages.addAll(Arrays.asList("res/textures/yacht/wind/0.png", "res/textures/yacht/wind/23.png", "res/textures/yacht/wind/45.png", "res/textures/yacht/wind/68.png", "res/textures/yacht/wind/90.png", "res/textures/yacht/wind/113.png", "res/textures/yacht/wind/135.png", "res/textures/yacht/wind/158.png", "res/textures/yacht/wind/180.png", "res/textures/yacht/wind/203.png", "res/textures/yacht/wind/225.png", "res/textures/yacht/wind/248.png", "res/textures/yacht/wind/270.png", "res/textures/yacht/wind/293.png", "res/textures/yacht/wind/315.png", "res/textures/yacht/wind/338.png"));
        
        objects.add(new Image(320, 180, "res/textures/background/1.png"));
        objects.add(new Yacht(100, 100, pathToHullImages, pathToSailImages, pathToWindImages, 100, 100, 315, 270, 2f, 180));
    }
}
