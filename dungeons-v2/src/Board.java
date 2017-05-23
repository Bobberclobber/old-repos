package se.liu.ida.josfa969.dungeonsv2;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.BoardListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ProjectileHandler;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.RoomListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.enums.RoomType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;
import se.liu.ida.josfa969.dungeonsv2.help_classes.DungeonMaps;

import java.util.*;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * The Board on which the game is played
 * <br>
 * The board is a randomized two-dimensional array of
 * rooms which all contain a certain population
 * depending on which type of room it is
 * <p/>
 * The Board class contains methods for switching room and level
 */
public class Board {

    /**
     * The board's width measured in number of rooms
     *
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    private static final int BOARD_WIDTH = Constants.STANDARD_BOARD_WIDTH;

    /**
     * The board's height measured in number of rooms
     *
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    private static final int BOARD_HEIGHT = Constants.STANDARD_BOARD_HEIGHT;

    private final List<BoardListener> listeners = new ArrayList<>();

    private final Room[][] roomArray = new Room[BOARD_WIDTH][BOARD_HEIGHT];

    /**
     * The room in which the character currently is
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    private Room currentRoom = null;

    private int score = 0;
    private int level = 1;

    /**
     * Standard constructor, creates a randomized board
     */
    public Board() {
        randomizeBoard();
        addAllDoors();
        setCurrentRoom(roomArray[0][0]);
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public Room getRoom(int column, int row) {
        return roomArray[column][row];
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }
    /**
     * Takes a Room as parameter and sets it as the current room
     * <br>
     *
     * @param room The room which is to be set to the new current room
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    void setCurrentRoom(Room room) {
        room.setEntered();
        room.setExplored();
        for (Room room1 : getNeighbours(room)) {
            room1.setExplored();
        }
        currentRoom = room;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void increaseScoreBy(int increase) {
        score += increase;
    }

    /**
     * Switches room depending on which door was entered
     * <br>
     *
     * @param door The door that was entered
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     */
    public void switchRoom(Door door) {
        Room newRoom = door.getTargetRoom();
        PlayerCharacter playerCharacter = currentRoom.getPlayerCharacter();

        // Sets the playerCharacter's position depending on which door was entered
        switch (door.getDirection()) {
            case LEFT:
                enterRoomLeft(playerCharacter);
                break;
            case RIGHT:
                enterRoomRight(playerCharacter);
                break;
            case UP:
                enterRoomUp(playerCharacter);
                break;
            case DOWN:
                enterRoomDown(playerCharacter);
                break;
        }
        switchRoom(newRoom);
    }

    private void enterRoomDown(PlayerCharacter playerCharacter) {
        playerCharacter.setYCord(Constants.CHARACTER_UP_START_Y);
    }

    private void enterRoomUp(PlayerCharacter playerCharacter) {
        playerCharacter.setYCord(Constants.CHARACTER_DOWN_START_Y);
    }

    private void enterRoomRight(PlayerCharacter playerCharacter) {
        playerCharacter.setXCord(Constants.CHARACTER_LEFT_START_X);
    }

    private void enterRoomLeft(PlayerCharacter playerCharacter) {
        playerCharacter.setXCord(Constants.CHARACTER_RIGHT_START_X);
    }

    /**
     * Switches to the given room by removing everything
     * from the old room, adding it to the new room
     * and making the new room the current room
     * <br>
     *
     * @param room The room that is to be switched to
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    void switchRoom(Room room) {
        PlayerCharacter playerCharacter = currentRoom.getPlayerCharacter();
        List<RoomListener> listeners = currentRoom.getRoomListeners();

        // Removes all the existing projectiles
        playerCharacter.clearProjectiles();

        // Clears any "stored" projectiles which might exist when entering a Shooter Room
        if (room.getType() == RoomType.SHOOTER_ROOM) {
            for (DungeonObject shooter : room.getObjects()) {
                ((ProjectileHandler) shooter).clearProjectiles();
            }
        }

        // Remove playerCharacter from the current room and add it to the new room
        currentRoom.removeCharacter(playerCharacter);
        room.addCharacter(playerCharacter);

        // Remove the listeners from the current room and add them to the new room
        // Disabling inspection because changing from for to foreach
        // results in Null Pointer Exception when switching rooms
        for (int i = 0; i < listeners.size(); i++) {
            room.addRoomListener(listeners.get(i));
            currentRoom.removeRoomListener(listeners.get(i));
        }

        // Set the new room as the current room
        setCurrentRoom(room);
        notifyListeners();
    }

    /**
     * Moves down one level by making a new board
     * and switching to the first room on the new board
     *
     * @see se.liu.ida.josfa969.dungeonsv2.DungeonMain
     */
    public void switchLevel() {
        level++;

        // Generate a new board
        clearBoard();
        randomizeBoard();
        addAllDoors();

        // Switch to the initial room of the newly generated board
        switchRoom(roomArray[0][0]);
        notifyListeners();
    }

    public void addBoardListener(BoardListener listener) {
        listeners.add(listener);
    }

    public void notifyListeners() {
        for (BoardListener listener : listeners) {
            listener.boardChanged();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                roomArray[j][i] = null;
            }
        }
    }

