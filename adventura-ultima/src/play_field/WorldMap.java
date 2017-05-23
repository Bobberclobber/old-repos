package se.wgco.jgf.adventura_ultima.play_field;

import se.wgco.jgf.adventura_ultima.navigation.Direction;
import se.wgco.jgf.adventura_ultima.navigation.WorldCoordinate;
import se.wgco.jgf.adventura_ultima.tiles.TerrainTile;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     A class representing the world map.
 * </p>
 */
public class WorldMap {
    /**
     * A map connecting every tile block to a coordinate in the world.
     */
    private AbstractMap<WorldCoordinate, TileBlock> worldMap = null;
    /**
     * A set of all coordinates which contain a tile block
     */
    private Set<WorldCoordinate> coordinates = null;
    /**
     * The current tile block, i.e. the tile block which is currently displayed
     */
    private TileBlock currentTileBlock = null;

    public WorldMap() {
        worldMap = new HashMap<WorldCoordinate, TileBlock>();
        coordinates = worldMap.keySet();
        generateInitialTileBlocks();
    }

    public AbstractMap<WorldCoordinate, TileBlock> getWorldMap(){
        return worldMap;
    }

    public Set<WorldCoordinate> getCoordinates() {
        return coordinates;
    }

    /**
     * Uses an existing world coordinate in order to access
     * a certain block
     *
     * @param coordinate The coordinate, the block at which is
     *                   to be returned. Note that this have to
     *                   already exist in the set coordinates.
     *                   Creating a new WorldCoordinate in order
     *                   to access a tile block will not work
     * @return Returns the tile block linked in worldMap to the
     *         given coordinate
     */
    public TileBlock getTileBlockAt(WorldCoordinate coordinate) {
        if (coordinates.contains(coordinate)) {
            return worldMap.get(coordinate);
        }
        System.out.println("No tile at that coordinate exists");
        return null;
    }

    /**
     * Returns the tile block linked to the WorldCoordinate
     * corresponding to the values passed as parameters
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The tile block at the world coordinate whose
     *         x- and y-values correspond to the ones passed as parameters
     */
    public TileBlock getTileBlockAt(int x, int y) {
        for (WorldCoordinate coordinate : coordinates) {
            if (coordinate.getX() == x && coordinate.getY() == y) {
                return worldMap.get(coordinate);
            }
        }
        System.out.println("No tile at that coordinate exists");
        return null;
    }

    public TileBlock getCurrentTileBlock() {
        return currentTileBlock;
    }

    private void generateInitialTileBlocks() {
        WorldCoordinate tempCoordinate = new WorldCoordinate(0, 0);
        TileBlock temp = new TileBlock(tempCoordinate);
        worldMap.put(tempCoordinate, temp);
        currentTileBlock = temp;
        generateNeighbouringBlocks(temp);
    }

    public void switchToTileBlock(TileBlock block) {
        currentTileBlock = block;
        generateNeighbouringBlocks(block);
        coordinates = worldMap.keySet();
    }

    public void switchToTileBlock(Direction direction) {
        int currentTileX = currentTileBlock.getCoordinate().getX();
        int currentTileY = currentTileBlock.getCoordinate().getY();
        TileBlock temp = null;
        switch (direction) {
            case NORTH:
                temp = getTileBlockAt(currentTileX, currentTileY - 1);
                break;
            case SOUTH:
                temp = getTileBlockAt(currentTileX, currentTileY + 1);
                break;
            case EAST:
                temp = getTileBlockAt(currentTileX + 1, currentTileY);
                break;
            case WEST:
                temp = getTileBlockAt(currentTileX - 1, currentTileY);
                break;
        }
        switchToTileBlock(temp);
    }

    /**
     * Generates neighbours for the given block
     * in all directions where no neighbour already exist
     *
     * @param block The block whose remaining neighbours are to be generated
     */
    private void generateNeighbouringBlocks(TileBlock block) {
        WorldCoordinate coordinate = block.getCoordinate();
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (!hasNeighbourAt(i, j)) {
                    AbstractMap<Direction, TerrainTile[]> neighbouringEdges = getNeighbouringEdges(i, j);
                    WorldCoordinate temp = new WorldCoordinate(i, j);
                    worldMap.put(temp, new TileBlock(temp, neighbouringEdges));
                }
            }
        }
    }

    private boolean hasNeighbourAt(int x, int y) {
        for (WorldCoordinate coordinate : coordinates) {
            if (coordinate.getX() == x && coordinate.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private AbstractMap<Direction, TerrainTile[]> getNeighbouringEdges(int x, int y) {
        AbstractMap<Direction, TerrainTile[]> neighbouringEdges = new EnumMap<Direction, TerrainTile[]>(Direction.class);
        // Check for a tile to the north
        if (hasNeighbourAt(x, y - 1)) {
            neighbouringEdges.put(Direction.NORTH, getTileBlockAt(x, y - 1).getTileBlockEdge(Direction.SOUTH));
        }

        // Check for a tile to the south
        if (hasNeighbourAt(x, y + 1)) {
            neighbouringEdges.put(Direction.SOUTH, getTileBlockAt(x, y + 1).getTileBlockEdge(Direction.NORTH));
        }

        // Check for a tile to the east
        if (hasNeighbourAt(x + 1, y)) {
            neighbouringEdges.put(Direction.EAST, getTileBlockAt(x + 1, y).getTileBlockEdge(Direction.WEST));
        }

        // Check for a tile to the west
        if (hasNeighbourAt(x - 1, y)) {
            neighbouringEdges.put(Direction.WEST, getTileBlockAt(x - 1, y).getTileBlockEdge(Direction.EAST));
        }

        return neighbouringEdges;
    }
}
