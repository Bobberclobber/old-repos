package se.scramble_studios.nehro.settlers_of_catan.res.data;

/**
 * Created by Josef on 07/02/2015.
 * <p>
 *     A data structure which stores the x and y-coordinates of a tile.
 * </p>
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        return x == ((Coordinate) obj).getX() && y == ((Coordinate) obj).getY();
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }
}
