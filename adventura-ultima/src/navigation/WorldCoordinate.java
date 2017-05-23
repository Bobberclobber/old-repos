package se.wgco.jgf.adventura_ultima.navigation;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     Class used to represent any given
 *     tile's position on the world map.
 * </p>
 */
public class WorldCoordinate {
    private int x = 0;
    private int y = 0;

    public WorldCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
