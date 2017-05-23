package objects.core;

import core.Coordinate;
import core.Dimension;
import graphics.Renderer;
import graphics.Texture;
import math.Vector2f;

/**
 * Created by Josef on 19/05/2015.
 * <p>
 *     The super class of all objects in the game
 *     (i.e. static and mobile environmentally controlled objects
 *     as well as player controlled objects)
 * </p>
 */
public abstract class GameObject {
    // Values used for game-mechanics
    private Coordinate coordinate;
    private Dimension dimension;
    private boolean controlled;
    private boolean stationary;
    private boolean solid;

    // Values used for movement
    protected float speed;
    protected Vector2f pos;
    protected Vector2f prevPos;
    protected Vector2f dir;
    protected Vector2f prevDir;

    // Values used for rendering
    private float offsetX;
    private float offsetY;
    private final Texture texture;

    // Constructor
    public GameObject(Coordinate coordinate, Dimension dimension, float speed,
                      boolean controlled, boolean stationary, boolean solid,
                      Texture texture, float offsetX, float offsetY) {

        this.coordinate = coordinate;
        this.dimension = dimension;
        this.controlled = controlled;
        this.stationary = stationary;
        this.solid = solid;

        this.speed = speed;
        pos = new Vector2f(coordinate.xCord, coordinate.yCord);
        prevPos = new Vector2f(coordinate.xCord, coordinate.yCord);
        prevDir = new Vector2f();

        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.texture = texture;
    }

    // Handles input for the object
    public abstract void input();

    // Updates the object
    public abstract void update(float delta);

    // Renders the object
    public void render(Renderer renderer, float alpha) {
        Vector2f interpolatedPosition = prevPos.lerp(pos, alpha);
        float x = interpolatedPosition.x;
        float y = interpolatedPosition.y;
        renderer.drawTextureRegion(texture, x, y, offsetX, offsetY, dimension.width, dimension.height);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public boolean isControlled() {
        return controlled;
    }

    public void setControlled(boolean controlled) {
        this.controlled = controlled;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}
