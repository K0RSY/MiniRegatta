package scenes;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import scenes.objects.ObjectTemplate;

public class SceneTemplate {
    LinkedHashMap<String, ArrayList<ObjectTemplate>> objects = new LinkedHashMap<String, ArrayList<ObjectTemplate>>();

    public void tick() {
        for (String objectKey : objects.keySet()) {
            for (int i = 0; i < objects.get(objectKey).size(); i++) {
                objects.get(objectKey).get(i).tick();
            }
        }
    }

    public LinkedHashMap<String, ArrayList<ObjectTemplate>> getObjects() {
        return objects;
    }

    public void addKey(String key) {
        objects.put(key, new ArrayList<ObjectTemplate>());
    }
}
