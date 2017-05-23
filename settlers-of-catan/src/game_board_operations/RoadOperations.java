package se.scramble_studios.nehro.settlers_of_catan.java.game_board_operations;

import se.scramble_studios.nehro.settlers_of_catan.java.game_elements.buildings.Road;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;
import se.scramble_studios.nehro.settlers_of_catan.res.data.Coordinate;
import se.scramble_studios.nehro.settlers_of_catan.res.data.SettlersMap;

import javax.swing.*;

/**
 * Created by Josef on 19/02/2015.
 * <p>
 *     A class containing operations on the game board involving roads
 * </p>
 */
public class RoadOperations {
    /**
     * <p>
     * Adds the given road to the road map and to the frame
     * </p>
     *
     * @param road The road to be added
     */
    public static void addRoad(Road road, SettlersMap<Coordinate, Road> roadMap, JComponent contentPane) {
        // Adds the road to the road map
        roadMap.insert(new Coordinate(road.getX(), road.getY()), road);
        // Adds the road to the frame
        road.setBounds(road.getX(), road.getY(), Constants.ROAD_WIDTH, Constants.ROAD_HEIGHT);
        road.setOpaque(true);
        contentPane.add(road, 1, 0);
    }
}
