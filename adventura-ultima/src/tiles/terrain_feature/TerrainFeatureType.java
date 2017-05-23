package se.wgco.jgf.adventura_ultima.tiles.terrain_feature;

import se.wgco.jgf.adventura_ultima.tiles.TerrainType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     Enums representing the different features a tile can have.
 * </p>
 */
public enum TerrainFeatureType {
    NONE, FOREST, RIVER, HILL, MOUNTAIN, OASIS;

    public static List<TerrainFeatureType> getAvailableFeatures(TerrainType tileType) {
        List<TerrainFeatureType> temp = new ArrayList<TerrainFeatureType>();
        temp.add(NONE);
        switch (tileType) {
            case GRASS_TILE:
                for (int n = 0; n < 5; n++) {
                    temp.add(FOREST);
                }
                temp.add(RIVER);
                temp.add(MOUNTAIN);
                temp.add(HILL);
                break;

            case PLAINS_TILE:
                temp.add(FOREST);
                temp.add(RIVER);
                temp.add(MOUNTAIN);
                temp.add(HILL);
                break;

            case DESERT_TILE:
                for (int n = 0; n < 10; n++) {
                    temp.add(NONE);
                }
                temp.add(HILL);
                temp.add(MOUNTAIN);
                temp.add(RIVER);
                temp.add(OASIS);
                break;

            case TUNDRA_TILE:
                for (int n = 0; n < 10; n++) {
                    temp.add(NONE);
                }
                temp.add(HILL);
                temp.add(MOUNTAIN);
                temp.add(RIVER);
                break;

            case SNOW_TILE:
                for (int n = 0; n < 20; n++) {
                    temp.add(NONE);
                    if (n % 2 == 0) {
                        temp.add(MOUNTAIN);
                    }
                }
                temp.add(HILL);
                break;

            case WATER_TILE:
                break;
        }
        return temp;
    }
}
