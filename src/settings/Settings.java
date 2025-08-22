package settings;

public class Settings {
    public static final int ticksPerSecond = 20;
    public static final int framesPerSecond = 120;
    public static final int windowWidth = 400;
    public static final int windowHeight = 225;
    public static final String title = "MiniRegatta";
    public static final float rotationSpeedToSpeedRatio = (float) 0.2;
    public static final float sheetSpeed = (float) 0.2;

    public interface ClickFunctionInterface {
        void clickFunction();
    }
}
