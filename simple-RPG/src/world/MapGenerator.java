package world;

import world.tile.Tile;

import java.util.Random;

/**
 * Created by Josef on 11/06/2015.
 * <p>
 *     Implementation of the diamond-square algorithm which is used to generate the map.
 * </p>
 */
public class MapGenerator {
    private final Random random = new Random();

    // Boolean used to check if a map has been generated
    private boolean generated = false;

    // Length of the map's side
    private int size = 1025;
    // The initial value for the map's corners
    private double seed = random.nextDouble();
    // The scale of the random offset
    private double scale = 0.5;
    private double[][] values;
    private Tile[][] tiles;

    public MapGenerator(int size) {
        this.size = size;
    }

    public MapGenerator(int size, double seed) {
        this.size = size;
        this.seed = seed % 1.0;
    }

    public double[][] getValues() throws MapNotGeneratedException {
        if (!generated)
            throw new MapNotGeneratedException();
        return values;
    }

    public Tile[][] getTiles() throws MapNotGeneratedException {
        if (!generated)
            throw new MapNotGeneratedException();
        return tiles;
    }

    public MapGenerator generate() {
        values = new double[size][size];
        tiles = new Tile[size][size];

        // Set the value of the map's corners to the seed value
        values[0][0] = seed;
        values[0][size-1] = seed;
        values[size-1][0] = seed;
        values[size-1][size-1] = seed;

        tiles[0][0] = Tile.getTile(seed);
        tiles[0][size-1] = Tile.getTile(seed);
        tiles[size-1][0] = Tile.getTile(seed);
        tiles[size-1][size-1] = Tile.getTile(seed);

        // Store the scale in a local variable
        double sc = scale;

        // Perform the diamond-square algorithm on smaller and smaller
        // areas and smaller and smaller offsets (geometrically smaller)
        // until a 2x2 (atomic unit) area is reached
        for (int side = size - 1; side >= 2; side /= 2, sc /= 2.0) {
            diamondSquare(side, sc);
        }

        // Set to true so we can catch exceptions
        generated = true;

        return this;
    }

    private void diamondSquare(int side, double sc) {
        // Half the side size
        int hs = side / 2;

        // Generate new square values
        for (int x = 0; x < size - 1; x += side) {
            for (int y = 0; y < size - 1; y += side) {
                square(x, y, side, sc);
            }
        }

        // Generate the diamond values
        for (int x = 0; x < size - 1; x += hs) {
            for (int y = (x + hs) % side; y < size - 1; y += side) {
                diamond(x, y, side, sc);
            }
        }
    }

    // Calculate the square value for the given coordinate
    private void square(int x, int y, int side, double sc) {
        // x, y is the top left corner
        //
        // a     b
        //
        //    p
        //
        // c     d
        //
        // Calculate the middle value (p) by averaging the corner
        // values and adding a (scaling) random offset
        double a = values[x][y];
        double b = values[x + side][y];
        double c = values[x][y + side];
        double d = values[x + side][y + side];

        // Calculate the value
        double val = valCalc(a, b, c, d, sc);

        // Distance to the middle of the square
        int hs = side / 2;
        // Set the middle value to the one which has been calculated
        values[x + hs][y + hs] = val;
        tiles[x + hs][y + hs] = Tile.getTile(val);
    }

    private void diamond(int x, int y, int side, double sc) {
        // Half the side size
        int hs = side / 2;

        // x, y is the center of the diamond
        //
        //      a
        //
        // b    p    c
        //
        //      d
        //
        // Calculate the middle value (p) by averaging the four
        // surrounding values. Modulo of the side size is applied
        // in order to achieve wrapping
        double a = values[x][(y - hs + size - 1) % (size - 1)];
        double b = values[(x - hs + size - 1) % (size - 1)][y];
        double c = values[(x + hs) % (size - 1)][y];
        double d = values[x][(y + hs) % (size - 1)];

        // Calculate the value
        double val = valCalc(a, b, c, d, sc);

        // Set the middle value
        values[x][y] = val;
        tiles[x][y] = Tile.getTile(val);

        // Wrap values on the edges
        if (x == 0) {
            values[size - 1][y] = val;
            tiles[size -1 ][y] = Tile.getTile(val);
        }
        if (y == 0) {
            values[x][size - 1] = val;
            tiles[x][size - 1] = Tile.getTile(val);
        }
    }

    // Calculate the value of the point surrounded by the given points and with an offset of the given scale
    private double valCalc(double a, double b, double c, double d, double sc) {
        // Calculate the average of the four points
        double avg = ((a + b + c + d) / 4.0);

        double val = -1;
        // Make sure the final value falls between 0 and 1.0
        while (val < 0 || val > 1.0) {
            // Calculate an offset based on the range and scale.
            // This offset can range between -sc and sc
            double offset = (random.nextDouble() * 2 * sc) - sc;
            val = avg + offset;
        }

        return val;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSeed(double seed) {
        this.seed = seed;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public class MapNotGeneratedException extends Exception {
        @Override
        public void printStackTrace() {
            System.err.println("Map has not been generated!");
        }
    }
}
