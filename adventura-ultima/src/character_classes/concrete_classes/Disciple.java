package se.wgco.jgf.adventura_ultima.character_classes.concrete_classes;

import se.wgco.jgf.adventura_ultima.character_classes.ClassType;
import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.character_classes.class_interfaces.StrIntHybrid;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A CharacterClass defining the playable class disciple
 * </p>
 */
public class Disciple implements StrIntHybrid {

    @Override
    public void initBaseStats(PlayerCharacter character) {
        character.setDexterity(2);
        character.setIntellect(4);
        character.setStrength(4);
    }

    @Override
    public ClassType getClassType() {
        return ClassType.DISCIPLE;
    }
}
