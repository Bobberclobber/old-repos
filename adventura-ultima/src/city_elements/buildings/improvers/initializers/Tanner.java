package se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.initializers;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces.ImproverInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.ImproverType;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     An ImprovementInitializer defining the improver tanner
 * </p>
 */
public class Tanner implements ImproverInitializer {

    @Override
    public ImproverType getImproverType() {
        return ImproverType.TANNER;
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.IMPROVER;
    }
}