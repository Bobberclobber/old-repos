package se.wgco.jgf.adventura_ultima.character_classes.concrete_classes;

import se.wgco.jgf.adventura_ultima.character_classes.ClassType;
import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.character_classes.class_interfaces.StrengthClass;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A CharacterClass defining the playable class warrior
 * </p>
 */
public class Warrior implements StrengthClass {

    @Override
    public void initBaseStats(PlayerCharacter character) {
        character.setDexterity(3);
        character.setIntellect(2);
        character.setStrength(5);
    }

    @Override
    public ClassType getClassType() {
        return ClassType.WARRIOR;
    }
}
