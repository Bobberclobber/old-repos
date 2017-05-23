package world.tile;

import graphics.Renderer;
import math.Vector2f;

import java.awt.*;

// TODO: Possible improvement: add temperature and aridity values to improve tile randomization
// TODO: Available T/A pairs: ( Sn ), ( Ro ), (3, 5), ( Bl ), ( La )
// TODO:                      (1, 4), (2, 4), (3, 4), (4, 4), ( De )
// TODO:                      (1, 3), (2, 3), (3, 3), ( Pl ), (5, 3)
// TODO:                      (1, 2), (2, 2), ( Gr ), (4, 2), (5, 2)
// TODO:                      (1, 1), ( Wa ), ( Fo ), (4, 1), (5, 1)
// TODO: Possible new terrains:
// TODO:    Tundra          (1, 4)
// TODO:    Rain-forest     (5, 1)
// TODO:    Swamp           (4, 2)
// TODO:

/**
 * Created by Josef on 15/06/2015.
 * <p>
 *     The superclass for all tiles
 * </p>
 */
public abstract class Tile {
    public final int SIZE = 75;
    private static final Tile[] TILES = {
            new BlightTile(0, 0),
            new DesertTile(0, 0),
            new ForestTile(0, 0),
            new GrassTile(0, 0),
            new LavaTile(0, 0),
            new PlainsTile(0, 0),
            new RockTile(0, 0),
            new SnowTile(0, 0),
            new WaterTile(0, 0)};

    // Positional variables
    public Vector2f pos;
    public Vector2f prevPos;
    public double scale = 1.0;

    // Graphics variables
    private int offsetX;
    private int offsetY;
    // TODO: Uncomment this when textures have been created
    //private Texture texture = Texture.loadTexture("res/img/tile_tex.png");
    private Color color;

    // Variables used for in-game operations
    public TileType type;
    public int temperature; // Between 1 and 5
    public int aridity; // Between 1 and 5
    //
    // Temperature / Aridity chart
    // A - - - - - - - - - - -
    //   | S | R |   | B | L |
    //   - - - - - - - - - - -
    //   |   |   |   |   | D |
    //   - - - - - - - - - - -
    //   |   |   |   | P |   |
    //   - - - - - - - - - - -
    //   |   |   | G |   |   |
    //   - - - - - - - - - - -
    //   |   | W | F |   |   |
    //   - - - - - - - - - - - T

    public Tile(float x, float y, int offsetX, int offsetY, Color color, TileType type, int temperature, int aridity) {
        pos = new Vector2f(x, y);
        prevPos = new Vector2f(x, y);

        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.color = color;

        this.type = type;
        this.temperature = temperature;
        this.aridity = aridity;
    }

    // Updates the tile by changing its on-screen position
    public void update(Vector2f delta) {
        prevPos = new Vector2f(pos.x, pos.y);
        pos = pos.add(delta);
    }

    public void render(Renderer renderer, float alpha) {
        Vector2f lerpPos = prevPos.lerp(pos, alpha);
        // TODO: Uncomment this when textures have been created
        //renderer.drawTextureRegion(texture, lerpPos.x, lerpPos.y, offsetX, offsetY, SIZE, SIZE);
    }

    // TODO: Change this function to alter the distribution of terrains
    public static Tile getTile (double n) {
            // LAVA
        if (n < 0.05) return TILES[4];

            // BLIGHT
        else if (n < 0.12) return TILES[0];

            // DESERT
        else if (n < 0.25) return TILES[1];

            // PLAINS
        else if (n < 0.40) return TILES[5];

            // ROCK
        else if (n < 0.44) return TILES[6];

            // GRASS
        else if (n < 0.62) return TILES[3];

            // FOREST
        else if (n < 0.70) return TILES[2];

            // WATER
        else if (n < 0.82) return TILES[8];

            // FOREST
        else if (n < 0.89) return TILES[2];

            // ROCK
        else if (n < 0.93) return TILES[6];

            // SNOW
        else if (n <= 1.0) return TILES[7];

            // DEFAULT
        else return TILES[3];
        //return TILES[(int) (n * TILES.length)];
    }

    // TODO: Implement this improvement of the above function
    // An improved get tile function which takes into account
    // the type of the tiles which were used to generate the
    // average value as well as the current scale which we
    // relate to
    public static Tile getTileAdvanced(Tile a, Tile b, Tile c, Tile d, double scale) {
        return new GrassTile(0, 0);
    }

    public Color getColor() {
        return color;
    }

    public enum TileType {
        BLIGHT, DESERT, FOREST, GRASS, LAVA, PLAINS, ROCK, SNOW, WATER
    }
}
