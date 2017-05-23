package world.tile;

import java.awt.*;

/**
 * Created by Josef on 16/06/2015.
 * <p> A lava tile</p>
 */
public class LavaTile extends Tile {
    public LavaTile(float x, float y) {
        super(x, y, 0, 0, Color.RED, TileType.LAVA, 5, 5);
    }
}
