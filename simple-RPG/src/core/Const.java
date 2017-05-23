package core;

/**
 * Created by Josef on 04/06/2015.
 * <p>
 *     Stores constants
 * </p>
 */
public class Const {
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

    // Shader paths
    public static final String GLSL_VERTEX_PATH = "res/glsl/default_vertex.glsl";
    public static final String GLSL_FRAGMENT_PATH = "res/glsl/default_fragment.glsl";
    public static final String GLSL_LEGACY_VERTEX_PATH = "res/glsl/legacy_vertex.glsl";
    public static final String GLSL_LEGACY_FRAGMENT_PATH = "res/glsl/legacy_fragment.glsl";

    // Font paths
    public static final String INCONSOLATA = "res/font/Inconsolata.otf";
}
