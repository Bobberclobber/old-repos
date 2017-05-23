package se.wgco.jgf.adventura_ultima.character_classes.concrete_classes;

import se.wgco.jgf.adventura_ultima.character_classes.ClassType;
import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.character_classes.class_interfaces.DexterityClass;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A CharacterClass defining the playable class scout
 * </p>
 */
public class Scout implements DexterityClass {

    @Override
    public void initBaseStats(PlayerCharacter character) {
        character.setDexterity(5);
        character.setIntellect(2);
        character.setStrength(3);
    }

    @Override
    public ClassType getClassType() {
        return ClassType.SCOUT;
    }
}
