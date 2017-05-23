package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

import se.liu.ida.josfa969.dungeonsv2.enums.Direction;

/**
 * Created by Josef on 2014-03-18.
 * <p/>
 * An interface defining all the methods used by a
 * shooting Dungeon Object which is controlled by the player
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ProjectileHandler
 */
interface ControlledShooting extends ProjectileHandler {
    public void shoot(Direction direction); //Warning is false positive
    public void shootLeft(); //Warning is false positive
    public void shootRight(); //Warning is false positive
    public void shootUp(); //Warning is false positive
    public void shootDown(); //Warning is false positive
}
