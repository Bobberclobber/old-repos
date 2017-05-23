package se.wgco.jgf.adventura_ultima.graphics_maps;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     An interface defining the methods required in
 *     order to create a class containing maps connecting
 *     game elements to some other object.
 * </p>
 */
public interface GraphicsMap {
    void initMaps();
    public void initTerrainTileMap();
    public void initTerrainFeatureMap();
    public void initCityElementMap();
    public void initCharacterClassMap();
}
