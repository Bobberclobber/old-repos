package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-18.
 * <p/>
 * An interface which bundles together all the interfaces
 * required by Dungeon Objects which are not controlled by the player
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Mobile
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid
 */
public interface NonPlayerControlled extends Mobile, Solid {
    public void move();
    // Warning suppressed because no good solution exists
    // Method has to exist in order for friendly NPC's to exist
    @SuppressWarnings("SuppressionAnnotation")
    public boolean isEnemy();
}
