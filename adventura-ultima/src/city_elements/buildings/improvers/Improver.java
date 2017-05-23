package se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces.ImproverInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.initializers.Enchanter;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.initializers.Smith;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.initializers.Tailor;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.initializers.Tanner;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.BuildingInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A meta-class which all improvers extend
 * </p>
 */
public class Improver implements BuildingInitializer {

    private ImproverType type = null;
    private static List<ImproverInitializer> improverInitializers = null;

    public Improver(ImproverInitializer initializer) {
        type = initializer.getImproverType();
    }

    public ImproverType getImproverType() {
        return type;
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.IMPROVER;
    }

    private static void makeInitializerList() {
        improverInitializers.add(new Enchanter());
        improverInitializers.add(new Smith());
        improverInitializers.add(new Tailor());
        improverInitializers.add(new Tanner());
    }

    public static ImproverInitializer getRandomImproverInitializer() {
        improverInitializers = new ArrayList<ImproverInitializer>();
        makeInitializerList();
        int randomNum = (int) (Math.random() * improverInitializers.size());
        return improverInitializers.get(randomNum);
    }
}
