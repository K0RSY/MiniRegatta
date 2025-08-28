package miniregatta.maps.data.objects;

import java.io.Serializable;

public class Buoy implements Serializable {
    public int positionX;
    public int positionY;
    public int crossQuarter;
    public boolean crossByLeftSide;

    public Buoy(int positionX, int positionY, int crossQuarter, boolean crossByLeftSide) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.crossQuarter = crossQuarter;
        this.crossByLeftSide = crossByLeftSide;
    }
}
