package scenes.objects;

import main.Main;

public class StartLine extends ObjectTemplate {
    int imageLength;
    int length;

    int startZoneX;
    int startZoneY;

    String pathToImage;
    String pathToFlaggedImage;

    boolean flagged;
    boolean invertFinish;

    int finishZoneX;
    int finishZoneY;

    public StartLine(int positionX, int positionY, String pathToImage, String pathToFlaggedImage, int imageLength, int length, int startZoneX, int startZoneY, boolean invertFinish) {
        super(positionX, positionY);
        this.imageLength = imageLength;

        this.length = length;

        this.startZoneX = startZoneX;
        this.startZoneY = startZoneY;

        this.pathToImage = pathToImage;
        this.pathToFlaggedImage = pathToFlaggedImage;

        finishZoneX = invertFinish ? -startZoneX : startZoneX;
        finishZoneY = invertFinish ? -startZoneY : startZoneY;
    }

    public void tick() {
    }

    public void draw() {
        int startPositionX = positionX - (length - 1) * imageLength * (1 - Math.abs(startZoneX)) / 2;
        int startPositionY = positionY - (length - 1) * imageLength * (1 - Math.abs(startZoneY)) / 2;

        String pathToCurrentImage;

        if (flagged) {
            pathToCurrentImage = pathToFlaggedImage;
        } else {
            pathToCurrentImage = pathToImage;
        }

        for (int i = 0; i < length; i++) {
            Main.getDisplay().getDisplayPanel().drawImage(pathToCurrentImage, startPositionX + imageLength * i * (1 - Math.abs(startZoneX)), startPositionY + imageLength * i * (1 - Math.abs(startZoneY)));
        }
    }

    public boolean getInPreStartZone(int positionX, int positionY) {
        return 
            (positionY) * Math.abs(-startZoneX) >= (this.positionY - length * imageLength / 2) * Math.abs(-startZoneX) &&
            (positionY) * Math.abs(-startZoneX) <= (this.positionY + length * imageLength / 2) * Math.abs(-startZoneX) &&
            (positionX) * Math.abs(-startZoneY) >= (this.positionX - length * imageLength / 2) * Math.abs(-startZoneY) &&
            (positionX) * Math.abs(-startZoneY) <= (this.positionX + length * imageLength / 2) * Math.abs(-startZoneY) &&
            positionX * -startZoneX >= this.positionX * -startZoneX &&
            positionY * -startZoneY >= this.positionY * -startZoneY;
    }

    public boolean getInPreFinishZone(int positionX, int positionY) {
        return 
            (positionY) * Math.abs(-finishZoneX) >= (this.positionY - length * imageLength / 2) * Math.abs(-finishZoneX) &&
            (positionY) * Math.abs(-finishZoneX) <= (this.positionY + length * imageLength / 2) * Math.abs(-finishZoneX) &&
            (positionX) * Math.abs(-finishZoneY) >= (this.positionX - length * imageLength / 2) * Math.abs(-finishZoneY) &&
            (positionX) * Math.abs(-finishZoneY) <= (this.positionX + length * imageLength / 2) * Math.abs(-finishZoneY) &&
            positionX * -finishZoneX >= this.positionX * -finishZoneX &&
            positionY * -finishZoneY >= this.positionY * -finishZoneY;
    }

    public boolean getInStartZone(int positionX, int positionY) {
        return 
            (positionY) * Math.abs(startZoneX) >= (this.positionY - length * imageLength / 2) * Math.abs(startZoneX) &&
            (positionY) * Math.abs(startZoneX) <= (this.positionY + length * imageLength / 2) * Math.abs(startZoneX) &&
            (positionX) * Math.abs(startZoneY) >= (this.positionX - length * imageLength / 2) * Math.abs(startZoneY) &&
            (positionX) * Math.abs(startZoneY) <= (this.positionX + length * imageLength / 2) * Math.abs(startZoneY) &&
            positionX * startZoneX >= this.positionX * startZoneX &&
            positionY * startZoneY >= this.positionY * startZoneY;
    }

    public boolean getInFinishZone(int positionX, int positionY) {
        return 
            (positionY) * Math.abs(finishZoneX) >= (this.positionY - length * imageLength / 2) * Math.abs(finishZoneX) &&
            (positionY) * Math.abs(finishZoneX) <= (this.positionY + length * imageLength / 2) * Math.abs(finishZoneX) &&
            (positionX) * Math.abs(finishZoneY) >= (this.positionX - length * imageLength / 2) * Math.abs(finishZoneY) &&
            (positionX) * Math.abs(finishZoneY) <= (this.positionX + length * imageLength / 2) * Math.abs(finishZoneY) &&
            positionX * finishZoneX >= this.positionX * finishZoneX &&
            positionY * finishZoneY >= this.positionY * finishZoneY;
    }

    public void toogleFlagged() {
        this.flagged = !this.flagged;
    }
}
