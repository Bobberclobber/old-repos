package se.wgco.jgf.adventura_ultima.city_elements.buildings;

import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.BuildingInitializer;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A Building used to represent a city's tavern
 * </p>
 */
public class Tavern implements BuildingInitializer {

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.TAVERN;
    }
}
