package scenes.objects;

import java.util.ArrayList;
import java.util.HashMap;

import main.Main;

public class AnimatedImage extends ObjectTemplate {
    HashMap<String, ArrayList<String>> animations;
    HashMap<String, Boolean> loops;
    String currentAnimationKey;
    int ticksPerImage = 1;
    int tickCounter = 0;
    int currentImageIndex = 0;

    public AnimatedImage(int positionX, int positionY, HashMap<String, ArrayList<String>> animations, HashMap<String, Boolean> loops, int ticksPerImage) {
        super(positionX, positionY);
        this.ticksPerImage = ticksPerImage;
        this.tickCounter = ticksPerImage;
        this.animations = animations;
        this.loops = loops;
    }

    public void tick() {
        ArrayList<String> currentAnimation = animations.get(currentAnimationKey);

        if (tickCounter-- == 0) {
            currentImageIndex += 1;
            if (currentImageIndex > currentAnimation.size() - 1) {
                if (loops.get(currentAnimationKey)) {
                    currentImageIndex = 0;
                } else {
                    currentImageIndex = currentAnimation.size() - 1;
                }
            }
            tickCounter = ticksPerImage;
        }
    }

    public void draw() {
        Main.getDisplay().getDisplayPanel().drawImage(animations.get(currentAnimationKey).get(currentImageIndex), positionX, positionY);
    }

    public void resetAnimation() {
        tickCounter = ticksPerImage;
        currentImageIndex = 0;
    }

    public void startAnimation(String animationKey) {
        if (animationKey != currentAnimationKey) {
            currentAnimationKey = animationKey;
            resetAnimation();
        }
    }

    public boolean getCurrentAnimationStopped() {
        return !loops.get(currentAnimationKey) && currentImageIndex == animations.get(currentAnimationKey).size() - 1;
    }

    public String getCurrentAnimationKey() {
        return currentAnimationKey;
    }

    public void setTicksPerImage(int ticksPerImage) {
        this.ticksPerImage = ticksPerImage;
    }

    public void setСurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }
}
