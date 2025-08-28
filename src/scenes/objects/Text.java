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
        String[] textLines = text.split("\\r?\\n");

        for (int lineCounter = 0; lineCounter < textLines.length; lineCounter++) {
            int startPositionX = positionX - (textLines[lineCounter].length() - 1) * symbolWidth / 2;
            for (int i = 0; i < textLines[lineCounter].length(); i++) {
                Main.getDisplay().getDisplayPanel().drawImage(pathToImages + textLines[lineCounter].charAt(i) + Settings.imageFile, startPositionX + i * symbolWidth, positionY + lineCounter * symbolWidth);
            }
        }
    }

    public void setText(String Text) {
        this.text = Text;
    }
}
