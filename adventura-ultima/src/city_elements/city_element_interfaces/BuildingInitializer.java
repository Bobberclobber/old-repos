package se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     An interface which define methods used to initialize
 *     CityElements of the type Building
 * </p>
 */
public interface BuildingInitializer {
    BuildingType getBuildingType();
}
