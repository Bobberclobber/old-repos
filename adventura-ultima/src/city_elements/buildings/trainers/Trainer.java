package se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers;

import se.wgco.jgf.adventura_ultima.city_elements.buildings.BuildingType;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.building_interfaces.TrainerInitializer;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers.initializers.Brawler;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers.initializers.Rogue;
import se.wgco.jgf.adventura_ultima.city_elements.buildings.trainers.initializers.Wizard;
import se.wgco.jgf.adventura_ultima.city_elements.city_element_interfaces.BuildingInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A meta-class which all trainers extend
 * </p>
 */
public class Trainer implements BuildingInitializer {

    private TrainerType type = null;
    private static List<TrainerInitializer> trainerInitializers = null;

    public Trainer(TrainerInitializer initializer) {
        type = initializer.getTrainerType();
    }

    public TrainerType getTrainerType() {
        return type;
    }

    @Override
    public BuildingType getBuildingType() {
        return BuildingType.TRAINER;
    }

    private static void makeInitializerList() {
        trainerInitializers.add(new Brawler());
        trainerInitializers.add(new Rogue());
        trainerInitializers.add(new Wizard());
    }

    public static TrainerInitializer getRandomTrainerInitializer() {
        trainerInitializers = new ArrayList<TrainerInitializer>();
        makeInitializerList();
        int randomNum = (int) (Math.random() * trainerInitializers.size());
        return trainerInitializers.get(randomNum);
    }
}
