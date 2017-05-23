package se.scramble_studios.nehro.settlers_of_catan.res.data;

import java.awt.*;

/**
 * Created by Josef on 10/01/2015.
 * <p>
 *     Provides easy storage for game-wide constants.
 * </p>
 */
public class Constants {
    /*
    * Game Board Constants
    * */
    public static final int STANDARD_BOARD_LEVEL = 3;

    /*
    * Tile Constants
    * */
    public static final int ALL_TILE_TYPES = 8;
    public static final int PLAYABLE_TILE_TYPES = 7;
    public static final int LAND_TILE_TYPES = 5;
    public static final int SEA_TILE_TYPES = 4;

    public static final int TILE_WIDTH = 200;
    public static final int TILE_HEIGHT = 173;

    // The offsets used to make the hexagonal tiles lite edge to edge
    public static final int TILE_X_OFFSET = 150;
    public static final int TILE_Y_OFFSET = 86;

    // Creates a polygon used for I/O regarding tiles
    private static final int[] TILE_X_POINTS = {0, 50, 150, 200, 150, 50};
    private static final int[] TILE_Y_POINTS = {86, 0, 0, 86, 173, 173};
    public static final Polygon TILE_POLYGON = new Polygon(TILE_X_POINTS, TILE_Y_POINTS, 6);

    public enum TileType {
        CLAY_TILE, EMPTY_TILE, FIELD_TILE, FOREST_TILE, HARBOR_TILE, MEADOW_TILE, MOUNTAIN_TILE, SEA_TILE
    }

    /*
    * Road Constants
    * */
    public static final int ROAD_WIDTH = 100;
    public static final int ROAD_HEIGHT = 100;

    // Polygons used for I/O regarding roads
    // The points of the polygons are based on the fact that the
    // .png-file of the roads are 100px x 100px
    private static final int[] ROAD_H_X_POINTS = {15, 85, 85, 15};
    private static final int[] ROAD_H_Y_POINTS = {45, 45, 55, 55};

    private static final int[] ROAD_NE_X_POINTS = {};
    private static final int[] ROAD_NE_Y_POINTS = {};

    private static final int[] ROAD_SE_X_POINTS = {};
    private static final int[] ROAD_SE_Y_POINTS = {};

    public static final Polygon ROAD_H_POLYGON = new Polygon(ROAD_H_X_POINTS, ROAD_H_Y_POINTS, 4);
    public static final Polygon ROAD_NE_POLYGON = new Polygon(ROAD_NE_X_POINTS, ROAD_NE_Y_POINTS, 0);
    public static final Polygon ROAD_SE_POLYGON = new Polygon(ROAD_SE_X_POINTS, ROAD_SE_Y_POINTS, 0);

    // Constants storing the path to different graphic res
    public static final String TILE_IMG_ROOT = "src/se/scramble_studios/nehro/settlers_of_catan/res/img/tiles/";
    public static final String ROAD_IMG_ROOT = "src/se/scramble_studios/nehro/settlers_of_catan/res/img/roads/";
}
