package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A grass tile
 * </p>
 */
public class GrassTile extends Tile {
    public GrassTile(float x, float y) {
        super(x, y, 0, 0, Color.CYAN, TileType.GRASS, 3, 2);
    }
}
