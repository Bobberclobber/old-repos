package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles;

import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import javax.swing.*;

/**
 * Created by Josef on 10/01/2015.
 * <p>
 *     A "sea tile"-representation.
 * </p>
 */
public class SeaTile extends Tile {

    public SeaTile(int x, int y) {
        super(new ImageIcon(Constants.TILE_IMG_ROOT + "sea_tile.png").getImage(), x, y);
    }

    @Override
    public Constants.TileType getTileType() {
        return Constants.TileType.SEA_TILE;
    }
}
