package miniregatta.scenes.objects;

import miniregatta.main.Main;
import miniregatta.settings.Settings;

public class TextButton extends Text {
    String pathToBackgroundImage = "";
    String pathToActiveBackgroundImage = "";
    int width;
    int height;
    boolean active = false;
    Settings.ClickFunctionInterface clickFunctionInterface = () -> {};

    public TextButton(int positionX, int positionY, String text, String pathToImages, String pathToBackgroundImage, String pathToActiveBackgroundImage, int symbolWidth, Settings.ClickFunctionInterface clickFunctionInterface) {
        super(positionX, positionY, text, pathToImages, symbolWidth);
        this.pathToBackgroundImage = pathToBackgroundImage;
        this.pathToActiveBackgroundImage = pathToActiveBackgroundImage;
        this.clickFunctionInterface = clickFunctionInterface;

        width = (text.length() + 2) * symbolWidth;
        height = symbolWidth * 3;
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
                Main.getSpeaker().addSoundToQueue("/resources/sounds/click.wav");
                clickFunctionInterface.clickFunction();
            }
        }
        else {
            active = false;
        }
    }

    public void draw() {
        String currentPathToImage = active ? pathToActiveBackgroundImage : pathToBackgroundImage;


        int startPositionX = positionX - (text.length() - 1) * symbolWidth / 2;

        for (int i = 0; i < text.length(); i++) {
            Main.getDisplay().getDisplayPanel().drawImage(currentPathToImage, startPositionX + i * symbolWidth, positionY);
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                String textureName;
                String character = "" + text.charAt(i);
                if (character.equals(".")) {
                    textureName = "dot";
                } else if (character.equals("+")) {
                    textureName = "plus";
                } else if (character.equals(" ")) {
                    continue;
                } else {
                    textureName = character;
                }
                textureName += Settings.imageFile;
                Main.getDisplay().getDisplayPanel().drawImage(pathToImages + textureName, startPositionX + i * symbolWidth, positionY);
            }
        }
    }

    public void setClickFunctionInterface(Settings.ClickFunctionInterface clickFunctionInterface) {
        this.clickFunctionInterface = clickFunctionInterface;
    }

    public void setText(String text) {
        this.text = text;
    }
}
