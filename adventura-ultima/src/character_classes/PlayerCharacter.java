package se.wgco.jgf.adventura_ultima.character_classes;

import se.wgco.jgf.adventura_ultima.character_classes.class_interfaces.CharacterClass;
import se.wgco.jgf.adventura_ultima.navigation.Direction;

/**
 * Created by Josef on 26/04/2014.
 * <p>
 *     An abstract class which all classes inherit
 * </p>
 */
public class PlayerCharacter {
    private int xPosition = 0;
    private int yPosition = 0;
    private int strength = 0;
    private int dexterity = 0;
    private int intellect = 0;
    private ClassType classType = null;
    private CharacterClass characterClass = null;

    public PlayerCharacter(int xPosition, int yPosition, CharacterClass characterClass) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.characterClass = characterClass;
        this.classType = characterClass.getClassType();
        characterClass.initBaseStats(this);
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void move(Direction direction) {
        switch (direction) {
            case NORTH:
                yPosition -= 1;
                break;
            case SOUTH:
                yPosition += 1;
                break;
            case EAST:
                xPosition += 1;
                break;
            case WEST:
                xPosition -= 1;
                break;
        }
    }
}
