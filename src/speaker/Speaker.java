package speaker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import settings.Settings;

public class Speaker extends Thread {
    ArrayList<String> soundQueue = new ArrayList<String>();
    static final long nanoPerFrame = (long) (1000000000 / Settings.framesPerSecond);

    public void playSound(String pathToSound) {
        try {
            URL soundURL = getClass().getResource(pathToSound);

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);

            Clip clip = AudioSystem.getClip();

            clip.open(ais);
            
            clip.setFramePosition(0);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }

    public void addSoundToQueue(String pathToSound) {
        soundQueue.add(pathToSound);
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.NANOSECONDS.sleep(nanoPerFrame);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (soundQueue.size() >= 1) {
                playSound(soundQueue.get(0));
                soundQueue.remove(0);
            }
        }
    }
}
