package se.wgco.jgf.adventura_ultima.city_elements.buildings;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     An enum defining the different types of
 *     buildings which can be present in a town.
 * </p>
 */
public enum BuildingType {
    TAVERN, HEALERS_HUT, SHOP, IMPROVER, TRAINER;

    private static final List<BuildingType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static BuildingType randomBuildingType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
