package se.wgco.jgf.adventura_ultima.tiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     An enum defining the different types
 *     of tiles which can exist on the world map.
 * </p>
 */
public enum TerrainType {
    GRASS_TILE, PLAINS_TILE, DESERT_TILE, TUNDRA_TILE, SNOW_TILE, WATER_TILE;

    private static final List<TerrainType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static TerrainType randomTerrainType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
