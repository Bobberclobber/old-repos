package se.liu.ida.josfa969.dungeonsv2.help_classes;

import se.liu.ida.josfa969.dungeonsv2.enums.CharacterType;
import se.liu.ida.josfa969.dungeonsv2.enums.EnemyType;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;
import se.liu.ida.josfa969.dungeonsv2.enums.RoomType;

import java.awt.*;
import java.util.AbstractMap;
import java.util.EnumMap;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * A class containing enum maps for mapping Rooms and Dungeon Objects
 * to different colors as well as methods for accessing these maps
 */
public final class DungeonMaps {

    // Room maps
    private static final AbstractMap<RoomType, String> ROOM_TEXT_MAP = new EnumMap<>(RoomType.class);
    private static final AbstractMap<RoomType, Color> ROOM_COLOR_MAP = new EnumMap<>(RoomType.class);

    // PlayerCharacter maps
    private static final AbstractMap<CharacterType, Color> CHARACTER_COLOR_MAP = new EnumMap<>(CharacterType.class);

    // Projectile maps
    private static final AbstractMap<ProjectileType, Color> PROJECTILE_COLOR_MAP = new EnumMap<>(ProjectileType.class);

    // StandardEnemy maps
    private static final AbstractMap<EnemyType, Color> ENEMY_COLOR_MAP = new EnumMap<>(EnemyType.class);

    private DungeonMaps() {
    }

    /**
     * Creates and returns a map linking the different types of rooms to a letter
     *
     * @return An AbstractMap linking a RoomType to a one-letter String
     * @see se.liu.ida.josfa969.dungeonsv2.enums.RoomType
     */
    public static AbstractMap<RoomType, String> getRoomTextMap() {
        ROOM_TEXT_MAP.put(RoomType.EMPTY_ROOM, "E");
        ROOM_TEXT_MAP.put(RoomType.STANDARD_ROOM, "S");
        ROOM_TEXT_MAP.put(RoomType.STANDARD_BOSS_ROOM, "B");
        ROOM_TEXT_MAP.put(RoomType.SHOOTER_ROOM, "H");
        return ROOM_TEXT_MAP;
    }

    /**
     * Creates and returns a map linking the different types of rooms to a color
     *
     * @return An AbstractMap linking a RoomType to a Color
     * @see se.liu.ida.josfa969.dungeonsv2.enums.RoomType
     */
    public static AbstractMap<RoomType, Color> getRoomColorMap() {
        ROOM_COLOR_MAP.put(RoomType.EMPTY_ROOM, Color.WHITE);
        ROOM_COLOR_MAP.put(RoomType.STANDARD_ROOM, Color.WHITE);
        ROOM_COLOR_MAP.put(RoomType.STANDARD_BOSS_ROOM, Color.WHITE);
        ROOM_COLOR_MAP.put(RoomType.SHOOTER_ROOM, Color.WHITE);
        return ROOM_COLOR_MAP;
    }

    /**
     * Creates and returns a map linking the different types of characters to a color
     *
     * @return An AbstractMap linking a CharacterType to a Color
     * @see se.liu.ida.josfa969.dungeonsv2.enums.CharacterType
     */
    public static AbstractMap<CharacterType, Color> getCharacterColorMap() {
        CHARACTER_COLOR_MAP.put(CharacterType.STANDARD_CHARACTER, Color.GREEN);
        return CHARACTER_COLOR_MAP;
    }

    /**
     * Creates and returns a map linking the different types of projectiles to a color
     *
     * @return An AbstractMap linking a ProjectileType to a Color
     * @see se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType
     */
    public static AbstractMap<ProjectileType, Color> getProjectileColorMap() {
        PROJECTILE_COLOR_MAP.put(ProjectileType.STANDARD_PROJECTILE, Color.RED);
        PROJECTILE_COLOR_MAP.put(ProjectileType.ENEMY_PROJECTILE, Color.CYAN);
        return PROJECTILE_COLOR_MAP;
    }

    /**
     * Creates and returns a map linking the different types of enemies to a color
     *
     * @return An AbstractMap linking a EnemyType to a Color
     * @see se.liu.ida.josfa969.dungeonsv2.enums.EnemyType
     */
    public static AbstractMap<EnemyType, Color> getEnemyColorMap() {
        ENEMY_COLOR_MAP.put(EnemyType.STANDARD_ENEMY, Color.BLACK);
        ENEMY_COLOR_MAP.put(EnemyType.STANDARD_BOSS, Color.ORANGE);
        ENEMY_COLOR_MAP.put(EnemyType.SHOOTING_ENEMY, Color.MAGENTA);
        return ENEMY_COLOR_MAP;
    }

}
