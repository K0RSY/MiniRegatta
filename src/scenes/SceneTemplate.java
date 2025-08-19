package scenes;

import java.util.ArrayList;
import java.util.List;

import scenes.objects.ObjectTemplate;

public class SceneTemplate {
    List<ObjectTemplate> objects = new ArrayList<ObjectTemplate>();

    public void tick() {
        for (ObjectTemplate object : objects) {
            object.tick();
        }
    }

    public List<ObjectTemplate> getObjects() {
        return objects;
    }
}
