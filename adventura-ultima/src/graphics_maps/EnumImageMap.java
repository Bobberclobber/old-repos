package se.wgco.jgf.adventura_ultima.graphics_maps;

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
 *     connecting the different enums to images.
 * </p>
 */
public class EnumImageMap implements GraphicsMap {
    private static AbstractMap<TerrainType, Image> terrainTileMap = new EnumMap<TerrainType, Image>(TerrainType.class);
    public static AbstractMap<TerrainFeatureType, Image> terrainFeatureMap = new EnumMap<TerrainFeatureType, Image>(TerrainFeatureType.class);
    public static AbstractMap<CityElementType, Image> cityElementMap = new EnumMap<CityElementType, Image>(CityElementType.class);

    @Override
    public void initMaps() {

    }

    @Override
    public void initTerrainTileMap() {

    }

    @Override
    public void initTerrainFeatureMap() {

    }

    @Override
    public void initCityElementMap() {

    }

    @Override
    public void initCharacterClassMap() {

    }
}
