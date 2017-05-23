package se.liu.ida.josfa969.dungeonsv2.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Josef on 2014-03-01.
 * <p/>
 * Enum class containing the different types of rooms
 * as well as a method for getting a random room
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
 */
public enum RoomType {

    EMPTY_ROOM, STANDARD_ROOM, STANDARD_BOSS_ROOM, SHOOTER_ROOM;

    private static final List<RoomType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static RoomType randomRoomType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
