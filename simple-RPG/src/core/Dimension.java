package core;

/**
 * Created by Josef on 04/06/2015.
 * <p>
 *     A class used to store the absolute and relative dimensions of a game object
 * </p>
 */
public class Dimension {
    public int width;
    public int height;
    public int depth;
    public float scale;

    // Constructor
    public Dimension(int width, int height, int depth, float scale) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.scale = scale;
    }
}
