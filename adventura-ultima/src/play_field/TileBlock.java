package se.wgco.jgf.adventura_ultima.play_field;

import se.wgco.jgf.adventura_ultima.constants.WorldConstants;
import se.wgco.jgf.adventura_ultima.navigation.Direction;
import se.wgco.jgf.adventura_ultima.navigation.WorldCoordinate;
import se.wgco.jgf.adventura_ultima.tiles.*;

import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Josef on 28/04/2014.
 * <p>
 * A class representing one screen's worth of terrain tiles.
 * </p>
 */
public class TileBlock {
    private final int BLOCKS_WIDTH = WorldConstants.WIDTH_TILE_NUMBER;
    private final int BLOCKS_HEIGHT = WorldConstants.HEIGHT_TILE_NUMBER;
    private final int MAX_X_INDEX = BLOCKS_WIDTH - 1;
    private final int MAX_Y_INDEX = BLOCKS_HEIGHT - 1;

    private WorldCoordinate coordinate;
    private TerrainTile[][] terrainTiles = null;
    private TerrainTile currentTerrainTile = null;
    private AbstractMap<Direction, TerrainTile[]> neighbouringEdges = null;

    /**
     * Constructor used for the very first tile block
     *
     * @param coordinate The coordinate at which the block is to be placed
     */
    public TileBlock(WorldCoordinate coordinate) {
        this.coordinate = coordinate;
        terrainTiles = new TerrainTile[BLOCKS_WIDTH][BLOCKS_HEIGHT];
        randomizeBlock();
        currentTerrainTile = terrainTiles[WorldConstants.STANDARD_X_START_TILE][WorldConstants.STANDARD_Y_START_TILE];
    }

    /**
     * Constructor used for every block created next to an already existing one
     *
     * @param coordinate        The coordinate at which the block is to be placed
     * @param neighbouringEdges A map linking directions to arrays containing
     *                          the edges of neighbouring tile blocks
     */
    public TileBlock(WorldCoordinate coordinate, AbstractMap<Direction, TerrainTile[]> neighbouringEdges) {
        this.coordinate = coordinate;
        this.neighbouringEdges = neighbouringEdges;
        terrainTiles = new TerrainTile[BLOCKS_WIDTH][BLOCKS_HEIGHT];
        randomizeBlock();
        currentTerrainTile = terrainTiles[WorldConstants.STANDARD_X_START_TILE][WorldConstants.STANDARD_Y_START_TILE];
    }

    public WorldCoordinate getCoordinate() {
        return coordinate;
    }

    public TerrainTile[][] getTerrainTiles() {
        return terrainTiles;
    }

    public TerrainTile getTerrainTile(int x, int y) {
        try {
            return terrainTiles[x][y];
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
            return null;
        }
    }

    public TerrainTile getCurrentTerrainTile() {
        return currentTerrainTile;
    }

    /**
     * Returns true if the given tile is at the edge of the block
     *
     * @param tile The tile to check
     * @return True or false depending on if the
     * tile is at the edge of nor
     */
    public boolean tileIsAtEdge(TerrainTile tile) {
        int tempX = 0;
        int tempY = 0;
        for (int x = 0; x <= MAX_X_INDEX; x++) {
            for (int y = 0; y <= MAX_Y_INDEX; y++) {
                if (terrainTiles[x][y] == tile) {
                    tempX = x;
                    tempY = y;
                    break;
                }
            }
        }
        boolean atXEdge = tempX == 0 || tempX == MAX_X_INDEX;
        boolean atYEdge = tempY == 0 || tempY == MAX_Y_INDEX;
        return atXEdge || atYEdge;
    }

    public void switchTile(TerrainTile tile) {
        currentTerrainTile = tile;
    }

    private void randomizeBlock() {
        terrainTiles[0][0] = TerrainTile.randomTerrainTile();
        randomizeEdges();
        generateCenter();
    }

    private void randomizeEdges() {
        if (neighbouringEdges != null && neighbouringEdges.keySet().contains(Direction.NORTH)) {
            setNorthernEdge(neighbouringEdges.get(Direction.NORTH));
        } else {
            randomizeNorthernEdge();
        }

        if (neighbouringEdges != null && neighbouringEdges.keySet().contains(Direction.EAST)) {
            setEasternEdge(neighbouringEdges.get(Direction.EAST));
        } else {
            randomizeEasternEdge();
        }

        if (neighbouringEdges != null && neighbouringEdges.keySet().contains(Direction.SOUTH)) {
            setSouthernEdge(neighbouringEdges.get(Direction.SOUTH));
        } else {
            randomizeSouthernEdge();
        }

        if (neighbouringEdges != null && neighbouringEdges.keySet().contains(Direction.WEST)) {
            setWesternEdge(neighbouringEdges.get(Direction.WEST));
        } else {
            randomizeWesternEdge();
        }
    }

