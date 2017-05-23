package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;

import java.awt.*;

/**
 * Created by Josef on 2014-03-25.
 * <p/>
 * A Door Dungeon Object
 * <p/>
 * A Door is represented by a blue rectangle
 * in the middle of one of a room's four walls
 * <p/>
 * When the player enters a door the player's
 * character is moved to the room to which the door leads
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid
 */
public class Door extends DungeonObject implements Solid {
    private final Room targetRoom;

    public Door(Room targetRoom, Direction direction) {
        super(direction);
        this.targetRoom = targetRoom;
    }

    public Room getTargetRoom() {
        return targetRoom;
    }

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
