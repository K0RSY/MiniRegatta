package miniregatta.scenes.objects;

import miniregatta.main.Main;
import miniregatta.settings.*;

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
                String textureName;
                String character = "" + textLines[lineCounter].charAt(i);
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
                Main.getDisplay().getDisplayPanel().drawImage(pathToImages + textureName, startPositionX + i * symbolWidth, positionY + lineCounter * symbolWidth);
            }
        }
    }

    public void setText(String Text) {
        this.text = Text;
    }
}
