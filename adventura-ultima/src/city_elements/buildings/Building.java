package se.wgco.jgf.adventura_ultima.city_elements.buildings;

import se.wgco.jgf.adventura_ultima.city_elements.CityElementType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.improvers.Improver;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.shops.Shop;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers.Trainer;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.BuildingInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.CityElementInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A meta-class which all buildings extend
 * </p>
 */
public class Building implements CityElementInitializer {

    private BuildingType type = null;
    private static List<BuildingInitializer> buildingInitializers = null;

    public Building(BuildingInitializer initializer) {
        type = initializer.getBuildingType();
    }

    public BuildingType getBuildingType() {
        return type;
    }

    @Override
    public CityElementType getCityElementType() {
        return CityElementType.BUILDING;
    }

    private static void makeInitializerList() {
        buildingInitializers.add(new Improver(Improver.getRandomImproverInitializer()));
        buildingInitializers.add(new Shop(Shop.getRandomShopInitializer()));
        buildingInitializers.add(new Trainer(Trainer.getRandomTrainerInitializer()));
    }

    public static BuildingInitializer getRandomBuildingInitializer() {
        buildingInitializers = new ArrayList<BuildingInitializer>();
        makeInitializerList();
        int randomNum = (int) (Math.random() * buildingInitializers.size());
        return buildingInitializers.get(randomNum);
    }
}
