package se.wgco.jgf.adventura_ultima.character_classes.class_interfaces;

import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.character_classes.ClassType;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     An interface which all label interfaces extend and which
 *     defines methods used to initialize a player character
 * </p>
 */
public interface CharacterClass {
    void initBaseStats(PlayerCharacter character);
    ClassType getClassType();
}
