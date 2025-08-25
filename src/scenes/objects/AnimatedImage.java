package scenes.objects;

import java.util.ArrayList;

import main.Main;

public class AnimatedImage extends ObjectTemplate {
    ArrayList<String> pathToImages = new ArrayList<String>();
    int ticksPerImage = 1;
    int tickCounter = 0;
    int currentImageIndex = 0;

    public AnimatedImage(int positionX, int positionY, ArrayList<String> pathToImages, int ticksPerImage) {
        super(positionX, positionY);
        this.ticksPerImage = ticksPerImage;
        this.tickCounter = ticksPerImage;
        this.pathToImages = pathToImages;
    }

    public void tick() {
        if (tickCounter-- == 0) {
            currentImageIndex += 1;
            currentImageIndex = currentImageIndex <= pathToImages.size() - 1 ? currentImageIndex : 0;
            tickCounter = ticksPerImage;
        }
    }

    public void draw() {
        Main.getDisplay().getDisplayPanel().drawImage(pathToImages.get(currentImageIndex), positionX, positionY);
    }
    
    public void setPathToImages(ArrayList<String> pathToImages) {
        this.pathToImages = pathToImages;
    }

    public void setTicksPerImage(int ticksPerImage) {
        this.ticksPerImage = ticksPerImage;
    }

    public void setСurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }
}
