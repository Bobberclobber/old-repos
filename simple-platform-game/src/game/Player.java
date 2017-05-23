package se.scramble_studios.nehro.simple_platform_game.java.game;

import org.lwjgl.glfw.GLFW;
import se.scramble_studios.nehro.simple_platform_game.java.core.Const;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.Renderer;
import se.scramble_studios.nehro.simple_platform_game.java.graphics.Texture;
import se.scramble_studios.nehro.simple_platform_game.java.map.Map;
import se.scramble_studios.nehro.simple_platform_game.java.math.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Josef on 29/03/2015.
 * <p>
 *     This class represents the player
 * </p>
 */
public class Player {
    private Vector2f previousPosition;
    private Vector2f position;

    private final float speed;
    private Vector2f direction;
    private Vector2f prevDir;

    public boolean jumping = false;
    public boolean falling = false;

    private final Texture texture;

    public Player(Texture texture, float x, float y, float speed) {
        previousPosition = new Vector2f(x, y);
        position = new Vector2f(x, y);

        this.speed = speed;
        prevDir = new Vector2f();
        this.texture = texture;
    }

    // Handles input
    public void input(float delta) {
        direction = new Vector2f();
        long window = GLFW.glfwGetCurrentContext();

        // Jump
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS)
            startJump();
        if (jumping)
            direction.y = jump(delta);

        // Falling
        if (falling)
            direction.y = jump(delta); // A fall is a jump with zero height

        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) // Move left
            direction.x = accelerate(delta, -2f);
        else if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) // Move right
            direction.x = accelerate(delta, 2f);
        else // Slow down if no movement key is pressed
            direction.x = accelerate(delta, 0f);

        // Save the current direction for use in calculating acceleration
        prevDir.x = direction.x;
        prevDir.y = direction.y;
    }

    // Updates the player
    public void update(float delta) {
        previousPosition = new Vector2f(position.x, position.y);
        if (direction.x != 0 || direction.y != 0)
            direction.normalize();
        Vector2f velocity = direction.scale(speed * delta);
        position = position.add(velocity);
    }

    /*
     * Checks collisions in all directions
     */

    // Collision left
    public int checkCollisionLeft(Map map) {
        float xAdjust = position.x % Const.BLOCK_WIDTH;
        if (map.getBlock(position.x, getMidY()) == Const.BRICK_CHAR) {
            position.x += (Const.BLOCK_WIDTH - xAdjust);
            prevDir.x = 0f;
            return Const.COLLISION;
        }
        return Const.NO_COLLISION;
    }

    // Collision right
    public int checkCollisionRight(Map map) {
        float xAdjust = position.x % Const.BLOCK_WIDTH;
        if (map.getBlock((position.x + Const.PLAYER_WIDTH), getMidY()) == Const.BRICK_CHAR) {
            position.x -= xAdjust;
            prevDir.x = 0f;
            return Const.COLLISION;
        }
        return Const.NO_COLLISION;
    }

    // Collision bottom
    public int checkCollisionBottom(Map map) {
        float yAdjust = position.y % Const.BLOCK_HEIGHT;
        if (map.getBlock(getMidX(), position.y) == Const.BRICK_CHAR) {
            position.y += (Const.BLOCK_HEIGHT - yAdjust);
            return Const.COLLISION;
        }
        return Const.NO_COLLISION;
    }

    // Collision top
    public int checkCollisionTop(Map map) {
        float yAdjust = position.y % Const.BLOCK_HEIGHT;
        if (map.getBlock(getMidX(), (position.y + Const.PLAYER_HEIGHT)) == Const.BRICK_CHAR) {
            position.y -= yAdjust;
            return Const.COLLISION;
        }
        return Const.NO_COLLISION;
    }

    public void render(Renderer renderer, float alpha, Map map) {
        Vector2f interpolatedPosition = previousPosition.lerp(position, alpha);
        float x = interpolatedPosition.x;
        float y = interpolatedPosition.y;

        // Adjusts the players on screen position
        if (x > Const.WIN_HALF_WIDTH && x < map.getWidth() - Const.WIN_HALF_WIDTH)
            x = Const.WIN_HALF_WIDTH;
        else if (x >= map.getWidth() - Const.WIN_HALF_WIDTH)
            x += Const.WINDOW_WIDTH - map.getWidth();

        if (y > Const.WIN_HALF_HEIGHT && y < map.getHeight() - Const.WIN_HALF_HEIGHT)
            y = Const.WIN_HALF_HEIGHT;
        else if (y >= map.getHeight() - Const.WIN_HALF_HEIGHT)
            y += Const.WINDOW_HEIGHT - map.getHeight();

        renderer.drawTextureRegion(texture, x, y, Const.PLAYER_WIDTH, Const.PLAYER_HEIGHT, Const.PLAYER_WIDTH, Const.PLAYER_HEIGHT);
    }

    public float getMidX() {
        return position.x + (Const.PLAYER_WIDTH / 2);
    }

    public float getMidY() {
        return position.y + (Const.PLAYER_HEIGHT / 2);
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    // Return a speed depending on if the player is accelerating or not
    private float accelerate(float delta, float lim) {
        // If moving left
        if (lim < 0)
            return prevDir.x > lim ? prevDir.x - 4*delta : lim;
        else if (lim > 0)
            return prevDir.x < lim ? prevDir.x + 4*delta : lim;
        else
            return Math.abs(prevDir.x) > lim ? prevDir.x -= prevDir.x / 20 : lim;
    }

    private float jump(float delta) {
        return prevDir.y - 4*delta;
    }

    public void startJump() {
        if (!jumping && !falling) {
            jumping = true;
            prevDir.y = Const.PLAYER_BASE_JUMP_HEIGHT;
        }
    }

    public void endJump() {
        jumping = false;
    }

    public void startFall() {
        if (!falling) {
            falling = true;
            jumping = false;
            prevDir.y = 0f;
        }
    }

    public void endFall() {
        falling = false;
    }
}
