package main;

import java.io.File;
import java.util.concurrent.TimeUnit;

import display.*;
import scenes.*;
import settings.*;
import speaker.*;

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
        Main.getSpeaker().addSoundToQueue("res/sounds/init.wav");
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
        File mouse_dog_file = new File("res/mouse_dog.png");
        if(!mouse_dog_file.exists() || mouse_dog_file.isDirectory()) {
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
