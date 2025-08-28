package scenes.objects;

import main.Main;

import settings.*;

public class Text extends ObjectTemplate {
    String text;
    String pathToImages;
    int symbolWidth;

    public Text(int positionX, int positionY, String text, String pathToImages, int symbolWidth) {
        super(positionX, positionY);
        this.text = text;
        this.pathToImages = pathToImages;
        this.symbolWidth = symbolWidth;
    }

    public void draw() {
        int startPositionX = positionX - (text.length() - 1) * symbolWidth / 2;
        for (int i = 0; i < text.length(); i++) {
            Main.getDisplay().getDisplayPanel().drawImage(pathToImages + text.charAt(i) + Settings.imageFile, startPositionX + i * symbolWidth, positionY);
        }
    }

    public void setText(String Text) {
        this.text = Text;
    }
}
