package se.scramble_studios.nehro.simple_platform_game.java;

import org.lwjgl.glfw.GLFW;
import se.scramble_studios.nehro.simple_platform_game.java.core.GameLogic;
import se.scramble_studios.nehro.simple_platform_game.java.core.VariableTimestepGame;

/**
 * Created by Josef on 22/02/2015.
 * <p>
 *     The main class
 * </p>
 */
public class PlatformMain {

    // Main function
    public static void main(String[] args) {
        GameLogic gl = new VariableTimestepGame();
        try {
            gl.start();
        } finally {
            // GLFW has to be terminated or the application will run in the background
            GLFW.glfwTerminate();
        }
    }
}
