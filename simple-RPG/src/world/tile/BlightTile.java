package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A blight tile
 * </p>
 */
public class BlightTile extends Tile {
    public BlightTile(float x, float y) {
        super(x, y, 0, 0, Color.MAGENTA, TileType.BLIGHT, 4, 5);
    }
}
