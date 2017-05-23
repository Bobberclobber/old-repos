package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles;

import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import javax.swing.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 *     A "field tile"-representation.
 * </p>
 */
public class FieldTile extends Tile {

    public FieldTile(int x, int y) {
        super(new ImageIcon(Constants.TILE_IMG_ROOT + "field_tile.png").getImage(),x, y);
    }

    @Override
    public Constants.TileType getTileType() {
        return Constants.TileType.FIELD_TILE;
    }
}
