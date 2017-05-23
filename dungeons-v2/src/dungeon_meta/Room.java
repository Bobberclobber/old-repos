package se.liu.ida.josfa969.dungeonsv2.dungeon_meta;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.RoomListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door;
import se.liu.ida.josfa969.dungeonsv2.enums.RoomType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;
import se.liu.ida.josfa969.dungeonsv2.help_classes.RoomPopulations;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 2014-02-28.
 * <p/>
 * The meta class which all room sub classes extends
 * <p/>
 * Contains the standard room dimensions as well as
 * fields storing the room type and a list of the room's population
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid
 */
public class Room implements Solid{

    private final RoomType type;
    private PlayerCharacter playerCharacter = null;
    private List<DungeonObject> objects = null;
    private final List<Door> doors;
    private final List<RoomListener> listeners;
    private boolean entered;
    private boolean explored;

    /**
     * Creates a new Room of the given type and inhabits it
     * according to the RoomPopulations class.
     *
     * @param type Which type of Room is to be required
     * @see se.liu.ida.josfa969.dungeonsv2.enums.RoomType
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.RoomPopulations
     */
    public Room(RoomType type) {
        this.type = type;
        switch (type) {
            case EMPTY_ROOM:
                objects = RoomPopulations.emptyRoomPopulation();
                break;
            case STANDARD_ROOM:
                objects = RoomPopulations.standardRoomPopulation();
                break;
            case STANDARD_BOSS_ROOM:
                objects = RoomPopulations.standardBossRoomPopulation();
                break;
            case SHOOTER_ROOM:
                objects = RoomPopulations.shooterRoomPopulation();
                break;
            default:
                objects = RoomPopulations.emptyRoomPopulation();
        }
        doors = new ArrayList<>();
        listeners = new ArrayList<>();
        entered = false;
        explored = false;
    }

    public RoomType getType() {
        return type;
    }

    /**
     * Gets the PlayerCharacter which is currently in the Room
     *
     * @return The PlayerCharacter currently in the Room
     * (null if the PlayerCharacter is in another Room)
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     */
    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    /**
     * Adds the given PlayerCharacter to this Room
     *
     * @param playerCharacter The PlayerCharacter which is to be added
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     */
    public void addCharacter(PlayerCharacter playerCharacter) {
        objects.add(playerCharacter);
        this.playerCharacter = playerCharacter;
    }

    /**
     * Removes the given PlayerCharacter from this Room
     *
     * @param playerCharacter The PlayerCharacter which is to be removed
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     */
    public void removeCharacter(PlayerCharacter playerCharacter) {
        objects.remove(playerCharacter);
        this.playerCharacter = null;
    }

    /**
     * Gets the population of this Room
     * (as seen in the RoomPopulations class)
     *
     * @return A List of DungeonObjects containing
     *         this Room's inhabitants
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     */
    public List<DungeonObject> getObjects() {
        return objects;
    }

    /**
     * Remove the given DungeonObject
     * from this Room's List of inhabitants
     *
     * @param object The DungeonObject which
     *               is to be removed
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     */
    public void removeObject(DungeonObject object) {
        objects.remove(object);
    }

    /**
     * Gets the doors which lead from this room
     * to its neighbours
     *
     * @return An Iterable containing the Doors
     *         leading from this Room to its neighbours
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     */
    public Iterable<Door> getDoors() {
        return doors;
    }

    /**
     * Adds the given door to this Room
     *
     * @param door The Door which is to be added
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     */
    public void addDoor(Door door) {
        doors.add(door);
    }

    public void addRoomListener(RoomListener listener) {
        listeners.add(listener);
    }

    public void removeRoomListener(RoomListener listener) {
        listeners.remove(listener);
    }

    public List<RoomListener> getRoomListeners() {
        return listeners;

    }

    public boolean isEntered() {
        return entered;
    }

    /**
     * This method is called whenever
     * the player enters this Room
     *
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     */
    public void setEntered() {
        this.entered = true;
    }

    public boolean isExplored() {
        return explored;
    }

    /**
     * This method is called for this Room
     * whenever any of its neighbours is entered
     *
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     */
    public void setExplored() {
        this.explored = true;
    }

    public void notifyListeners() {
        for (RoomListener listener : listeners) {
            listener.roomChanged();
        }
    }

    @Override
    public Rectangle getCollisionBox() {
        return new Rectangle(0, 0, Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
    }

    @Override
    public void onCollision(DungeonObject otherObject) {
    }
}
