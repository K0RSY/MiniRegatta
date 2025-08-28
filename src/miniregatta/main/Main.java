package miniregatta.main;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import miniregatta.display.*;
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

    public static void main(String[] args) {
        checkMouseDogFile();
        speaker.start();
        Main.getSpeaker().addSoundToQueue("/resources/sounds/init.wav");
        mainLoop();
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
    }

    public static void checkMouseDogFile() {
        URL mouseDogURL = Main.class.getClassLoader().getResource("/resources/mouse_dog.png");
        if (mouseDogURL != null) {
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
}
