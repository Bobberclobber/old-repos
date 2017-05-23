package se.scramble_studios.nehro.simple_platform_game.java.core;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

/**
 * Created by Josef on 28/03/2015.
 * <p>
 *     A timer class used to calculate delta time, FPS and UPS
 * </p>
 */
public class GameTimer {
    // System time since last loop
    private double lastLoopTime;

    // Used for FPS and UPS calculation
    private float timeCount;

    // Frames per second
    private int fps;

    // Counter for the FPS calculation
    private int fpsCount;

    // Updates per second
    private int ups;

    // Counter for the UPS calculation
    private int upsCount;

    // Initializes the timer
    public void init() {
        lastLoopTime = getTime();
    }

    // Returns the time since glfwInit in seconds
    public double getTime() {
        return glfwGetTime();
    }

    // Returns the time that has passed since the last loop
    public float getDelta() {
        double time = getTime();
        float delta = (float) (time - lastLoopTime);
        lastLoopTime = time;
        timeCount += delta;
        return delta;
    }

    // Updates the FPS counter
    public void updateFPS() {
        fpsCount++;
    }

    // Updates the UPS counter
    public void updateUPS() {
        upsCount++;
    }

    // Updates FPS and UPS if a whole second has passed
    public void update() {
        if (timeCount > 1f) {
            fps = fpsCount;
            fpsCount = 0;

            ups = upsCount;
            upsCount = 0;

            timeCount -= 1f;
        }
    }

    // Getter for the FPS
    public int getFPS() {
        return fps > 0 ? fps : fpsCount;
    }

    // Getter for the UPS
    public int getUPS() {
        return ups > 0 ? ups : upsCount;
    }

    // Getter for the last loop time
    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
