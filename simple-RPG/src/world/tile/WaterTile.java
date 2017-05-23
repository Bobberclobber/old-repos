package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A water tile
 * </p>
 */
public class WaterTile extends Tile {
    public WaterTile(float x, float y) {
        super(x, y, 0, 0, Color.BLUE, TileType.WATER, 2, 1);
    }
}
