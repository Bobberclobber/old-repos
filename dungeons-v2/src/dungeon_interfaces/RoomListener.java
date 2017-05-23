package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-04.
 *
 * A listener interface which is called whenever any aspect
 * of the game, which requires the current room to be updated, occurs
 */
public interface RoomListener {
    public void roomChanged();
}
