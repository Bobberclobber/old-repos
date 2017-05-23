package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A desert tile
 * </p>
 */
public class DesertTile extends Tile {
    public DesertTile(float x, float y) {
        super(x, y, 0, 0, Color.YELLOW, TileType.DESERT, 5, 4);
    }
}
