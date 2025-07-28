package main;

import java.io.File;

import display.*;
import engine.*;
import reader.*;
import settings.*;

public class Main {
    static Engine engine = new Engine();
    static Display display = new Display();
    static Reader reader = new Reader();

    static boolean run = true;
    static long lastProcessesEndTimeNano = System.nanoTime();
    static long processesEndTimeNano;
    static long accumulatorTimeNano = 0;
    static double deltaTimeSeconds;
    static final long nanoPerTick = (long) (1000000000 / Settings.ticksPerSecond);

    public static void main(String[] args) {
        checkMouseDogFile();
        mainLoop();
    }

    public static void mainLoop() {
        while (run) {
            processesEndTimeNano = System.nanoTime();
            deltaTimeSeconds = (double) (processesEndTimeNano - lastProcessesEndTimeNano) / 1000000000;

            lastProcessesEndTimeNano += (long) (deltaTimeSeconds * 1000000000);
            accumulatorTimeNano += (long) (deltaTimeSeconds * 1000000000);

            reader.tick();
            while (accumulatorTimeNano > nanoPerTick) {
                engine.tick();
                accumulatorTimeNano -= nanoPerTick;
            }
            display.tick();
        }
    }

    public static void checkMouseDogFile() {
        File mouse_dog_file = new File("res/mouse_dog.png");
        if(!mouse_dog_file.exists() || mouse_dog_file.isDirectory()) {
            throw new java.lang.Error("Mouse-dog image not found");
        }
    }

    public static Engine getEngine() {
        return engine;
    }

    public static Display getDisplay() {
        return display;
    }

    public static Reader getReader() {
        return reader;
    }

    public static double getDeltaTimeSeconds() {
        return deltaTimeSeconds;
    }
}