    private void randomizeNorthernEdge() {
        for (int x = 0; x <= MAX_X_INDEX; x++) {
            if (terrainTiles[x][0] == null) {
                terrainTiles[x][0] = nextTile(terrainTiles[x - 1][0].getTileType());
            }
        }
    }

    private void randomizeSouthernEdge() {
        for (int x = MAX_X_INDEX; x >= 0; x--) {
            if (terrainTiles[x][MAX_Y_INDEX] == null) {
                terrainTiles[x][MAX_Y_INDEX] = nextTile(terrainTiles[x + 1][MAX_Y_INDEX].getTileType());
            }
        }
    }

    private void randomizeEasternEdge() {
        for (int y = 0; y <= MAX_Y_INDEX; y++) {
            if (terrainTiles[MAX_X_INDEX][y] == null) {
                terrainTiles[MAX_X_INDEX][y] = nextTile(terrainTiles[MAX_X_INDEX][y - 1].getTileType());
            }
        }
    }

    private void randomizeWesternEdge() {
        for (int y = MAX_Y_INDEX; y >= 0; y--) {
            if (terrainTiles[0][y] == null) {
                terrainTiles[0][y] = nextTile(terrainTiles[0][y + 1].getTileType());
            }
        }
    }

    private void setNorthernEdge(TerrainTile[] edge) {
        for (int x = 0; x <= MAX_X_INDEX; x++) {
            terrainTiles[x][0] = edge[x];
        }
    }

    private void setEasternEdge(TerrainTile[] edge) {
        terrainTiles[MAX_X_INDEX] = edge;
    }

    private void setSouthernEdge(TerrainTile[] edge) {
        for (int x = 0; x <= MAX_X_INDEX; x++) {
            terrainTiles[x][MAX_Y_INDEX] = edge[x];
        }
    }

    private void setWesternEdge(TerrainTile[] edge) {
        terrainTiles[0] = edge;
    }

    private TerrainTile nextTile(TerrainType previousType) {
        TerrainTile temp = null;
        // Provides a random number between 1 and 100
        int rand = (int) (Math.random() * 100 + 1);
        switch (previousType) {
            case GRASS_TILE:
                temp = grassNeighbour(rand);
                break;
            case PLAINS_TILE:
                temp = plainsNeighbour(rand);
                break;
            case DESERT_TILE:
                temp = desertNeighbour(rand);
                break;
            case TUNDRA_TILE:
                temp = tundraNeighbour(rand);
                break;
            case SNOW_TILE:
                temp = snowNeighbour(rand);
                break;
            case WATER_TILE:
                temp = waterNeighbour(rand);
                break;
        }
        return temp;
    }

    /**
     * Returns a TerrainTile at the following percentages:
     * 55% = GrassTile
     * 30% = PlainsTile
     * 15% = WaterTile
     *
     * @param rand A random number between 1 and 100
     * @return A randomized tile following the above percentages
     */
    private TerrainTile grassNeighbour(int rand) {
        if (1 <= rand && rand <= 55) {
            return new GrassTile();
        } else if (56 <= rand && rand <= 85) {
            return new PlainsTile();
        } else {
            return new WaterTile();
        }
    }

    /**
     * Returns a TerrainTile at the following percentages:
     * 40% = PlainsTile
     * 20% = DesertTile
     * 20% = TundraTile
     * 15% = GrassTile
     * 5% = WaterTile
     *
     * @param rand A random number between 1 and 100
     * @return A randomized tile following the above percentages
     */
    private TerrainTile plainsNeighbour(int rand) {
        if (1 <= rand && rand <= 40) {
            return new PlainsTile();
        } else if (41 <= rand && rand <= 60) {
            return new DesertTile();
        } else if (61 <= rand && rand <= 80) {
            return new TundraTile();
        } else if (rand <= 81 && rand <= 95) {
            return new GrassTile();
        } else {
            return new WaterTile();
        }
    }

    /**
     * Returns a TerrainTile at the following percentages:
     * 50% = DesertTile
     * 35% = PlainsTile
     * 15% = WaterTile
     *
     * @param rand A random number between 1 and 100
     * @return A randomized tile following the above percentages
     */
    private TerrainTile desertNeighbour(int rand) {
        if (1 <= rand && rand <= 50) {
            return new DesertTile();
        } else if (51 <= rand && rand <= 85) {
            return new PlainsTile();
        } else {
            return new WaterTile();
        }
    }

