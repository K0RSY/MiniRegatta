package scenes.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Buoy extends AnimatedImage {
    int width = 0;
    int height = 0;

    boolean flagged = false;

    int crossQuarterX;
    int crossQuarterY;
    
    int startCrossQuarterX;
    int startCrossQuarterY;

    int endCrossQuarterX;
    int endCrossQuarterY;

    boolean crossByLeftSide;

    public Buoy(int positionX, int positionY, int width, int height, HashMap<String, ArrayList<String>> animations, HashMap<String, Boolean> loops, int ticksPerImage, int crossQuarterX, int crossQuarterY, boolean crossByLeftSide) {
        super(positionX, positionY, animations, loops, ticksPerImage);

        this.width = width;
        this.height = height;

        this.crossQuarterX = crossQuarterX;
        this.crossQuarterY = crossQuarterY;
        this.crossByLeftSide = crossByLeftSide;

        if (crossQuarterX + crossQuarterY == 0) {
            startCrossQuarterX = -crossQuarterX;
            startCrossQuarterY = crossQuarterY;

            endCrossQuarterX = crossQuarterX;
            endCrossQuarterY = -crossQuarterY;
        } else {
            startCrossQuarterX = crossQuarterX;
            startCrossQuarterY = -crossQuarterY;

            endCrossQuarterX = -crossQuarterX;
            endCrossQuarterY = crossQuarterY;
        }

        if (crossByLeftSide) {
            int bufferQuarterX = endCrossQuarterX;
            int bufferQuarterY = endCrossQuarterY;

            endCrossQuarterX = startCrossQuarterX;
            endCrossQuarterY = startCrossQuarterY;

            startCrossQuarterX = bufferQuarterX;
            startCrossQuarterY = bufferQuarterY;
        }
    }
    public void draw() {
        if (flagged) {
            if (currentAnimationKey == "flag") {
                if (getCurrentAnimationStopped()) {
                    startAnimation("flagged");
                }
            } else if (currentAnimationKey != "flagged") {
                startAnimation("flag");
            }
        }
        else {
            startAnimation("unflag");
        }

        super.draw();
    }

    public void toogleFlagged() {
        this.flagged = !this.flagged;
    }

    public int getQuarterX(int positionX) {
        return (positionX > this.positionX) ? 1 : -1;
    }

    public int getQuarterY(int positionY) {
        return (positionY > this.positionY) ? 1 : -1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public boolean getInStartCrossQuarter(int quarterX, int quarterY) {
        return quarterX == this.startCrossQuarterX && quarterY == this.startCrossQuarterY;
    }

    public boolean getInEndCrossQuarter(int quarterX, int quarterY) {
        return quarterX == this.endCrossQuarterX && quarterY == this.endCrossQuarterY;
    }

    public boolean getInCrossQuarter(int quarterX, int quarterY) {
        return quarterX == this.crossQuarterX && quarterY == this.crossQuarterY;
    }
}
