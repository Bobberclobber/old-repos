package se.wgco.jgf.adventura_ultima.graphics_maps;

import se.wgco.jgf.adventura_ultima.character_classes.ClassType;
import se.wgco.jgf.adventura_ultima.city_elements.CityElementType;
import se.wgco.jgf.adventura_ultima.tiles.TerrainType;
import se.wgco.jgf.adventura_ultima.tiles.terrain_feature.TerrainFeatureType;

import java.awt.*;
import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     Defines methods used to initialize and get maps,
 *     connecting the different enums to colors.
 * </p>
 */
public class EnumColorMap implements GraphicsMap {
    public static AbstractMap<TerrainType, Color> terrainTileMap = new EnumMap<TerrainType, Color>(TerrainType.class);
    public static AbstractMap<TerrainFeatureType, Color> terrainFeatureMap = new EnumMap<TerrainFeatureType, Color>(TerrainFeatureType.class);
    public static AbstractMap<CityElementType, Color> cityElementMap = new EnumMap<CityElementType, Color>(CityElementType.class);

    public static AbstractMap<ClassType, Color> characterClassMap = new EnumMap<ClassType, Color>(ClassType.class);

    @Override
    public void initMaps() {
        initTerrainTileMap();
        initTerrainFeatureMap();
        initCityElementMap();

        initCharacterClassMap();
    }

    @Override
    public void initTerrainTileMap() {
        terrainTileMap.put(TerrainType.GRASS_TILE, Color.GREEN);
        terrainTileMap.put(TerrainType.PLAINS_TILE, Color.PINK);
        terrainTileMap.put(TerrainType.DESERT_TILE, Color.YELLOW);
        terrainTileMap.put(TerrainType.TUNDRA_TILE, Color.GRAY);
        terrainTileMap.put(TerrainType.SNOW_TILE, Color.WHITE);
        terrainTileMap.put(TerrainType.WATER_TILE, Color.BLUE);
    }

    @Override
    public void initTerrainFeatureMap() {
        terrainFeatureMap.put(TerrainFeatureType.NONE, Color.WHITE);
        terrainFeatureMap.put(TerrainFeatureType.FOREST, Color.GREEN);
        terrainFeatureMap.put(TerrainFeatureType.HILL, Color.PINK);
        terrainFeatureMap.put(TerrainFeatureType.MOUNTAIN, Color.DARK_GRAY);
        terrainFeatureMap.put(TerrainFeatureType.RIVER, Color.BLUE);
        terrainFeatureMap.put(TerrainFeatureType.OASIS, Color.BLUE);
    }

    @Override
    public void initCityElementMap() {
        cityElementMap.put(CityElementType.BUILDING, Color.DARK_GRAY);
        cityElementMap.put(CityElementType.CITY_ROAD, Color.PINK);
        cityElementMap.put(CityElementType.CITY_GATE, Color.ORANGE);
    }

    @Override
    public void initCharacterClassMap() {
        characterClassMap.put(ClassType.DISCIPLE, Color.WHITE);
        characterClassMap.put(ClassType.SCOUT, Color.GREEN);
        characterClassMap.put(ClassType.SORCERER, Color.BLUE);
        characterClassMap.put(ClassType.THIEF, Color.BLACK);
        characterClassMap.put(ClassType.TRIBESMAN, Color.MAGENTA);
        characterClassMap.put(ClassType.WARRIOR, Color.RED);
    }
}
