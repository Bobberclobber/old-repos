package se.scramble_studios.nehro.settlers_of_catan.java.game_elements;

import java.awt.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 *     Interface used to encapsulate all game elements
 * </p>
 */
public interface GameElement {
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public Polygon getPolygon();
}
