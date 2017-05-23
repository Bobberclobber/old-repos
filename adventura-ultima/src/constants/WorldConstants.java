package se.wgco.jgf.adventura_ultima.constants;

/**
 * Created by Josef on 28/04/2014.
 * <p>
 *     A class defining all constants used when creating the world.
 * </p>
 */
public class WorldConstants {
    public static int TILE_SIDE = 50;
    public static int TILE_SIDE_OVERVIEW = TILE_SIDE / 5;
    public static int CITY_ELEMENT_SIDE = 80;

    public static int WIDTH_TILE_NUMBER = GameWindowConstants.PLAY_FIELD_WIDTH / TILE_SIDE;
    public static int HEIGHT_TILE_NUMBER = GameWindowConstants.PLAY_FIELD_HEIGHT / TILE_SIDE;

    public static int STANDARD_X_START_TILE = WIDTH_TILE_NUMBER / 2;
    public static int STANDARD_Y_START_TILE = HEIGHT_TILE_NUMBER / 2;

    public static int STANDARD_CHARACTER_WIDTH = 35;
    public static int STANDARD_CHARACTER_HEIGHT = 35;

    /*
     * Defines data used to paint terrain features on map
     */

    // Data used to paint an invisible shape
    public static int[] NO_FEATURE_X_POINTS = {0};
    public static int[] NO_FEATURE_Y_POINTS = {0};
    public static int NO_FEATURE_POINT_NUMBER = 1;

    // Data used to paint the forest feature
    public static int[] FOREST_X_POINTS = {0, TILE_SIDE / 2, TILE_SIDE};
    public static int[] FOREST_Y_POINTS = {TILE_SIDE, 0, TILE_SIDE};
    public static int FOREST_POINT_NUMBER = 3;

    // Data used to paint the hill feature
    public static int[] HILL_X_POINTS = {0, TILE_SIDE / 4, 3 * TILE_SIDE / 4, TILE_SIDE};
    public static int[] HILL_Y_POINTS = {TILE_SIDE, TILE_SIDE / 2, TILE_SIDE / 2, TILE_SIDE};
    public static int HILL_POINT_NUMBER = 4;

    // Data used to paint the mountain feature
    public static int[] MOUNTAIN_X_POINTS = {0, TILE_SIDE / 2, TILE_SIDE};
    public static int[] MOUNTAIN_Y_POINTS = {TILE_SIDE, 0, TILE_SIDE};
    public static int MOUNTAIN_POINT_NUMBER = 3;

    // Data used to paint the river feature
    public static int[] RIVER_X_POINTS = {0, 0, TILE_SIDE, TILE_SIDE};
    public static int[] RIVER_Y_POINTS = {5 * TILE_SIDE / 9, 4 * TILE_SIDE / 9, 4 * TILE_SIDE / 9, 5 * TILE_SIDE / 9};
    public static int RIVER_POINT_NUMBER = 4;

    // Data used to paint the oasis feature
    public static int[] OASIS_X_POINTS = {0, TILE_SIDE / 3, 2 * TILE_SIDE / 3, TILE_SIDE, 2 * TILE_SIDE / 3, TILE_SIDE / 3};
    public static int[] OASIS_Y_POINTS = {TILE_SIDE / 2, TILE_SIDE / 3, TILE_SIDE / 3, TILE_SIDE / 2, 2 * TILE_SIDE / 3, 2 * TILE_SIDE / 3};
    public static int OASIS_POINT_NUMBER = 6;

    /*
     * Defines data used to paint terrain features on the world map overview
     */

    // Data used to paint an invisible shape
    public static int[] NO_FEATURE_X_POINTS_OVERVIEW = {0};
    public static int[] NO_FEATURE_Y_POINTS_OVERVIEW = {0};
    public static int NO_FEATURE_POINT_NUMBER_OVERVIEW = 1;

    // Data used to paint the forest feature
    public static int[] FOREST_X_POINTS_OVERVIEW = {0, TILE_SIDE_OVERVIEW / 2, TILE_SIDE_OVERVIEW};
    public static int[] FOREST_Y_POINTS_OVERVIEW = {TILE_SIDE_OVERVIEW, 0, TILE_SIDE_OVERVIEW};
    public static int FOREST_POINT_NUMBER_OVERVIEW = 3;

    // Data used to paint the hill feature
    public static int[] HILL_X_POINTS_OVERVIEW = {0, TILE_SIDE_OVERVIEW / 4, 3 * TILE_SIDE_OVERVIEW / 4, TILE_SIDE_OVERVIEW};
    public static int[] HILL_Y_POINTS_OVERVIEW = {TILE_SIDE_OVERVIEW, TILE_SIDE_OVERVIEW / 2, TILE_SIDE_OVERVIEW / 2, TILE_SIDE_OVERVIEW};
    public static int HILL_POINT_NUMBER_OVERVIEW = 4;

    // Data used to paint the mountain feature
    public static int[] MOUNTAIN_X_POINTS_OVERVIEW = {0, TILE_SIDE_OVERVIEW / 2, TILE_SIDE_OVERVIEW};
    public static int[] MOUNTAIN_Y_POINTS_OVERVIEW = {TILE_SIDE_OVERVIEW, 0, TILE_SIDE_OVERVIEW};
    public static int MOUNTAIN_POINT_NUMBER_OVERVIEW = 3;

    // Data used to paint the river feature
    public static int[] RIVER_X_POINTS_OVERVIEW = {0, 0, TILE_SIDE_OVERVIEW, TILE_SIDE_OVERVIEW};
    public static int[] RIVER_Y_POINTS_OVERVIEW = {5 * TILE_SIDE_OVERVIEW / 9, 4 * TILE_SIDE_OVERVIEW / 9, 4 * TILE_SIDE_OVERVIEW / 9, 5 * TILE_SIDE_OVERVIEW / 9};
    public static int RIVER_POINT_NUMBER_OVERVIEW = 4;

    // Data used to paint the oasis feature
    public static int[] OASIS_X_POINTS_OVERVIEW = {0, TILE_SIDE_OVERVIEW / 3, 2 * TILE_SIDE_OVERVIEW / 3, TILE_SIDE_OVERVIEW, 2 * TILE_SIDE_OVERVIEW / 3, TILE_SIDE_OVERVIEW / 3};
    public static int[] OASIS_Y_POINTS_OVERVIEW = {TILE_SIDE_OVERVIEW / 2, TILE_SIDE_OVERVIEW / 3, TILE_SIDE_OVERVIEW / 3, TILE_SIDE_OVERVIEW / 2, 2 * TILE_SIDE_OVERVIEW / 3, 2 * TILE_SIDE_OVERVIEW / 3};
    public static int OASIS_POINT_NUMBER_OVERVIEW = 6;
}
