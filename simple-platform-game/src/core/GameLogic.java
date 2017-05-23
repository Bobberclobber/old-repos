package se.scramble_studios.nehro.simple_platform_game.java.core;

import org.lwjgl.glfw.GLFWErrorCallback;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.GameWindow;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.Renderer;
import se.scramble_studios.nehro.simple_platform_game.java.state.GameState;
import se.scramble_studios.nehro.simple_platform_game.java.state.StateMachine;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Josef on 22/02/2015.
 * <p>
 *     Collects all the game logic in a single class
 * </p>
 */
public abstract class GameLogic {
    private GLFWErrorCallback errorCallback;

    // Shows if the game is running
    protected boolean running;

    // Window used by the game
    protected GameWindow window;

    // Used for timing calculations
    protected GameTimer timer;

    // Used for rendering
    protected Renderer renderer;

    // Stores the current state
    protected StateMachine state;

    // Default constructor
    public GameLogic() {
        timer = new GameTimer();
        renderer = new Renderer();
        state = new StateMachine();
    }

    // Called to init and start the game
    public void start() {
        init();
        gameLoop();
        dispose();
    }

    // Initializes the game
    public void init() {
        // Setup error callbacks
        glfwSetErrorCallback(errorCallback = errorCallbackPrint());

        // Initialize GLFW
        if (glfwInit() != GL_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW!");

        // Create GLFW window
        window = new GameWindow(true);

        // Initialize timer
        timer.init();

        // Initialize renderer
        renderer.init(isDefaultContext());

        // Initialize the states
        initStates();

        // Set running to true when initializing is complete
        running = true;
    }

    // Initialize the states
    private void initStates() {
        state.add(Const.GAME_STATE, new GameState(renderer));
        state.change(Const.GAME_STATE);
    }

    // The game loop
    public abstract void gameLoop();

    // Handles input
    public void input(float delta) {
        state.input(delta);
    }

    // Updates the game (fixed time-step)
    public void update() {
        state.update();
    }

    // Updates the game (variable time-step)
    public void update(float delta) {
        state.update(delta);
    }

    // Renders the game (no interpolation)
    public void render() {
        state.render();
    }

    // Renders the game (with interpolation)
    public void render(float alpha) {
        state.render(alpha);
    }

    // Synchronises the game at specified FPS
    public void sync(int fps) {
        double lastLoopTime = timer.getLastLoopTime();
        double now = timer.getTime();
        float targetTime = 1f / fps;

        while (now - lastLoopTime < targetTime) {
            Thread.yield();

            // This is optional if you want the game to stop
            // consuming to much CPU but you will loose some
            // accuracy because Thread.sleep(1) could sleep
            // longer than 1 ms
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, e);
            }

            now = timer.getTime();
        }
    }

    public void dispose() {
        // Dispose renderer
        renderer.dispose();

        // Set empty state to trigger the exit method in the current state
        state.change(null);

        // Release the window and its callbacks
        window.destroy();

        // Terminate GLFW and release error callbacks
        glfwTerminate();
        errorCallback.release();
    }

    // Determines if the OpenGL context supports version 3.2
    private boolean isDefaultContext() {
        String version = glGetString(GL_VERSION);
        int major = Character.getNumericValue(version.charAt(0));
        int minor = Character.getNumericValue(version.charAt(2));
        return major == 3 && minor == 2;
    }
}
