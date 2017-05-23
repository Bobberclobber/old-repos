package se.scramble_studios.nehro.settlers_of_catan.java.game_board_operations;

import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles.Tile;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Coordinate;
import se.scramble_studios.nehro.settlers_of_catan.res.data.SettlersMap;

import javax.swing.*;

/**
 * Created by Josef on 19/02/2015.
 * <p>
 *     A class containing operations on the game board involving tiles
 * </p>
 */
public class TileOperations {
    /**
     * <p>
     * Adds the given tile to the given tile map and to the given content pane
     * </p>
     *
     * @param tile The tile to be added
     */
    public static void addTile(Tile tile, SettlersMap<Coordinate, Tile> tileMap, JComponent contentPane) {
        // Adds tile to the tile map
        tileMap.insert(new Coordinate(tile.getX(), tile.getY()), tile);
        // Adds the tile to the frame
        tile.setBounds(tile.getX(), tile.getY(), Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
        tile.setOpaque(true);
        contentPane.add(tile, 1, 0);
    }

    /**
     * <p>
     * Recursive algorithm which calculates the number of tiles
     * needed to create a board of the given level.
     * </p>
     *
     * @param level The level used to calculate the tile number
     * @return The number of tiles (int)
     */
    public static int calcTileNum(int level) {
        if (level <= 0)
            return 1;
        else
            return calcTileNum(level - 1) + level * 6;
    }

    /**
     * <p>
     *     Calculates the number of tiles in the outermost layer
     *     in a game board with the given number of layers
     * </p>
     *
     * @param levels The number of levels
     * @return Returns the number of tiles in the outer layer
     */
    public static int outerLayerTileNum(int levels) {
        return levels * 6;
    }
}
