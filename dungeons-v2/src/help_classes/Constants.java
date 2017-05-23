package se.liu.ida.josfa969.dungeonsv2.help_classes;

import se.liu.ida.josfa969.dungeonsv2.enums.DungeonButtonType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * A class containing constants used by different classes
 */
public final class Constants {
    private Constants() {
    }

    /*
     * Universal game constants
     */
    public final static String TITLE = "Dungeons v. 2.0";
    public final static int GAME_UPDATE_RATE = 10;

    /*
     * Board dimensions
     */
    public final static int STANDARD_BOARD_WIDTH = 9;
    public final static int STANDARD_BOARD_HEIGHT = 5;

    /*
     * Window dimensions
     */
    public final static int WINDOW_WIDTH = 1000;
    public final static int WINDOW_HEIGHT = 800;

    /*
     * Start menu dimensions
     */
    public final static int START_MENU_WIDTH = 300;
    public final static int START_MENU_HEIGHT = 400;

    /*
     * Status Bar dimensions
     */
    public final static int STATUS_BAR_WIDTH = 900;
    public final static int STATUS_BAR_HEIGHT = 100;
    public final static int MAP_ROOM_HEIGHT = STATUS_BAR_HEIGHT / STANDARD_BOARD_HEIGHT - 5;
    public final static int MAP_ROOM_WIDTH = 2 * MAP_ROOM_HEIGHT;
    public static final int HEALTH_BAR_HEIGHT = 25;

    /*
     * Status Bar positioning constants
     */
    public final static int STATUS_BAR_X_CORD = (WINDOW_WIDTH - STATUS_BAR_WIDTH) / 2;
    public final static int STATUS_BAR_Y_CORD = 50;
    public final static int HEALTH_PERCENTAGE_X_CORD = 115;
    public final static int HEALTH_PERCENTAGE_Y_CORD = 17;
    public final static int SCORE_TEXT_X_CORD = STATUS_BAR_WIDTH / 2;
    public final static int LEVEL_TEXT_X_CORD = SCORE_TEXT_X_CORD - 80;
    public final static int STATUS_TEXT_Y_CORD = 15;

    /*
     * Dungeon Button constants
     */
    public final static int DUNGEON_BUTTON_WIDTH = 150;
    public final static int DUNGEON_BUTTON_HEIGHT = 60;
    public final static int DUNGEON_BUTTON_FONT_SIZE = 15;

    /*
     * Dungeon Button image sources
     */
    // Warning suppressed since I after extensive searching
    // could find no simple way creating a wholly relative
    // file path based on the file's name. Additionally, the
    // file paths given below are at least somewhat relative
    // since file path modifications are accepted as long as
    // the path src/resources/img/img_name.jpg remains intact
    @SuppressWarnings("HardcodedFileSeparator")
    private final static String[] START_BUTTON_IMAGES = {
            "src/resources/img/start_button.jpg",
            "src/resources/img/start_button_hover.jpg",
            "src/resources/img/start_button_click.jpg"
    };

    // Warning suppressed since I after extensive searching
    // could find no simple way creating a wholly relative
    // file path based on the file's name. Additionally, the
    // file paths given below are at least somewhat relative
    // since file path modifications are accepted as long as
    // the path src/resources/img/img_name.jpg remains intact
    @SuppressWarnings("HardcodedFileSeparator")
    private final static String[] EXIT_BUTTON_IMAGES = {
            "src/resources/img/exit_button.jpg",
            "src/resources/img/exit_button_hover.jpg",
            "src/resources/img/exit_button_click.jpg"
    };

    // Warning suppressed since I after extensive searching
    // could find no simple way creating a wholly relative
    // file path based on the file's name. Additionally, the
    // file paths given below are at least somewhat relative
    // since file path modifications are accepted as long as
    // the path src/resources/img/img_name.jpg remains intact
    @SuppressWarnings("HardcodedFileSeparator")
    private final static String[] RESTART_BUTTON_IMAGES = {
            "src/resources/img/restart_button.jpg",
            "src/resources/img/restart_button_hover.jpg",
            "src/resources/img/restart_button_click.jpg"
    };

    /*
     * Dungeon Button images
     */
    public static Image[] getImageArray(DungeonButtonType buttonType) {
        String[] sourceArray;
        // Gets the correct Array List of Images depending
        // on which Button type was passed in as a parameter
        switch (buttonType) {
            case START_BUTTON:
                sourceArray = START_BUTTON_IMAGES;
                break;
            case EXIT_BUTTON:
                sourceArray = EXIT_BUTTON_IMAGES;
                break;
            case RESTART_BUTTON:
                sourceArray = RESTART_BUTTON_IMAGES;
                break;
            default:
                sourceArray = new String[0];
        }
        Image[] imageArray = new Image[sourceArray.length];
        for (int i = 0; i < sourceArray.length; i++) {
            imageArray[i] = new ImageIcon(sourceArray[i]).getImage();
        }
        return imageArray;
    }

    /*
     * Menu Buttons positioning constants
     */
    public final static int MENU_START_BUTTON_X_CORD = (START_MENU_WIDTH - DUNGEON_BUTTON_WIDTH) / 2;
    public final static int MENU_START_BUTTON_Y_CORD = ((START_MENU_HEIGHT - DUNGEON_BUTTON_HEIGHT) / 2) - DUNGEON_BUTTON_HEIGHT - 10;

