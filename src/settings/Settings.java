package settings;

public class Settings {
    public static final int ticksPerSecond = 20;
    public static final int framesPerSecond = 120;
    public static final int windowWidth = 640;
    public static final int windowHeight = 360;
    public static final String title = "MiniRegatta";
    public static final int hullRotationSpeed = 3;
    public static final float sailEasingSpeed = .05f;
    public static final int headToWindHullRotationSpeed = 18;
    public static final int notHeadToWindDegrees = 150;

    public interface ClickFunctionInterface {
        void clickFunction();
    }
}
