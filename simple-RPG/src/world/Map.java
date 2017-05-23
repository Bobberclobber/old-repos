package world;

import world.tile.Tile;

/**
 * Created by Josef on 06/06/2015.
 * <p>
 *     The game's map. Consists of a 2D grid of all
 *     tile in the world and another containing the
 *     tiles which should be rendered for the current screen.
 * </p>
 */
public class Map {
    public final int SIZE = 1025;

    private Tile[][] map;
    private Screen screen;

    public Map() {
        try {
            // Generate a tile map seeded with grass tiles in the corners
            map = new MapGenerator(SIZE, 0.5).generate().getTiles();
        } catch (MapGenerator.MapNotGeneratedException e) {
            e.printStackTrace();
        }
    }
}
