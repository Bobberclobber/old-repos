package se.wgco.jgf.adventura_ultima.tiles;

import se.wgco.jgf.adventura_ultima.tiles.terrain_feature.TerrainFeatureType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     A class which all terrain tiles inherit from.
 * </p>
 */
public abstract class TerrainTile {
    private TerrainType tileType = null;
    private static List<TerrainTile> terrainTiles = null;
    private List<TerrainFeatureType> tileFeatures = null;

    public TerrainTile(TerrainType tileType) {
        this.tileType = tileType;
        tileFeatures = new ArrayList<TerrainFeatureType>();
        randomizeFeatures();
    }

    public TerrainType getTileType() {
        return tileType;
    }

    public List<TerrainFeatureType> getTileFeatures() {
        return tileFeatures;
    }

    public void addTileFeature(TerrainFeatureType feature) {
        tileFeatures.add(feature);
    }

    public void removeTileFeature(TerrainFeatureType feature) {
        tileFeatures.remove(feature);
    }

    public static TerrainTile randomTerrainTile() {
        terrainTiles = new ArrayList<TerrainTile>();
        makeTerrainTilesList();
        int randomNum = (int) (Math.random() * terrainTiles.size());
        return terrainTiles.get(randomNum);
    }

    private static void makeTerrainTilesList() {
        terrainTiles.add(new DesertTile());
        terrainTiles.add(new GrassTile());
        terrainTiles.add(new PlainsTile());
        terrainTiles.add(new SnowTile());
        terrainTiles.add(new TundraTile());
        terrainTiles.add(new WaterTile());
    }

    private void randomizeFeatures() {
        if (tileType != TerrainType.WATER_TILE) {
            List<TerrainFeatureType> availableFeatures = TerrainFeatureType.getAvailableFeatures(tileType);
            TerrainFeatureType randomFeature = availableFeatures.get(new Random().nextInt(availableFeatures.size()));
            tileFeatures.add(randomFeature);
        }
    }
}
