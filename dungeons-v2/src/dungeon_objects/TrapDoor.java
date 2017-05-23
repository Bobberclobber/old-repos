package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;

import java.awt.*;

/**
 * Created by Josef on 11/04/2014.
 * <p/>
 * A Trap Door Dungeon Object
 * <p/>
 * A Trap Door is represented by a blue
 * square in the middle of the room
 * <p/>
 * A trap brings the player to the next level,
 * placing the player's character in an
 * empty room on the level below the current one
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid
 */
public class TrapDoor extends DungeonObject implements Solid {

    @Override
    public boolean isPlayerControlled() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Rectangle getCollisionBox() {
        return new Rectangle(getXCord(), getYCord(), getWidth(), getHeight());
    }

    @Override
    public void onCollision(DungeonObject otherObject) {
    }
}
