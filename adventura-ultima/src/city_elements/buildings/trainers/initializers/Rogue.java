package se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers.initializers;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces.TrainerInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers.TrainerType;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A Trainer used to represent a city's rogue
 * </p>
 */
public class Rogue implements TrainerInitializer {

    @Override
    public TrainerType getTrainerType() {
        return TrainerType.ROGUE;
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.TRAINER;
    }
}