    /**
     * Randomizes the board using the following algorithm:
     * <br>
     * 1. Add an empty room to the first slot
     * <br>
     * 2. Go to next slot and check if it is empty and has at least one neighbour
     * <br>
     * 3. If the slot is empty, 8 out of 10 times a random room is added
     * <br>
     * Repeat steps 2 and 3 until the end of the board is
     * reached and the number of rooms is five or more
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    private void randomizeBoard() {
        roomArray[0][0] = new Room(RoomType.EMPTY_ROOM);
        // Makes sure a board with less than five rooms cannot be created
        while (countRooms() < 5) {
            for (int i = 0; i < BOARD_HEIGHT; i++) {
                for (int j = 0; j < BOARD_WIDTH; j++) {
                    if (roomArray[j][i] == null && hasNeighbour(j, i)) {
                        int rand = (int) (Math.random() * (10));
                        if (0 <= rand && rand <= 7) {
                            roomArray[j][i] = new Room(RoomType.randomRoomType());
                        }
                    }
                }
            }
        }
    }

    /**
     * Cycles through all the rooms and adds the correct doors
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     */
    private void addAllDoors() {
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (roomArray[j][i] != null) {
                    addDoors(j, i);
                }
            }
        }
    }

    /**
     * Adds doors to the room at the given coordinates
     * depending on where its neighbours are
     *
     * @param column Which column to examine
     * @param row    Which row to examine
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     */
    // Add all the doors to the room with the given coordinates
    private void addDoors(int column, int row) {
        AbstractMap<Direction, Room> neighbours = getNeighbours(column, row);
        for (Map.Entry<Direction, Room> neighbour : neighbours.entrySet()) {
            roomArray[column][row].addDoor(new Door(neighbour.getValue(), neighbour.getKey()));
        }
    }

    /**
     * Count the number of rooms on the board
     *
     * @return The number of rooms
     */
    private int countRooms() {
        int roomCount = 0;
        for (Room[] rooms : roomArray) {
            for (Room room : rooms) {
                // Inspection suppressed since "room"
                // doesn't have to be used in the if-statement
                // Code maturity issues ignored since no good solution exists for them
                //noinspection VariableNotUsedInsideIf
                if (room != null) {
                    roomCount++;
                }
            }
        }
        return roomCount;
    }

    /**
     * Checks if the room slot at the given coordinates has any neighbour
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return True/False depending on if the slot has any neighbours or not
     */
    //
    private boolean hasNeighbour(int column, int row) {
        // Check for a room to the left
        if (checkRoomLeft(column, row)) return true;

        // Check for a room to the right
        if (checkRoomRight(column, row)) return true;

        // Check for a room upwards
        if (checkRoomUp(column, row)) return true;

        // Check for a room downwards
        if (checkRoomDown(column, row)) return true;

        // Return false if no neighbour exists
        return false;
    }

    /**
     * Check if there is a room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return True/False depending on if there
     * is a room below the room at the given coordinates
     */
    private boolean checkRoomDown(int column, int row) {
        try {
            if (roomArray[column][row + 1] != null) {
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
            System.out.println("Index out of bounds");
        }
        return false;
    }

    /**
     * Check if there is a room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return True/False depending on if there
     * is a room above the room at the given coordinates
     */
    private boolean checkRoomUp(int column, int row) {
        try {
            if (roomArray[column][row - 1] != null) {
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
            System.out.println("Index out of bounds");
        }
        return false;
    }

    /**
     * Check if there is a room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return True/False depending on if there
     * is a room to the right the room at the given coordinates
     */
    private boolean checkRoomRight(int column, int row) {
        try {
            if (roomArray[column + 1][row] != null) {
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
            System.out.println("Index out of bounds");
        }
        return false;
    }

    /**
     * Check if there is a room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return True/False depending on if there
     * is a room to the left the room at the given coordinates
     */
    private boolean checkRoomLeft(int column, int row) {
        try {
            if (roomArray[column - 1][row] != null) {
                return true;
            }
        } catch (IndexOutOfBoundsException ignored) {
            System.out.println("Index out of bounds");
        }
        return false;
    }

    /**
     * Get the room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return The room slot below the room at the given coordinates
     */
    private Room getRoomDown(int column, int row) {
        return roomArray[column][row + 1];
    }

    /**
     * Get the room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return The room slot above the room at the given coordinates
     */
    private Room getRoomUp(int column, int row) {
        return roomArray[column][row - 1];
    }

    /**
     * Get the room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return The room slot to the right of the room at the given coordinates
     */
    private Room getRoomRight(int column, int row) {
        return roomArray[column + 1][row];
    }

    /**
     * Get the room below the room at the given coordinates
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return The room slot to the left of the room at the given coordinates
     */
    private Room getRoomLeft(int column, int row) {
        return roomArray[column - 1][row];
    }

    /**
     * Gets a map containing the given room's neighbours
     * mapped to whatever direction they are relative to the given room
     *
     * @param column Which column to examine
     * @param row Which row to examine
     * @return An AbstractMap mapping each direction
     * to whatever is in the corresponding room slot
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     */
    private AbstractMap<Direction, Room> getNeighbours(int column, int row) {
        AbstractMap<Direction, Room> neighbours = new EnumMap<>(Direction.class);

        // Check if there is a room to the left
        if (checkRoomLeft(column, row)) {
            neighbours.put(Direction.LEFT, getRoomLeft(column, row));
        }

        // Check if there is a room to the right
        if (checkRoomRight(column, row)) {
            neighbours.put(Direction.RIGHT, getRoomRight(column, row));
        }

        // Check if there is a room above
        if (checkRoomUp(column, row)) {
            neighbours.put(Direction.UP, getRoomUp(column, row));
        }

        // Check if there is a room below
        if (checkRoomDown(column, row)) {
            neighbours.put(Direction.DOWN, getRoomDown(column, row));
        }

        return neighbours;
    }

    /**
     * Uses the previously defined getNeighbours-method to
     * get the neighbours of a room without having to give the coordinates
     *
     * @param room The room who's neighbours the method is getting
     * @return A List of the given room's neighbours
     */
    private Iterable<Room> getNeighbours(Room room) {
        int column = 0;
        int row = 0;
        List<Room> neighbours = new ArrayList<>();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (room.equals(roomArray[j][i])) {
                    column = j;
                    row = i;
                }
            }
        }
        for (Room room1 : getNeighbours(column, row).values()) {
            neighbours.add(room1);
        }
        return neighbours;
    }

    /**
     * Allows the board to be printed. Each room is represented
     * as a letter according to the ROOM_TEXT_MAP in the DungeonMaps class.
     * Additionally, an empty slot is represented as '-' and the current room is a 'C'
     *
     * @return A string where all the rooms are represented as letters
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.DungeonMaps
     */
    @Override
    public String toString() {
        AbstractMap<RoomType, String> textMap = DungeonMaps.getRoomTextMap();
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                Room current = roomArray[j][i];
                if (currentRoom.equals(current)) {
                    boardString.append("C");
                    // Inspection suppressed since "current"
                    // doesn't have to be used in the if-statement
                    // Code maturity issues ignored since no good solution exists for them
                } else //noinspection VariableNotUsedInsideIf
                    if (current == null) {
                        boardString.append("-");
                    } else {
                        boardString.append(textMap.get(roomArray[j][i].getType()));
                    }
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}
