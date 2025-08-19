package scenes.objects;

public class Button extends Image {
    String pathToActiveImage = "";

    public Button(int positionX, int positionY, String pathToImage, String pathToActiveImage) {
        super(positionX, positionY, pathToImage);
        this.pathToActiveImage = pathToActiveImage;
    }

    public void tick() {

    }

    public void setPathToActiveImage(String pathToActiveImage) {
        this.pathToActiveImage = pathToActiveImage;
    }

    public void click() {
        
    }
}
