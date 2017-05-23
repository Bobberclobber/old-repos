package core;

/**
 * Created by Josef on 04/06/2015.
 * <p>
 *     Abstraction of world regular- and block-coordinates,
 *     as well as on-screen coordinates
 * </p>
 */
public class Coordinate {
    public float xCord;
    public float yCord;
    public int zCord;
    public int blockX;
    public int blockY;

    // Constructor
    public Coordinate(float xCord, float yCord, int zCord, int blockX, int blockY) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.zCord = zCord;
        this.blockX = blockX;
        this.blockY = blockY;
    }
}
