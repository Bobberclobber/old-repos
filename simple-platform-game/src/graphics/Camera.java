package se.scramble_studios.nehro.simple_platform_game.java.graphics;

import se.scramble_studios.nehro.simple_platform_game.java.core.Const;
import se.scramble_studios.nehro.simple_platform_game.java.game.Player;
import se.scramble_studios.nehro.simple_platform_game.java.map.Map;
import se.scramble_studios.nehro.simple_platform_game.java.math.Vector2f;

/**
 * Created by Josef on 04/04/2015.
 * <p>
 *     A class used to represent the part of the map visible on the screen
 * </p>
 */
public class Camera {
    private Map map;
    private Player player;
    private Texture texture;

    public Camera(Map map, Player player, Texture texture) {
        this.map = map;
        this.player = player;
        this.texture = texture;
    }

    public void renderScene(Renderer renderer) {
        Vector2f origin = calcOrigin();
        Vector2f lowerBounds = calcLowerBounds(origin);
        Vector2f upperBounds = calcUpperBounds(origin);
        Vector2f lowerBlock = calcLowerBlockBounds(lowerBounds);
        Vector2f upperBlock = calcUpperBlockBounds(upperBounds);
        Vector2f offset = calcOffset(lowerBounds);

        for (int h = (int) lowerBlock.y; h < (int) upperBlock.y; h++) {
            for (int w = (int) lowerBlock.x; w < (int) upperBlock.x; w++) {
                // Calculate on-screen coordinates
                float x = (w - lowerBlock.x - 1) * Const.BLOCK_WIDTH - offset.x;
                float y = (h - lowerBlock.y - 1) * Const.BLOCK_HEIGHT - offset.y;

                // Render a sky block
                renderer.drawTextureRegion(texture, x, y, Const.BLOCK_WIDTH, 0f,
                        (float) Const.BLOCK_WIDTH,
                        (float) Const.BLOCK_HEIGHT);

                char block = map.getBlock(w, h);
                switch (block) {
                    case Const.BRICK_CHAR: // Render a brick block
                        renderer.drawTextureRegion(texture, x, y, 0, 0f,
                                (float) Const.BLOCK_WIDTH,
                                (float) Const.BLOCK_HEIGHT);
                        break;
                    case Const.SPIKE_CHAR: // Render a spike block
                        renderer.drawTextureRegion(texture, x, y, 2 * Const.BLOCK_WIDTH, 0f,
                                (float) Const.BLOCK_WIDTH,
                                (float) Const.BLOCK_HEIGHT);
                        break;
                }
            }
        }
    }

    // Calculates the origin of the camera (in the bottom left corner)
    private Vector2f calcOrigin() {
        float x = player.getX() - Const.WIN_HALF_WIDTH;
        float y = player.getY() - Const.WIN_HALF_HEIGHT;
        // Compensates for negative numbers
        x = x < 0 ? 0 : Math.round(x);
        y = y < 0 ? 0 : Math.round(y);
        return new Vector2f(x, y);
    }

    // Calculates the lower bounds for a given origin
    private Vector2f calcLowerBounds(Vector2f origin) {
        float x = origin.x - Const.BLOCK_WIDTH;
        float y = origin.y - Const.BLOCK_HEIGHT;
        // Adjust for maximum bounds
        float maxBoundX = map.getWidth() - Const.WINDOW_WIDTH - Const.BLOCK_WIDTH;
        float maxBoundY = map.getHeight() - Const.WINDOW_HEIGHT - Const.BLOCK_HEIGHT;
        x = x > maxBoundX ? maxBoundX : x;
        y = y > maxBoundY ? maxBoundY : y;
        return new Vector2f(x, y);
    }

    // Calculates the upper bounds for a given origin
    private Vector2f calcUpperBounds(Vector2f origin) {
        float x = origin.x + Const.WINDOW_WIDTH + Const.BLOCK_WIDTH;
        float y = origin.y + Const.WINDOW_HEIGHT + Const.BLOCK_HEIGHT;
        // Adjust for maximum bounds
        float maxBoundX = map.getWidth() + Const.BLOCK_WIDTH;
        float maxBoundY = map.getHeight() + Const.BLOCK_HEIGHT;
        x = x > maxBoundX ? maxBoundX : x;
        y = y > maxBoundY ? maxBoundY : y;
        return new Vector2f(x, y);
    }

    // Calculates the lower boundary row and column for a given origin
    private Vector2f calcLowerBlockBounds(Vector2f lowerBounds) {
        int x = (int) (lowerBounds.x / Const.BLOCK_WIDTH);
        int y = (int) (lowerBounds.y / Const.BLOCK_HEIGHT);
        return new Vector2f(x, y);
    }

    // Calculates the upper boundary row and column for a given origin
    private Vector2f calcUpperBlockBounds(Vector2f upperBounds) {
        float x = Math.round(upperBounds.x / Const.BLOCK_WIDTH);
        float y = Math.round(upperBounds.y / Const.BLOCK_HEIGHT);
        return new Vector2f(x, y);
    }

    // Calculate the offset for the given lower bound coordinates
    private Vector2f calcOffset(Vector2f lowerBounds) {
        float x = lowerBounds.x % Const.BLOCK_WIDTH;
        float y = lowerBounds.y % Const.BLOCK_HEIGHT;
        return new Vector2f(x, y);
    }

    private void printVecInfo(String name, Vector2f vec) {
        int offset = 15 - name.length();
        for (int i = 0; i < offset; i++)
            System.out.print(" ");
        System.out.println(name + ": " + vec.x + "\t,\t" + vec.y);
    }
}
