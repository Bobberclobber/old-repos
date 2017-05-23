package se.scramble_studios.nehro.settlers_of_catan.res.data;

import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles.*;

import java.util.Random;

/**
 * Created by Josef on 18/01/2015.
 * <p>
 *     A class which stores functions used to
 *     randomly generate certain game elements.
 * </p>
 */
public class RandomGenerator {
    public static Tile randomTile(int x, int y) {
        int randomNum = (new Random()).nextInt(Constants.ALL_TILE_TYPES);
        switch (randomNum) {
            case 0:
                return new ClayTile(x, y);
            case 1:
                return new FieldTile(x, y);
            case 2:
                return new ForestTile(x, y);
            case 3:
                return new HarborTile(x, y);
            case 4:
                return new MeadowTile(x, y);
            case 5:
                return new MountainTile(x, y);
            case 6:
                return new SeaTile(x, y);
            default:
                return new EmptyTile(x, y);
        }
    }

    public static Tile randomPlayableTile(int x, int y) {
        int randomNum = (new Random()).nextInt(Constants.PLAYABLE_TILE_TYPES);
        switch (randomNum) {
            case 0:
                return new ClayTile(x, y);
            case 1:
                return new FieldTile(x, y);
            case 2:
                return new ForestTile(x, y);
            case 3:
                return new HarborTile(x, y);
            case 4:
                return new MeadowTile(x, y);
            case 5:
                return new MountainTile(x, y);
            case 6:
                return new SeaTile(x, y);
            default:
                return new EmptyTile(x, y);
        }
    }

    public static Tile randomLandTile(int x, int y) {
        int randomNum = (new Random()).nextInt(Constants.LAND_TILE_TYPES);
        switch (randomNum) {
            case 0:
                return new ClayTile(x, y);
            case 1:
                return new FieldTile(x, y);
            case 2:
                return new ForestTile(x, y);
            case 3:
                return new MountainTile(x, y);
            case 4:
                return new MeadowTile(x, y);
            default:
                return new EmptyTile(x, y);
        }
    }

    public static Tile randomSeaTile(int x, int y) {
        int randomNum = (new Random()).nextInt(Constants.SEA_TILE_TYPES);
        switch (randomNum) {
            case 0:
                return new SeaTile(x, y);
            case 1:
                return new HarborTile(x, y);
            default:
                return new SeaTile(x, y);
        }
    }
}
