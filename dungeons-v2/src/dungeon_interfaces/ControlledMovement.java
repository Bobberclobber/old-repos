package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

import se.liu.ida.josfa969.dungeonsv2.enums.Direction;

/**
 * Created by Josef on 2014-03-18.
 * <p/>
 * An interface defining the methods used by a
 * mobile Dungeon Object which is controlled by the player
 */
public interface ControlledMovement {
    public void move(Direction direction); //Warning is false positive
    public void moveLeft(); //Warning is false positive
    public void moveRight(); //Warning is false positive
    public void moveUp(); //Warning is false positive
    public void moveDown(); //Warning is false positive
}
