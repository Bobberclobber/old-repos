package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles;

import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import javax.swing.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 *     A "mountain tile"-representation.
 * </p>
 */
public class MountainTile extends Tile {

    public MountainTile(int x, int y) {
        super(new ImageIcon(Constants.TILE_IMG_ROOT + "mountain_tile.png").getImage(), x, y);
    }

    @Override
    public Constants.TileType getTileType() {
        return Constants.TileType.MOUNTAIN_TILE;
    }
}
