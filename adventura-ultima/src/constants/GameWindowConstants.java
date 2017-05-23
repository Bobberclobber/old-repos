package se.wgco.jgf.adventura_ultima.constants;

/**
 * Created by Josef on 28/04/2014.
 * <p>
 *     A class defining all constants used by the window.
 * </p>
 */
public class GameWindowConstants {
    public static int MARGIN = 25;

    public static int WINDOW_WIDTH = 1200;
    public static int WINDOW_HEIGHT = 825;

    public static int STATUS_BAR_WIDTH = WINDOW_WIDTH - 2 * MARGIN;
    public static int STATUS_BAR_HEIGHT = 200;

    public static int PLAY_FIELD_WIDTH = STATUS_BAR_WIDTH;
    public static int PLAY_FIELD_HEIGHT = WINDOW_HEIGHT - STATUS_BAR_HEIGHT - (3 * MARGIN);

    public static int PLAY_FIELD_X = MARGIN;
    public static int PLAY_FIELD_Y = MARGIN;

    public static int STATUS_BAR_X = MARGIN;
    public static int STATUS_BAR_Y = PLAY_FIELD_Y + PLAY_FIELD_HEIGHT + MARGIN;
}
