package se.scramble_studios.nehro.simple_platform_game.java.core;

import org.lwjgl.glfw.GLFWvidmode;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

/**
 * Created by Josef on 22/02/2015.
 * <p>
 *     A collection of constants
 * </p>
 */
public class Const {
    // Get resolution of primary monitor
    private static final ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

    public static final String TITLE = "Simple Platform";

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;
    public static final int WIN_HALF_WIDTH = WINDOW_WIDTH / 2;
    public static final int WIN_HALF_HEIGHT = WINDOW_HEIGHT / 2;

    // Calculate the middle of the monitor
    public static final int MONITOR_MID_WIDTH = (GLFWvidmode.width(vidmode) - WINDOW_WIDTH) / 2;
    public static final int MONITOR_MID_HEIGHT = (GLFWvidmode.height(vidmode) - WINDOW_HEIGHT) / 2;

    // Game element dimensions
    public static final int BLOCK_WIDTH = 40;
    public static final int BLOCK_HEIGHT = 40;
    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 40;

    public static final int WINDOW_BLOCK_WIDTH = WINDOW_WIDTH / BLOCK_WIDTH;
    public static final int WINDOW_BLOCK_HEIGHT = WINDOW_HEIGHT / BLOCK_HEIGHT;

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 60;

    // Strings used as qualifiers for states
    public static final String GAME_STATE = "game";

    // Shader paths
    public static final String GLSL_VERTEX_PATH = "res/glsl/default_vertex.glsl";
    public static final String GLSL_FRAGMENT_PATH = "res/glsl/default_fragment.glsl";
    public static final String GLSL_LEGACY_VERTEX_PATH = "res/glsl/legacy_vertex.glsl";
    public static final String GLSL_LEGACY_FRAGMENT_PATH = "res/glsl/legacy_fragment.glsl";

    // Font paths
    public static final String INCONSOLATA = "res/font/Inconsolata.otf";

    /*
     * GLSL Variables
     */

    // Uniform variables
    public static final String TEX_IMAGE = "texImage";
    public static final String MODEL = "model";
    public static final String VIEW = "view";
    public static final String PROJECTION = "projection";

    // Data variables
    public static final String FRAG_COLOR = "fragColor";

    // Pointers
    public static final String POSITION = "position";
    public static final String COLOR = "color";
    public static final String TEXCOORD = "texcoord";

    /*
     * Map parsing variables
     */

    // The root folder of all maps
    public static final String MAP_FILE_ROOT = "res/map/";

    // Constants used to parse the header
    public static final String PMAP_NAME = "name";
    public static final String PMAP_WIDTH = "width";
    public static final String PMAP_HEIGHT = "height";
    public static final String PMAP_STARTX = "startx";
    public static final String PMAP_STARTY = "starty";
    public static final char END_OF_HEADER = ';';

    // Characters present in the map
    public static final char SKY_CHAR = ' ';
    public static final char BRICK_CHAR = 'X';
    public static final char SPIKE_CHAR = 'W';

    // Values used by the collision detection
    public static final int NO_COLLISION = 0;
    public static final int COLLISION = 1;
    public static final int SPIKE_COLLISION = 2;
    public static final int GOAL_COLLISION = 3;

    // Player stats
    public static final float PLAYER_BASE_SPEED = 250f;
    public static final float PLAYER_BASE_JUMP_HEIGHT = 3f;

    // Paths for images
    public static final String PLAYER_IMG = "res/img/player_img.png";
    public static final String BLOCK_IMG = "res/img/block_img.png";
}
