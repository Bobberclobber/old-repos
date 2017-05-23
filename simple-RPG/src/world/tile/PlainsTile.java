package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A plains tile
 * </p>
 */
public class PlainsTile extends Tile {
    public PlainsTile(float x, float y) {
        super(x, y, 0, 0, Color.PINK, TileType.PLAINS, 4, 3);
    }
}
