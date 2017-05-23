package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

/**
 * Created by Josef on 06/06/2015.
 * <p>
 *     The main game window
 * </p>
 */
public class MainWindow {
    // Window handle
    private long window;

    // VSync
    private boolean vsync;

    // Dimensions
    private int width, height;

    public MainWindow(boolean vsync) {
        this.width = 1000;
        this.height = 800;
        this.vsync = vsync;

        // Setup the window
        setup();
    }

    public MainWindow(boolean vsync, int width, int height) {
        this.width = width;
        this.height = height;
        this.vsync = vsync;

        // Setup the window
        setup();
    }

    public MainWindow(int width, int height, boolean vsync) {
        this.width = width;
        this.height = height;
        this.vsync = vsync;

        // Setup the window
        setup();
    }

    private void setup() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
    }
}