    /**
     * Returns a TerrainTile at the following percentages:
     * 40% = TundraTile
     * 25% = SnowTile
     * 20% = PlainsTile
     * 15% = WaterTile
     *
     * @param rand A random number between 1 and 100
     * @return A randomized tile following the above percentages
     */
    private TerrainTile tundraNeighbour(int rand) {
        if (1 <= rand && rand <= 40) {
            return new TundraTile();
        } else if (41 <= rand && rand <= 65) {
            return new SnowTile();
        } else if (66 <= rand && rand <= 85) {
            return new PlainsTile();
        } else {
            return new WaterTile();
        }
    }

    /**
     * Returns a TerrainTile at the following percentages:
     * 60% = SnowTile
     * 40% = TundraTile
     *
     * @param rand A random number between 1 and 100
     * @return A randomized tile following the above percentages
     */
    private TerrainTile snowNeighbour(int rand) {
        if (1 <= rand && rand <= 60) {
            return new SnowTile();
        } else {
            return new TundraTile();
        }
    }

    /**
     * Returns a TerrainTile at the following percentages:
     * 60% = WaterTile
     * 10% = GrassTile
     * 10% = PlainsTile
     * 10% = DesertTile
     * 10% = TundraTile
     *
     * @param rand A random number between 1 and 100
     * @return A randomized tile following the above percentages
     */
    private TerrainTile waterNeighbour(int rand) {
        if (1 <= rand && rand <= 60) {
            return new WaterTile();
        } else if (61 <= rand && rand <= 70) {
            return new GrassTile();
        } else if (71 <= rand && rand <= 80) {
            return new PlainsTile();
        } else if (81 <= rand && rand <= 90) {
            return new DesertTile();
        } else {
            return new TundraTile();
        }
    }

    private void generateCenter() {
        for (int x = 1; x < MAX_X_INDEX; x++) {
            for (int y = 1; y < MAX_Y_INDEX; y++) {
                switch (getNeighbourTypeMajority(x, y)) {
                    case GRASS_TILE:
                        terrainTiles[x][y] = new GrassTile();
                        break;
                    case PLAINS_TILE:
                        terrainTiles[x][y] = new PlainsTile();
                        break;
                    case DESERT_TILE:
                        terrainTiles[x][y] = new DesertTile();
                        break;
                    case TUNDRA_TILE:
                        terrainTiles[x][y] = new TundraTile();
                        break;
                    case SNOW_TILE:
                        terrainTiles[x][y] = new SnowTile();
                        break;
                    case WATER_TILE:
                        terrainTiles[x][y] = new WaterTile();
                        break;
                }
            }
        }
    }

    private TerrainType getNeighbourTypeMajority(int x, int y) {
        AbstractMap<TerrainType, Integer> tileTypeAmounts = new EnumMap<TerrainType, Integer>(TerrainType.class);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                TerrainTile tempTile = terrainTiles[x + i][y + j];
                if (tempTile != null) {
                    TerrainType tempType = tempTile.getTileType();
                    if (!tileTypeAmounts.containsKey(tempType)) {
                        tileTypeAmounts.put(tempType, 1);
                    } else {
                        tileTypeAmounts.put(tempType, tileTypeAmounts.get(tempType) + 1);
                    }
                }
            }
        }
        int majority = 0;
        TerrainType majorityType = null;
        for (TerrainType type : tileTypeAmounts.keySet()) {
            if (tileTypeAmounts.get(type) > majority) {
                majority = tileTypeAmounts.get(type);
                majorityType = type;
            }
        }
        return majorityType;
    }

    public TerrainTile[] getTileBlockEdge(Direction edge) {
        TerrainTile[] temp = null;
        switch (edge) {
            case NORTH:
                temp = getNorthernEdge();
                break;
            case SOUTH:
                temp = getSouthernEdge();
                break;
            case EAST:
                temp = getEasternEdge();
                break;
            case WEST:
                temp = getWesternEdge();
                break;
        }
        return temp;
    }

    private TerrainTile[] getNorthernEdge() {
        TerrainTile[] temp = new TerrainTile[terrainTiles.length];
        for (int x = 0; x < terrainTiles.length; x++) {
            temp[x] = terrainTiles[x][0];
        }
        return temp;
    }

    private TerrainTile[] getSouthernEdge() {
        TerrainTile[] temp = new TerrainTile[terrainTiles.length];
        for (int x = 0; x < terrainTiles.length; x++) {
            temp[x] = terrainTiles[x][MAX_Y_INDEX];
        }
        return temp;
    }

    private TerrainTile[] getEasternEdge() {
        TerrainTile[] temp = new TerrainTile[terrainTiles[0].length];
        System.arraycopy(terrainTiles[MAX_X_INDEX], 0, temp, 0, terrainTiles[0].length);
        return temp;
    }

    private TerrainTile[] getWesternEdge() {
        TerrainTile[] temp = new TerrainTile[terrainTiles[0].length];
        System.arraycopy(terrainTiles[0], 0, temp, 0, terrainTiles[0].length);
        return temp;
    }
}