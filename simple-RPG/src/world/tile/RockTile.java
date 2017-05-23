package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A rock tile
 * </p>
 */
public class RockTile extends Tile {
    public RockTile(float x, float y) {
        super(x, y, 0, 0, Color.DARK_GRAY, TileType.ROCK, 2, 5);
    }
}
