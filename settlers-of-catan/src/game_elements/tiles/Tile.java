package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles;

import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.GameElement;
import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.buildings.Road;
import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.buildings.Town;
import se.scramble_studios.nehro.settlers_of_catan.java.gui_components.GraphicalTile;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Josef on 13/01/2015.
 * <p>
 *     The superclass used by all tiles
 * </p>
 */
public abstract class Tile extends GraphicalTile implements GameElement {
    private int x;
    private int y;
    private int neighboursIndex = 0;
    private int roadsIndex = 0;
    private int townsIndex = 0;
    private Tile[] neighbours = new Tile[6];
    private Road[] roads = new Road[6];
    private Town[] towns = new Town[3];

    public Tile(Image tileGraphics, int x, int y) {
        super(tileGraphics);
        this.x = x;
        this.y = y;
        TileMouseHandler mouseHandler = new TileMouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return Constants.TILE_WIDTH;
    }

    @Override
    public int getHeight() {
        return Constants.TILE_HEIGHT;
    }

    @Override
    public Polygon getPolygon() {
        return Constants.TILE_POLYGON;
    }

    public void addNeighbour(Tile tile) {
        try {
            neighbours[neighboursIndex++] = tile;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }

    public void addRoad(Road road) {
        try {
            roads[roadsIndex++] = road;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }

    public void addTown(Town town) {
        try {
            towns[townsIndex++] = town;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }

    public Tile[] getNeighbours() {
        return neighbours;
    }

    public Road[] getRoads() {
        return roads;
    }

    public Town[] getTowns() {
        return towns;
    }

    public abstract Constants.TileType getTileType();

    @Override
    public String toString() {
        return "Type: " + getTileType() + " X: " + x + " Y: " + y;
    }

    /**
     * <p>
     *     A MouseAdapter used to handler events
     * </p>
     */
    private class TileMouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            clickEvent(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            clickEvent(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        private void clickEvent(MouseEvent e) {

        }
    }
}
