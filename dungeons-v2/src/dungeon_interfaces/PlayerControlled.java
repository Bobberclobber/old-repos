package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-18.
 * <p/>
 * An interface which bundles together all the interfaces
 * required by Dungeon Objects which are controlled by the player
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Mobile
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ControlledMovement
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ControlledShooting
 */
public interface PlayerControlled extends Mobile, Solid, ControlledMovement, ControlledShooting {
}
