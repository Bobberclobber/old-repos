package se.liu.ida.josfa969.dungeonsv2.help_classes;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;

/**
 * Created by Josef on 22/04/2014.
 * <p/>
 * This class is used to handle collisions between DungeonObjects.
 * Every time a collision between two DungeonObjects occur, an
 * instance of this class is created and the method
 * executeCollisionHandling is called
 */
public class CollisionHandler {
    private Solid object;

    public CollisionHandler(Solid object) {
        this.object = object;
    }

    public void executeCollisionHandling(DungeonObject otherObject) {
        object.onCollision(otherObject);
    }
}
