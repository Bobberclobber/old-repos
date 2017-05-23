package se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.ImproverType;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.BuildingInitializer;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     An interface which define methods used to initialize
 *     Buildings of the type Improver
 * </p>
 */
public interface ImproverInitializer extends BuildingInitializer {
    ImproverType getImproverType();
}