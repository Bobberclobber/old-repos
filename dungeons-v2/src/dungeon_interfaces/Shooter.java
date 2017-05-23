package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-17.
 * <p/>
 * An interface defining the methods used by a shooting
 * Dungeon Object which is not controlled by the player
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ProjectileHandler
 */
public interface Shooter extends ProjectileHandler {
    public void shoot(); //Warning is false positive
}