    public final static int MENU_EXIT_BUTTON_X_CORD = (START_MENU_WIDTH - DUNGEON_BUTTON_WIDTH) / 2;
    public final static int MENU_EXIT_BUTTON_Y_CORD = ((START_MENU_HEIGHT - DUNGEON_BUTTON_HEIGHT) / 2) + 10;

    /*
     * Restart Button positioning constants
     */
    public final static int RESTART_BUTTON_X_CORD = (WINDOW_WIDTH - DUNGEON_BUTTON_WIDTH) / 2;
    public final static int RESTART_BUTTON_Y_CORD = ((WINDOW_HEIGHT - DUNGEON_BUTTON_HEIGHT) / 2) - DUNGEON_BUTTON_HEIGHT - 10;

    /*
     * Exit Button positioning constants
     */
    public final static int EXIT_BUTTON_X_CORD = (WINDOW_WIDTH - DUNGEON_BUTTON_WIDTH) / 2;
    public final static int EXIT_BUTTON_Y_CORD = ((WINDOW_HEIGHT - DUNGEON_BUTTON_HEIGHT) / 2) + 10;

    /*
     * Room dimensions
     */
    public final static int ROOM_WIDTH = 900;
    public final static int ROOM_HEIGHT = 500;

    /*
     * Room positioning constants
     */
    public final static int ROOM_MIDDLE_X = ROOM_WIDTH / 2;
    public final static int ROOM_MIDDLE_Y = ROOM_HEIGHT / 2;
    public final static int ROOM_X_CORD = (WINDOW_WIDTH - ROOM_WIDTH) / 2;
    public final static int ROOM_Y_CORD = (WINDOW_HEIGHT - ROOM_HEIGHT) / 2;

    /*
     * Door dimensions
     */
    public final static int DOOR_SHORT_SIDE = 20;
    public final static int DOOR_LONG_SIDE = 80;
    public final static int TRAP_DOOR_SIDE = 60;

    /*
     * Door positioning constants
     */
    public final static int UPPER_DOOR_X_CORD = (ROOM_WIDTH - DOOR_LONG_SIDE) / 2;
    public final static int UPPER_DOOR_Y_CORD = 0;

    public final static int LOWER_DOOR_X_CORD = (ROOM_WIDTH - DOOR_LONG_SIDE) / 2;
    public final static int LOWER_DOOR_Y_CORD = ROOM_HEIGHT - DOOR_SHORT_SIDE;

    public final static int RIGHT_DOOR_X_CORD = ROOM_WIDTH - DOOR_SHORT_SIDE;
    public final static int RIGHT_DOOR_Y_CORD = (ROOM_HEIGHT - DOOR_LONG_SIDE) / 2;

    public final static int LEFT_DOOR_X_CORD = 0;
    public final static int LEFT_DOOR_Y_CORD = (ROOM_HEIGHT - DOOR_LONG_SIDE) / 2;

    public final static int TRAP_DOOR_X_CORD = (ROOM_WIDTH - TRAP_DOOR_SIDE) / 2;
    public final static int TRAP_DOOR_Y_CORD = (ROOM_HEIGHT - TRAP_DOOR_SIDE) / 2;

    /*
     * Character constants
     */
    public final static int CHARACTER_WIDTH = 50;
    public final static int CHARACTER_HEIGHT = 50;
    public final static int CHARACTER_STANDARD_SPEED = 5;
    public final static int CHARACTER_STANDARD_HEALTH = 100;

    public final static int CHARACTER_STANDARD_MOVEMENT_RATE = 20;
    public final static int CHARACTER_STANDARD_SHOOTING_RATE = 1000;

    /*
     * Character Positioning constants
     */
    public final static int CHARACTER_START_X = ROOM_MIDDLE_X;
    public final static int CHARACTER_START_Y = ROOM_MIDDLE_Y;

    public final static int CHARACTER_LEFT_START_X = DOOR_SHORT_SIDE + 10;
    public final static int CHARACTER_RIGHT_START_X = ROOM_WIDTH - CHARACTER_WIDTH - 30;
    public final static int CHARACTER_UP_START_Y = DOOR_SHORT_SIDE + 10;
    public final static int CHARACTER_DOWN_START_Y = ROOM_HEIGHT - CHARACTER_HEIGHT - 30;

    /*
     * Projectile constants
     */
    public final static int PROJECTILE_WIDTH = 10;
    public final static int PROJECTILE_HEIGHT = 10;
    public final static int PROJECTILE_STANDARD_SPEED = 4;
    public final static int ENEMY_PROJECTILE_STANDARD_SPEED = 3;

    /*
     * Standard Enemy constants
     */
    public final static int ENEMY_STANDARD_WIDTH = 50;
    public final static int ENEMY_STANDARD_HEIGHT = 65;
    public final static int ENEMY_STANDARD_SPEED = 3;
    public final static int ENEMY_STANDARD_HEALTH = 8;

    /*
     * Standard Boss constants
     */
    public final static int BOSS_STANDARD_WIDTH = 150;
    public final static int BOSS_STANDARD_HEIGHT = 180;
    public final static int BOSS_STANDARD_SPEED = 1;
    public final static int BOSS_STANDARD_HEALTH = 40;

    /*
     * Shooting Enemy constants
     */
    public final static int SHOOTING_ENEMY_FIRE_RATE = 2000;
}
