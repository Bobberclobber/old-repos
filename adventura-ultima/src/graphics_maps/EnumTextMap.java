package se.wgco.jgf.adventura_ultima.graphics_maps;

import se.wgco.jgf.adventura_ultima.city_elements.CityElementType;
import se.wgco.jgf.adventura_ultima.tiles.TerrainType;
import se.wgco.jgf.adventura_ultima.tiles.terrain_feature.TerrainFeatureType;

import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     Defines methods used to initialize and get maps,
 *     connecting the different enums to strings.
 * </p>
 */
public class EnumTextMap implements GraphicsMap {
    public static AbstractMap<TerrainType, String> terrainTileMap = new EnumMap<TerrainType, String>(TerrainType.class);
    public static AbstractMap<TerrainFeatureType, String> terrainFeatureMap = new EnumMap<TerrainFeatureType, String>(TerrainFeatureType.class);
    public static AbstractMap<CityElementType, String> cityElementMap = new EnumMap<CityElementType, String>(CityElementType.class);

    @Override
    public void initMaps() {
        initTerrainTileMap();
        initTerrainFeatureMap();
        initCityElementMap();
    }

    @Override
    public void initTerrainTileMap() {
        terrainTileMap.put(TerrainType.GRASS_TILE, "G");
        terrainTileMap.put(TerrainType.PLAINS_TILE, "P");
        terrainTileMap.put(TerrainType.DESERT_TILE, "D");
        terrainTileMap.put(TerrainType.TUNDRA_TILE, "T");
        terrainTileMap.put(TerrainType.SNOW_TILE, "S");
        terrainTileMap.put(TerrainType.WATER_TILE, "W");
    }

    @Override
    public void initTerrainFeatureMap() {
        terrainFeatureMap.put(TerrainFeatureType.NONE, "n");
        terrainFeatureMap.put(TerrainFeatureType.FOREST, "f");
        terrainFeatureMap.put(TerrainFeatureType.HILL, "h");
        terrainFeatureMap.put(TerrainFeatureType.MOUNTAIN, "m");
        terrainFeatureMap.put(TerrainFeatureType.RIVER, "r");
        terrainFeatureMap.put(TerrainFeatureType.OASIS, "o");
    }

    @Override
    public void initCityElementMap() {
        cityElementMap.put(CityElementType.BUILDING, "B");
        cityElementMap.put(CityElementType.CITY_ROAD, "R");
        cityElementMap.put(CityElementType.CITY_GATE, "G");
    }

    @Override
    public void initCharacterClassMap() {

    }
}
