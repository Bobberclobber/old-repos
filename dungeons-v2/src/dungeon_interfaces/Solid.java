package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;

import java.awt.*;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * An interface defining the methods used by
 * Dungeon Objects which are able to collide
 */
public interface Solid {
    public Rectangle getCollisionBox();
    public void onCollision(DungeonObject otherObject);
}
