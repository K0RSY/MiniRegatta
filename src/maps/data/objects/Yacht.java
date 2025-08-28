package maps.data.objects;

import java.io.Serializable;

public class Yacht implements Serializable {
    public int positionX;
    public int positionY;
    public int hullRotationDegrees;
    public int windRotationDegrees;
    public float sailEase;
    public float windSpeed;

    public Yacht(int positionX, int positionY, int hullRotationDegrees, int windRotationDegrees, float sailEase, float windSpeed) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.hullRotationDegrees = hullRotationDegrees;
        this.windRotationDegrees = windRotationDegrees;
        this.sailEase = sailEase;
        this.windSpeed = windSpeed;
    }
}
