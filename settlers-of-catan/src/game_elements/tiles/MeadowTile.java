package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles;

import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import javax.swing.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 *     A "meadow tile"-representation.
 * </p>
 */
public class MeadowTile extends Tile {

    public MeadowTile(int x, int y) {
        super(new ImageIcon(Constants.TILE_IMG_ROOT + "meadow_tile.png").getImage(), x, y);
    }

    @Override
    public Constants.TileType getTileType() {
        return Constants.TileType.MEADOW_TILE;
    }
}
