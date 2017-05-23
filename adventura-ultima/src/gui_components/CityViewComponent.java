package se.wgco.jgf.adventura_ultima.gui_components;

import se.wgco.jgf.adventura_ultima.city.City;
import se.wgco.jgf.adventura_ultima.city_elements.CityElement;
import se.wgco.jgf.adventura_ultima.constants.GameWindowConstants;
import se.wgco.jgf.adventura_ultima.constants.WorldConstants;
import se.wgco.jgf.adventura_ultima.graphics_maps.EnumColorMap;
import se.wgco.jgf.adventura_ultima.graphics_maps.EnumTextMap;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 07/05/2014.
 * <p>
 *     A JComponent which paints the part of the game
 *     where the player is navigating a city
 * </p>
 */
public class CityViewComponent extends JComponent {
    private final int CITY_VIEW_WIDTH = GameWindowConstants.PLAY_FIELD_WIDTH;
    private final int CITY_VIEW_HEIGHT = GameWindowConstants.PLAY_FIELD_HEIGHT;
    private final int CITY_ELEMENT_SIDE = WorldConstants.CITY_ELEMENT_SIDE;

    private City currentCity = null;
    private CityElement[][] cityMap = null;

    public CityViewComponent(City currentCity) {
        this.currentCity = currentCity;
        cityMap = currentCity.getCityMap();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(int x = 0; x < currentCity.getMapWidth(); x++) {
            for(int y = 0; y < currentCity.getMapHeight(); y++) {
                Color cityElementColor = EnumColorMap.cityElementMap.get(cityMap[x][y].getCityElementType());
                g2d.setColor(cityElementColor);
                g2d.fillRect(x * CITY_ELEMENT_SIDE, y * CITY_ELEMENT_SIDE, CITY_ELEMENT_SIDE, CITY_ELEMENT_SIDE);
            }
        }
    }
}
