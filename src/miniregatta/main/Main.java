package miniregatta.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;

import miniregatta.display.*;
import miniregatta.maps.save.Save;
import miniregatta.scenes.*;
import miniregatta.settings.*;
import miniregatta.speaker.*;

public class Main {
    static SceneTemplate currentScene = new MainMenu();
    static Display display = new Display();
    static Speaker speaker = new Speaker();

    static boolean run = true;
    static long lastProcessesEndTimeNano = System.nanoTime();
    static long processesEndTimeNano;
    static long accumulatorTimeNano = 0;
    static long deltaTimeNano;
    static float interpolationProgress;
    static final long nanoPerTick = (long) (1000000000 / Settings.ticksPerSecond);
    static final long nanoPerFrame = (long) (1000000000 / Settings.framesPerSecond);
    static ArrayList<String> loadedMaps;

    public static void main(String[] args) {
        checkMouseDogFile();

        SwingUtilities.invokeLater(display);

        speaker.start();
        speaker.addSoundToQueue("/resources/sounds/init.wav");

        createSaveFile();

        loadMaps();

        mainLoop();
    }

    public static void createSaveFile() {
        File saveFile = new File(Settings.saveFilePath);
        
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
                
                FileOutputStream fos = new FileOutputStream(Settings.saveFilePath);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                
                Save save = new Save();
                oos.writeObject(save);
        
                oos.close();
                bos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadMaps() {
        try {
            loadedMaps = new ArrayList<String>();
            URI mapDirectoryURI = Main.class.getResource(Settings.mapPath).toURI();
            Path mapPath;
            FileSystem fileSystem = null;
            if (mapDirectoryURI.getScheme().equals("jar")) {
                fileSystem = FileSystems.newFileSystem(mapDirectoryURI, Collections.<String, Object>emptyMap());
                mapPath = fileSystem.getPath(Settings.mapPath);
            } else {
                mapPath = Paths.get(mapDirectoryURI);
            }
            Stream<Path> walk = Files.walk(mapPath, 1);
            for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
                String[] pathList = ("" + it.next()).split("/");
                loadedMaps.add(pathList[pathList.length - 1]);
            }
            loadedMaps.remove(0);
            walk.close();
            if (fileSystem != null) {
                fileSystem.close();
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void mainLoop() {
        while (run) {
            processesEndTimeNano = System.nanoTime();
            deltaTimeNano = (processesEndTimeNano - lastProcessesEndTimeNano);
            interpolationProgress += (double) (deltaTimeNano % nanoPerTick) / nanoPerTick;
            interpolationProgress %= (double) 1.0;

            lastProcessesEndTimeNano += deltaTimeNano;
            accumulatorTimeNano += deltaTimeNano;

            while (accumulatorTimeNano > nanoPerTick) {
                currentScene.tick();
                accumulatorTimeNano -= nanoPerTick;
            }
            
            display.tick();

            try {
                TimeUnit.NANOSECONDS.sleep(nanoPerFrame - deltaTimeNano);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public static void checkMouseDogFile() {
        URL mouseDogURL = Main.class.getResource("/resources/mouse_dog.png");
        if (mouseDogURL == null) {
            throw new java.lang.Error("Mouse-dog image not found");
        }
    }

    public static SceneTemplate getCurrentScene() {
        return currentScene;
    }

    public static Display getDisplay() {
        return display;
    }

    public static Speaker getSpeaker() {
        return speaker;
    }

    public static float getInterpolationProgress() {
        return interpolationProgress;
    }

    public static void changeCurrentScene(SceneTemplate newScene) {
        currentScene = newScene;
    }

    public static ArrayList<String> getLoadedMaps() {
        return loadedMaps;
    }

    public static void stopMainLoop() {
        System.exit(0);
    }

    public static long getDeltaTime() {
        return deltaTimeNano;
    }
}
