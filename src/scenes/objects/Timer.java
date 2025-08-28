package scenes.objects;

import main.Main;
import settings.Settings;

public class Timer extends Text {
    int maxStartTime;
    int time;
    int warningTime;
    int addTime;
    int startTime;
    String warning;

    public Timer(int positionX, int positionY, int size, int startTime, String pathToImages, int symbolWidth) {
        super(positionX, positionY, "", pathToImages, symbolWidth);
        this.startTime = startTime;
    }

    public void tick() {
        if (startTime == 0) {
            time++;
            text = getFormattedTime(time);
        } else {
            startTime--;
            text = "" + (startTime / 20 + 1);
            if (startTime % 20 == 0 && startTime >= 20) {
                Main.getSpeaker().addSoundToQueue("res/sounds/countdown.wav");
            }
        }

        if (warningTime > 0) {
            warningTime -= 1;
            text += "\n+" + addTime / 20 + " " + warning;
        }
    }

    public String getFormattedTime(int time) {
        String minutes = "" + (time / 20) / 60;
        String seconds = "" + (time / 20) % 60;

        minutes = "0".repeat(2 - minutes.length()) + minutes;
        seconds = "0".repeat(2 - seconds.length()) + seconds;

        return minutes + ":" + seconds;
    }

    public int getTime() {
        return time;
    }

    public boolean startTimeOut() {
        return startTime == 0;
    }

    public boolean getWarningTimeOut() {
        return warningTime == 0;
    }

    public void addTime(int time, String warning) {
        this.time += time;
        addTime = time;
        warningTime = Settings.warningTime;
        this.warning = warning;
    }
}
