package se.wgco.jgf.adventura_ultima.navigation;

/**
 * Created by Josef on 30/04/2014.
 * <p>
 *     Class used to represent any given
 *     city element's position in a city.
 * </p>
 */
public class CityCoordinate {
    private int x = 0;
    private int y = 0;

    public CityCoordinate(int x, int y) {
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
