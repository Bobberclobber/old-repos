package se.wgco.jgf.adventura_ultima.character_classes.concrete_classes;

import se.wgco.jgf.adventura_ultima.character_classes.ClassType;
import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.character_classes.class_interfaces.IntellectClass;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A CharacterClass defining the playable class sorcerer
 * </p>
 */
public class Sorcerer implements IntellectClass {

    @Override
    public void initBaseStats(PlayerCharacter character) {
        character.setDexterity(2);
        character.setIntellect(6);
        character.setStrength(2);
    }

    @Override
    public ClassType getClassType() {
        return ClassType.SORCERER;
    }
}
