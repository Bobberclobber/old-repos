package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 23/04/2014.
 * <p/>
 * An interface used to define how any action
 * performed by the character should be modelled
 */
public interface CharacterAction {
    void perform();
    int getUpdateRate();
}
