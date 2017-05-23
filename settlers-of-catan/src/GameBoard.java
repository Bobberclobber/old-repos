package se.scramble_studios.nehro.settlers_of_catan.java;

import se.scramble_studios.nehro.settlers_of_catan.java.game_board_operations.TileOperations;
import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.buildings.Road;
import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles.Tile;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Coordinate;
import se.scramble_studios.nehro.settlers_of_catan.res.data.RandomGenerator;
import se.scramble_studios.nehro.settlers_of_catan.res.data.SettlersMap;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 * The graphical representation of the game board.
 * Observe! When adding an adjacent tile, the offsets should be:
 * x: 150 when diagonally right, -150 when diagonally left, 300 when right, -300 when left
 * y: 86 when diagonally below, -86 when diagonally above, 173 when below, -173 when above
 * The offsets of the roads probably match the tiles' offsets
 * </p>
 */
public class GameBoard extends JFrame {
    private SettlersMap<Coordinate, Tile> tileMap = new SettlersMap<>();
    private SettlersMap<Coordinate, Road> roadMap = new SettlersMap<>();
    private JLayeredPane contentPane;

    public GameBoard() {
        super("Settlers of Catan");

        // Creates JComponents
        contentPane = new JLayeredPane();

        /*
         * Modifies the JComponents
         */
        // Content pane
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        contentPane.setBounds(0, 0, (int) screenDim.getWidth(), (int) screenDim.getHeight());

        /*
        * Randomizes the board
        * */
        createRandomBoard(Constants.STANDARD_BOARD_LEVEL);

        // Modifies the JFrame
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adds JComponents to the frame
        this.add(contentPane, BorderLayout.CENTER);

        // Makes the frame show up correctly
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * <p>
     * Creates a random board by "spiraling" out from a single tile
     * in the middle of the screen, using a pseudo-BFS algorithm
     * </p>
     *
     * @param levels The number of levels the board should have,
     *               where one level is a layer of tiles which,
     *               when added, causes the board to assume a hexagonal shape
     */
    private void createRandomBoard(int levels) {
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        int midX = (int) (screenDim.getWidth() / 2) - (Constants.TILE_WIDTH / 2);
        int midY = (int) (screenDim.getHeight() / 2) - (Constants.TILE_HEIGHT / 2);

        // Initializes variables used to create the board
        int tileNum = TileOperations.calcTileNum(levels); // Used to determine the number of loops performed by the BFS algorithm
        int totalTileNum = tileNum; // Stores the total number of tile
        int outerLayerTileNum = TileOperations.outerLayerTileNum(levels); // Stores the number of tiles in the outermost layer
        Queue<Tile> pendingTiles = new ArrayDeque<>(); // Stores the tiles to be processed
        ArrayList<Coordinate> coordList = new ArrayList<>(); // Stores the neighbouring coordinates of each tile
        Tile initialTile = RandomGenerator.randomLandTile(midX, midY); // The initial tile
        TileOperations.addTile(initialTile, tileMap, contentPane); // Adds the initial tile to the frame
        pendingTiles.add(initialTile); // Adds the first tile to the pending list
        tileNum--; // Decrements the tile number

        while (!pendingTiles.isEmpty()) {
            Tile currTile = pendingTiles.peek();
            pendingTiles.remove(currTile);
            // Gets the current tile's coordinates
            int currX = currTile.getX();
            int currY = currTile.getY();

            // Creates a list containing all coordinates neighbouring to the current tile
            coordList.add(new Coordinate(currX, currY - 2 * Constants.TILE_Y_OFFSET));
            coordList.add(new Coordinate(currX, currY + 2 * Constants.TILE_Y_OFFSET));
            coordList.add(new Coordinate(currX - Constants.TILE_X_OFFSET, currY - Constants.TILE_Y_OFFSET));
            coordList.add(new Coordinate(currX - Constants.TILE_X_OFFSET, currY + Constants.TILE_Y_OFFSET));
            coordList.add(new Coordinate(currX + Constants.TILE_X_OFFSET, currY - Constants.TILE_Y_OFFSET));
            coordList.add(new Coordinate(currX + Constants.TILE_X_OFFSET, currY + Constants.TILE_Y_OFFSET));

            // Iterates over the coordinates and adds a new tile at the
            // coordinate if no tile exists at the current coordinate
            for (Coordinate coord : coordList)
                if (!tileMap.containsKey(coord) && tileNum > 0) {
                    Tile newTile; // The new tile to be added
                    // Adds a random land or sea tile depending on which layer the tile belongs to
                    if(tileNum >= totalTileNum - outerLayerTileNum)
                        newTile = RandomGenerator.randomLandTile(coord.getX(), coord.getY());
                    else
                        newTile = RandomGenerator.randomSeaTile(coord.getX(), coord.getY());

                    pendingTiles.add(newTile); // Marks the new tile as pending
                    TileOperations.addTile(newTile, tileMap, contentPane); // Adds the tile to the board
                    // Adds the new tile as a neighbour to the current tile
                    currTile.addNeighbour(newTile);
                    tileNum--;
                } else
                    currTile.addNeighbour(tileMap.get(coord));
            coordList.clear();
        }
    }
}
