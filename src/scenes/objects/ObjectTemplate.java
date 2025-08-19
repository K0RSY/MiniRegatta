package scenes.objects;

public class ObjectTemplate {
    int positionX = 0;
    int positionY = 0;

    public ObjectTemplate(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void tick() {}

    public void draw() {}

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
