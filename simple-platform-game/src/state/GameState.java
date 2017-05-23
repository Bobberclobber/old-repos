package se.scramble_studios.nehro.simple_platform_game.java.state;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import se.scramble_studios.nehro.simple_platform_game.java.core.Const;
import se.scramble_studios.nehro.simple_platform_game.java.game.Player;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.Camera;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.Renderer;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.Texture;
import se.scramble_studios.nehro.simple_platform_game.java.map.Map;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.glClearColor;

/**
 * Created by Josef on 28/03/2015.
 * <p>
 *     Contains a platform game
 * </p>
 */
public class GameState implements State {
    private Texture playerTexture;
    private Texture mapTexture;
    private final Renderer renderer;

    private Map map;
    private Player player;
    private Camera camera;

    private int gameWidth;
    private int gameHeight;

    public GameState(Renderer renderer) {
        this.renderer = renderer;
        this.map = new Map();
    }

    @Override
    public void input(float delta) {
        player.input(delta);
    }

    @Override
    public void update(float delta) {
        // Update position
        player.update(delta);

        // Get collision results
        int collLeft = player.checkCollisionLeft(map);
        int collRight = player.checkCollisionRight(map);
        int collTop = player.checkCollisionTop(map);
        int collBottom = player.checkCollisionBottom(map);

        // Collision left
        if (collLeft == Const.COLLISION) {

        }

        // Collision right
        if (collRight == Const.COLLISION) {

        }

        // Collision top
        if (collTop == Const.COLLISION) {
            player.endJump();
            player.startFall();
        }

        // Collision bottom
        if (collBottom == Const.COLLISION) {
            player.endJump();
            player.endFall();
        } else if (collBottom == Const.NO_COLLISION && !player.jumping)
            player.startFall();
    }

    @Override
    public void render(float alpha) {
        // Clear drawing area
        renderer.clear();

        // Draw the map
        mapTexture.bind();
        renderer.begin();
        camera.renderScene(renderer);
        renderer.end();

        // Draw the player
        playerTexture.bind();
        renderer.begin();
        player.render(renderer, alpha, map);
        renderer.end();
    }

    @Override
    public void enter() {
        // Get width and height of framebuffer
        long window = GLFW.glfwGetCurrentContext();
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetFramebufferSize(window, widthBuffer, heightBuffer);
        gameWidth = widthBuffer.get();
        gameHeight = heightBuffer.get();

        // Load textures
        playerTexture = Texture.loadTexture(Const.PLAYER_IMG);
        mapTexture = Texture.loadTexture(Const.BLOCK_IMG);

        // Initialize game objects
        map.initMap("map2");
        player = new Player(playerTexture, map.getStartx(), map.getStarty(), Const.PLAYER_BASE_SPEED);

        // Create camera
        camera = new Camera(map, player, mapTexture);

        // Set clear color to gray
        glClearColor(0.5f, 0.5f, 0.5f, 1f);
    }

    @Override
    public void exit() {
        playerTexture.delete();
        mapTexture.delete();
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }
}
