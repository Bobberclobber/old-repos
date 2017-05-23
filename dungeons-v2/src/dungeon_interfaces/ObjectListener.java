package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-02.
 * <p/>
 * A listener interface which is called whenever
 * any aspect of the game, which requires a
 * certain Dungeon Object to be updated, occurs
 */
public interface ObjectListener {
    public void objectChanged();
}
