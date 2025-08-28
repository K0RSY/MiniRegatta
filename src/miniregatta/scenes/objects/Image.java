package miniregatta.scenes.objects;

import miniregatta.main.Main;

public class Image extends ObjectTemplate {
    String pathToImage = "";
    boolean isPressed = false;

    public Image(int positionX, int positionY, String pathToImage) {
        super(positionX, positionY);
        this.pathToImage = pathToImage;
    }

    public void draw() {
        Main.getDisplay().getDisplayPanel().drawImage(pathToImage, positionX, positionY);
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}
