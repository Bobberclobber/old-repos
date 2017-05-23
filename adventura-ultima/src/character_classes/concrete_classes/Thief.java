package se.wgco.jgf.adventura_ultima.character_classes.concrete_classes;

import se.wgco.jgf.adventura_ultima.character_classes.ClassType;
import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.character_classes.class_interfaces.IntDexHybrid;

/**
 * Created by Josef on 14/05/2014.
 * <p>
 *     A CharacterClass defining the playable class thief
 * </p>
 */
public class Thief implements IntDexHybrid {

    @Override
    public void initBaseStats(PlayerCharacter character) {
        character.setDexterity(4);
        character.setIntellect(4);
        character.setStrength(2);
    }

    @Override
    public ClassType getClassType() {
        return ClassType.THIEF;
    }
}
