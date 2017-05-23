package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles;

import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import javax.swing.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 *     An "empty tile"-representation, used for the map editor feature.
 * </p>
 *
 */
public class EmptyTile extends Tile {

    public EmptyTile(int x, int y) {
        super(new ImageIcon(Constants.TILE_IMG_ROOT + "empty_tile.png").getImage(), x, y);
    }

    @Override
    public Constants.TileType getTileType() {
        return Constants.TileType.EMPTY_TILE;
    }
}
