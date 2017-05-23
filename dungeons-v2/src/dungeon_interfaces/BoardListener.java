package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-02.
 *
 * A listener interface which is called whenever any aspect
 * of the game, which requires board to be updated, occurs
 */
public interface BoardListener {
    public void boardChanged();
}
