package se.wgco.jgf.adventura_ultima.graphics_maps;

import se.wgco.jgf.adventura_ultima.constants.WorldConstants;
import se.wgco.jgf.adventura_ultima.tiles.terrain_feature.TerrainFeatureType;

import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Josef on 01/05/2014.
 * <p>
 *     Defines methods used to initialize and get maps,
 *     connecting the different enums to shapes.
 * </p>
 */
public class EnumShapeDataMap implements GraphicsMap {
    public static AbstractMap<TerrainFeatureType, int[]> terrainFeatureXPointsMap = new EnumMap<TerrainFeatureType, int[]>(TerrainFeatureType.class);
    public static AbstractMap<TerrainFeatureType, int[]> terrainFeatureYPointsMap = new EnumMap<TerrainFeatureType, int[]>(TerrainFeatureType.class);
    public static AbstractMap<TerrainFeatureType, Integer> terrainFeaturePointNumberMap = new EnumMap<TerrainFeatureType, Integer>(TerrainFeatureType.class);

    public static AbstractMap<TerrainFeatureType, int[]> terrainFeatureXPointsMapOverview = new EnumMap<TerrainFeatureType, int[]>(TerrainFeatureType.class);
    public static AbstractMap<TerrainFeatureType, int[]> terrainFeatureYPointsMapOverview = new EnumMap<TerrainFeatureType, int[]>(TerrainFeatureType.class);
    public static AbstractMap<TerrainFeatureType, Integer> terrainFeaturePointNumberMapOverview = new EnumMap<TerrainFeatureType, Integer>(TerrainFeatureType.class);

    @Override
    public void initMaps() {
        initTerrainFeatureMap();
    }

    @Override
    public void initTerrainTileMap() {

    }

    @Override
    public void initTerrainFeatureMap() {
        terrainFeatureXPointsMap.put(TerrainFeatureType.NONE, WorldConstants.NO_FEATURE_X_POINTS);
        terrainFeatureYPointsMap.put(TerrainFeatureType.NONE, WorldConstants.NO_FEATURE_Y_POINTS);
        terrainFeaturePointNumberMap.put(TerrainFeatureType.NONE, WorldConstants.NO_FEATURE_POINT_NUMBER);

        terrainFeatureXPointsMap.put(TerrainFeatureType.FOREST, WorldConstants.FOREST_X_POINTS);
        terrainFeatureYPointsMap.put(TerrainFeatureType.FOREST, WorldConstants.FOREST_Y_POINTS);
        terrainFeaturePointNumberMap.put(TerrainFeatureType.FOREST, WorldConstants.FOREST_POINT_NUMBER);

        terrainFeatureXPointsMap.put(TerrainFeatureType.HILL, WorldConstants.HILL_X_POINTS);
        terrainFeatureYPointsMap.put(TerrainFeatureType.HILL, WorldConstants.HILL_Y_POINTS);
        terrainFeaturePointNumberMap.put(TerrainFeatureType.HILL, WorldConstants.HILL_POINT_NUMBER);

        terrainFeatureXPointsMap.put(TerrainFeatureType.MOUNTAIN, WorldConstants.MOUNTAIN_X_POINTS);
        terrainFeatureYPointsMap.put(TerrainFeatureType.MOUNTAIN, WorldConstants.MOUNTAIN_Y_POINTS);
        terrainFeaturePointNumberMap.put(TerrainFeatureType.MOUNTAIN, WorldConstants.MOUNTAIN_POINT_NUMBER);

        terrainFeatureXPointsMap.put(TerrainFeatureType.RIVER, WorldConstants.RIVER_X_POINTS);
        terrainFeatureYPointsMap.put(TerrainFeatureType.RIVER, WorldConstants.RIVER_Y_POINTS);
        terrainFeaturePointNumberMap.put(TerrainFeatureType.RIVER, WorldConstants.RIVER_POINT_NUMBER);

        terrainFeatureXPointsMap.put(TerrainFeatureType.OASIS, WorldConstants.OASIS_X_POINTS);
        terrainFeatureYPointsMap.put(TerrainFeatureType.OASIS, WorldConstants.OASIS_Y_POINTS);
        terrainFeaturePointNumberMap.put(TerrainFeatureType.OASIS, WorldConstants.OASIS_POINT_NUMBER);


        terrainFeatureXPointsMapOverview.put(TerrainFeatureType.NONE, WorldConstants.NO_FEATURE_X_POINTS_OVERVIEW);
        terrainFeatureYPointsMapOverview.put(TerrainFeatureType.NONE, WorldConstants.NO_FEATURE_Y_POINTS_OVERVIEW);
        terrainFeaturePointNumberMapOverview.put(TerrainFeatureType.NONE, WorldConstants.NO_FEATURE_POINT_NUMBER_OVERVIEW);

        terrainFeatureXPointsMapOverview.put(TerrainFeatureType.FOREST, WorldConstants.FOREST_X_POINTS_OVERVIEW);
        terrainFeatureYPointsMapOverview.put(TerrainFeatureType.FOREST, WorldConstants.FOREST_Y_POINTS_OVERVIEW);
        terrainFeaturePointNumberMapOverview.put(TerrainFeatureType.FOREST, WorldConstants.FOREST_POINT_NUMBER_OVERVIEW);

        terrainFeatureXPointsMapOverview.put(TerrainFeatureType.HILL, WorldConstants.HILL_X_POINTS_OVERVIEW);
        terrainFeatureYPointsMapOverview.put(TerrainFeatureType.HILL, WorldConstants.HILL_Y_POINTS_OVERVIEW);
        terrainFeaturePointNumberMapOverview.put(TerrainFeatureType.HILL, WorldConstants.HILL_POINT_NUMBER_OVERVIEW);

        terrainFeatureXPointsMapOverview.put(TerrainFeatureType.MOUNTAIN, WorldConstants.MOUNTAIN_X_POINTS_OVERVIEW);
        terrainFeatureYPointsMapOverview.put(TerrainFeatureType.MOUNTAIN, WorldConstants.MOUNTAIN_Y_POINTS_OVERVIEW);
        terrainFeaturePointNumberMapOverview.put(TerrainFeatureType.MOUNTAIN, WorldConstants.MOUNTAIN_POINT_NUMBER_OVERVIEW);

        terrainFeatureXPointsMapOverview.put(TerrainFeatureType.RIVER, WorldConstants.RIVER_X_POINTS_OVERVIEW);
        terrainFeatureYPointsMapOverview.put(TerrainFeatureType.RIVER, WorldConstants.RIVER_Y_POINTS_OVERVIEW);
        terrainFeaturePointNumberMapOverview.put(TerrainFeatureType.RIVER, WorldConstants.RIVER_POINT_NUMBER_OVERVIEW);

        terrainFeatureXPointsMapOverview.put(TerrainFeatureType.OASIS, WorldConstants.OASIS_X_POINTS_OVERVIEW);
        terrainFeatureYPointsMapOverview.put(TerrainFeatureType.OASIS, WorldConstants.OASIS_Y_POINTS_OVERVIEW);
        terrainFeaturePointNumberMapOverview.put(TerrainFeatureType.OASIS, WorldConstants.OASIS_POINT_NUMBER_OVERVIEW);
    }

    @Override
    public void initCityElementMap() {

    }

    @Override
    public void initCharacterClassMap() {

    }
}
