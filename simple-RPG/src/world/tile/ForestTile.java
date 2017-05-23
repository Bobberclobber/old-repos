package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p>
 *     A forest tile
 * </p>
 */
public class ForestTile extends Tile {
    public ForestTile(float x, float y) {
        super(x, y, 0, 0, Color.GREEN, TileType.FOREST, 3, 1);
    }
}
