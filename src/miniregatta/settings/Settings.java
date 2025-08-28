package miniregatta.settings;

public class Settings {
    public static final int ticksPerSecond = 20;
    public static final int framesPerSecond = 60;
    public static final int windowWidth = 640;
    public static final int windowHeight = 360;
    public static final String title = "MiniRegatta";
    public static final String imageFile = ".png";
    public static final String mapPath = "/resources/maps/";
    public static final int hullRotationSpeed = 3;
    public static final float sailEasingSpeed = .05f;
    public static final int headToWindHullRotationSpeed = 18;
    public static final int notHeadToWindDegrees = 150;
    public static final int warningTime = 50;
    public static final int buoyCollisionAddTime = 100;
    public static final float buoyCollisionDistaceMultiplier = 1.2f;
    public static final int falseStartCollisionAddTime = 200;
    public static final int transitionSpeed = 160;
    public static final float transitionSpeedMultiplier = 0.85f;

    public interface ClickFunctionInterface {
        void clickFunction();
    }
}
