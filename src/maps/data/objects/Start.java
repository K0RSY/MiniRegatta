package maps.data.objects;

import java.io.Serializable;

public class Start implements Serializable {
    public int positionX;
    public int positionY;
    public int lenght;
    public int startX;
    public int startY;
    public boolean invertFinish;

    public Start(int positionX, int positionY, int lenght, int startX, int startY, boolean invertFinish) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.lenght = lenght;
        this.startX = startX;
        this.startY = startY;
        this.invertFinish = invertFinish;
    }
}
