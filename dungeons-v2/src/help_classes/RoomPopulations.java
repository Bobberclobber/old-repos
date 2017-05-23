package se.liu.ida.josfa969.dungeonsv2.help_classes;

import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.ShootingEnemy;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.StandardBoss;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.StandardEnemy;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.TrapDoor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 2014-03-01.
 * <p/>
 * A class containing methods for creating
 * and accessing different room's populations
 */
public final class RoomPopulations {

    private static List<DungeonObject> population = null;

    private RoomPopulations() {
    }

    /**
     * The Empty Room's population
     * <br>
     * Contains: nothing
     *
     * @return An empty ArrayList
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    public static List<DungeonObject> emptyRoomPopulation() {
        return new ArrayList<>();
    }

    /**
     * The Standard Room's population
     * <br>
     * Contains: 4 Standard Enemies
     *
     * @return An ArrayList containing 4 StandardEnemies
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.StandardEnemy
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    public static List<DungeonObject> standardRoomPopulation() {
        population = new ArrayList<>();
        int leftX = 100;
        int rightX = Constants.ROOM_WIDTH - Constants.ENEMY_STANDARD_WIDTH - 100;
        int upperY = 100;
        int lowerY = Constants.ROOM_HEIGHT - Constants.ENEMY_STANDARD_HEIGHT - 100;
        population.add(new StandardEnemy(leftX, upperY));
        population.add(new StandardEnemy(leftX, lowerY));
        population.add(new StandardEnemy(rightX, upperY));
        population.add(new StandardEnemy(rightX, lowerY));
        return population;
    }

    /**
     * The Standard Boss Room's population
     * <br>
     * Contains: 1 Trap Door, 1 Standard Boss
     *
     * @return An ArrayList containing a TrapDoor and a StandardBoss
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.StandardBoss
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.TrapDoor
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    public static List<DungeonObject> standardBossRoomPopulation() {
        population = new ArrayList<>();
        population.add(new TrapDoor());
        population.add(new StandardBoss());
        return population;
    }

    /**
     * The Shooter Room's population
     * <br>
     * Contains: 5 Shooting Enemies
     *
     * @return An ArrayList containing 5 ShootingEnemies
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.ShootingEnemy
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    public static List<DungeonObject> shooterRoomPopulation() {
        population = new ArrayList<>();
        int leftX = 100;
        int rightX = Constants.ROOM_WIDTH - Constants.ENEMY_STANDARD_WIDTH - 100;
        int middleX = Constants.ROOM_MIDDLE_X - Constants.ENEMY_STANDARD_WIDTH / 2;
        int upperY = 100;
        int lowerY = Constants.ROOM_HEIGHT - Constants.ENEMY_STANDARD_HEIGHT - 100;
        int middleY = Constants.ROOM_MIDDLE_Y - Constants.ENEMY_STANDARD_HEIGHT / 2;
        population.add(new ShootingEnemy(leftX, upperY));
        population.add(new ShootingEnemy(leftX, lowerY));
        population.add(new ShootingEnemy(rightX, upperY));
        population.add(new ShootingEnemy(rightX, lowerY));
        population.add(new ShootingEnemy(middleX, middleY));
        return population;
    }

}
