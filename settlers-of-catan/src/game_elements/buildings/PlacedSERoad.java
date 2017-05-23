package se.scramble_studios.nehro.settlers_of_catan.java.game_elements.buildings;

import se.scramble_studios.nehro.settlers_of_catan.res.data.Constants;

import javax.swing.*;

/**
 * Created by Josef on 14/01/2015.
 * <p>
 *     A representation of a road phasing south-east.
 * </p>
 */
public class PlacedSERoad extends Road {

    public PlacedSERoad(int x, int y, String color) {
        super(new ImageIcon(Constants.ROAD_IMG_ROOT + "road_se_" + color + ".png").getImage(),
                x, y, Constants.ROAD_SE_POLYGON);
    }
}
