package scenes.objects;

import main.Main;

public class Button extends Image {
    String pathToActiveImage = "";
    int width = 0;
    int height = 0;
    boolean active = false;

    public Button(int positionX, int positionY, String pathToImage, String pathToActiveImage, int width, int height) {
        super(positionX, positionY, pathToImage);
        this.pathToActiveImage = pathToActiveImage;
        this.width = width;
        this.height = height;
    }

    public void tick() {
        int clickX = Main.getDisplay().getButtonReader().getClickX();
        int clickY = Main.getDisplay().getButtonReader().getClickY();
        boolean pressed = Main.getDisplay().getButtonReader().getPressed();

        if (
            (positionX - width / 2 < clickX && clickX < positionX + width / 2) && 
            (positionY - height / 2 < clickY && clickY < positionY + height / 2)
        ) {
            if (pressed) {
                active = true;
            }
            else if (active == true) {
                active = false;
                click();
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

    public void click() {
        System.out.println("sex");
    }
}
