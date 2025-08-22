package scenes.objects;

import main.Main;
import settings.Settings;

public class Button extends Image {
    String pathToActiveImage = "";
    int width = 0;
    int height = 0;
    boolean active = false;
    Settings.ClickFunctionInterface clickFunctionInterface = () -> {};

    public Button(int positionX, int positionY, String pathToImage, String pathToActiveImage, int width, int height, Settings.ClickFunctionInterface clickFunctionInterface) {
        super(positionX, positionY, pathToImage);
        this.pathToActiveImage = pathToActiveImage;
        this.width = width;
        this.height = height;
        this.clickFunctionInterface = clickFunctionInterface;
    }

    public void tick() {
        int mousePositionX = Main.getDisplay().getMouseReader().getPositionX();
        int mousePositionY = Main.getDisplay().getMouseReader().getPositionY();
        boolean pressed = Main.getDisplay().getButtonReader().getPressed();

        if (
            (positionX - width / 2 < mousePositionX && mousePositionX < positionX + width / 2) && 
            (positionY - height / 2 < mousePositionY && mousePositionY < positionY + height / 2)
        ) {
            if (pressed) {
                active = true;
            }
            else if (active == true) {
                active = false;
                clickFunctionInterface.clickFunction();
            }
        }
        else {
            active = false;
        }
    }

    public void draw() {
        String pathToCurrentImage;

        if (active) {
            pathToCurrentImage = pathToActiveImage;
        }
        else {
            pathToCurrentImage = pathToImage;
        }

        Main.getDisplay().getDisplayPanel().drawImage(pathToCurrentImage, positionX, positionY);
    }

    public void setPathToActiveImage(String pathToActiveImage) {
        this.pathToActiveImage = pathToActiveImage;
    }
}
