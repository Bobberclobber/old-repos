package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.buildings;

import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.GameElement;
import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.tiles.Tile;
import se.scramble_studios.nehro.settlers_of_catan.java.gui_components.GraphicalRoad;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import java.awt.*;

/**
 * Created by Josef on 12/01/2015.
 * <p>
 *     The super class used by all roads
 * </p>
 */
public abstract class Road extends GraphicalRoad implements GameElement {
    private int x;
    private int y;
    private Polygon roadPolygon;
    private Town neighbouringTown = null;
    private int tilesIndex = 0;
    private int roadsIndex = 0;
    private Tile[] neighbouringTiles = new Tile[4];
    private Road[] neighbouringRoads = new Road[4];

    public Road(Image roadGraphics, int x, int y, Polygon roadPolygon) {
        super(roadGraphics, roadPolygon);
        this.x = x;
        this.y = y;
        this.roadPolygon = roadPolygon;
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
        return Constants.ROAD_WIDTH;
    }

    @Override
    public int getHeight() {
        return Constants.ROAD_HEIGHT;
    }

    @Override
    public Polygon getPolygon() {
        return roadPolygon;
    }

    public void addNeighboruingTown(Town town) {
        neighbouringTown = town;
    }

    public void addNeighbouringTile(Tile tile) {
        try {
            neighbouringTiles[tilesIndex++] = tile;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }

    public void addNeighbouringRoad(Road road) {
        try {
            neighbouringRoads[roadsIndex++] = road;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }

    public Town getNeighbouringTown() {
        return neighbouringTown;
    }

    public Tile[] getNeighbouringTiles() {
        return neighbouringTiles;
    }

    public Road[] getNeighbouringRoads() {
        return neighbouringRoads;
    }
}
