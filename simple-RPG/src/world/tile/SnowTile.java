package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A snow tile
 * </p>
 */
public class SnowTile extends Tile {
    public SnowTile(float x, float y) {
        super(x, y, 0, 0, Color.WHITE, TileType.SNOW, 1, 5);
    }
}
