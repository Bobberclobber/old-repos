package se.scramble_studios.nehro.simple_platform_game.java.graphics;

import org.lwjgl.opengl.GLContext;
import se.scramble_studios.nehro.simple_platform_game.java.core.Const;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by Josef on 22/02/2015.
 * <p>
 *     The main game window
 * </p>
 */
public class GameWindow {
    // Window handle
    private long window;

    // VSync
    private boolean vsync;

    // Dimensions
    private int width, height;

    public GameWindow(boolean vsync) {
        this.width = Const.WINDOW_WIDTH;
        this.height = Const.WINDOW_HEIGHT;
        this.vsync = vsync;

        // Sets up the window
        setup();
    }

    public GameWindow(int width, int height, boolean vsync) {
        this.width = width;
        this.height = height;
        this.vsync = vsync;

        // Sets up the window
        setup();
    }

    private void setup() {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        long temp = glfwCreateWindow(1, 1, "", NULL, NULL);
        glfwMakeContextCurrent(temp);
        GLContext.createFromCurrent();
        String version = glGetString(GL_VERSION);
        glfwDestroyWindow(temp);
        int major = Character.getNumericValue(version.charAt(0));
        int minor = Character.getNumericValue(version.charAt(2));

        // Reset and set window hints
        glfwDefaultWindowHints();
        if (major > 3 || (major == 3 && minor >= 2)) {
            // Hints for OpenGL core profile
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        } else {
            // Hints for legacy OpenGL 2.1
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);
        }
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);

        // Create window
        window = glfwCreateWindow(width, height, Const.TITLE, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window!");
        }

        // Center window on screen
        glfwSetWindowPos(window, Const.MONITOR_MID_WIDTH, Const.MONITOR_MID_HEIGHT);

        // Create OpenGL Context
        glfwMakeContextCurrent(window);
        GLContext.createFromCurrent();

        // Enable vsync
        if (vsync)
            glfwSwapInterval(1);
    }

    // Returns true if the window is closing
    public boolean isClosing() {
        return glfwWindowShouldClose(window) == GL_TRUE;
    }

    // Updates the screen
    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    // Destroys all windows and releases its callbacks
    public void destroy() {
        glfwDestroyWindow(window);
    }

    // Setter for vsync
    public void setVsync(boolean vsync) {
        this.vsync = vsync;
        if (vsync)
            glfwSwapInterval(1);
        else
            glfwSwapInterval(0);
    }

    // Check if vsync is enabled
    public boolean isVSyncEnabled() {
        return vsync;
    }
}
