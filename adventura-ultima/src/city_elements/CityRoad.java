package se.wgco.jgf.adventura_ultima.city_elements;

import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.CityElementInitializer;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A CityElement used to represent the roads of a city
 * </p>
 */
public class CityRoad implements CityElementInitializer {

    @Override
    public CityElementType getCityElementType() {
        return CityElementType.CITY_ROAD;
    }
}
