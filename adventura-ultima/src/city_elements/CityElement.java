package se.wgco.jgf.adventura_ultima.city_elements;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.Building;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.CityElementInitializer;
import se.wgco.jgf.adventura_ultima.navigation.CityCoordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     A class which all city elements inherit from.
 * </p>
 */
public class CityElement {

    private CityCoordinate coordinate = null;
    private CityElementType type = null;
    private static List<CityElementInitializer> cityElementInitializers = null;

    public CityElement(CityCoordinate coordinate, CityElementInitializer initializer) {
        this.coordinate = coordinate;
        type = initializer.getCityElementType();
    }

    public CityCoordinate getCoordinate() {
        return coordinate;
    }

    public CityElementType getCityElementType() {
        return type;
    }

    private static void makeInitializerList() {
        cityElementInitializers.add(new Building(Building.getRandomBuildingInitializer()));
        cityElementInitializers.add(new CityGate());
        cityElementInitializers.add(new CityRoad());
    }

    public static CityElementInitializer getRandomCityElementInitializer() {
        cityElementInitializers = new ArrayList<CityElementInitializer>();
        makeInitializerList();
        int randomNum = (int) (Math.random() * cityElementInitializers.size());
        return cityElementInitializers.get(randomNum);
    }
}
