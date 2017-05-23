package se.scramble_studios.nehro.simple_platform_game.java.graphics;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import se.scramble_studios.nehro.simple_platform_game.java.math.Matrix4f;
import se.scramble_studios.nehro.simple_platform_game.java.text.Font;
import se.scramble_studios.nehro.simple_platform_game.java.core.Const;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STREAM_DRAW;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

/**
 * Created by Josef on 28/03/2015.
 * <p>
 *     This class is performing the rendering process
 * </p>
 */
public class Renderer {
    private VertexArrayObject vao;
    private VertexBufferObject vbo;
    private Shader vertexShader;
    private Shader fragmentShader;
    private ShaderProgram program;
    private Font font;
    private Font debugFont;

    private boolean defaultContext;

    private FloatBuffer vertices;
    private int numVertices;
    private boolean drawing;

    // Initializes the renderer
    public void init(boolean defaultContext) {
        this.defaultContext = defaultContext;

        if (defaultContext) {
            // Generate Vertex Array Object
            vao = new VertexArrayObject();
            vao.bind();
        } else
            vao = null;

        // Generate Vertex Buffer Object
        vbo = new VertexBufferObject();
        vbo.bind(GL_ARRAY_BUFFER);

        // Create FloatBuffer
        vertices = BufferUtils.createFloatBuffer(4096);

        // Upload null data to allocate storage for the VBO
        long size = vertices.capacity() * Float.BYTES;
        vbo.uploadData(GL_ARRAY_BUFFER, size, GL_STREAM_DRAW);

        // Initialise variables
        numVertices = 0;
        drawing = false;

        // Load shaders
        if (defaultContext) {
            vertexShader = Shader.loadShader(GL_VERTEX_SHADER, Const.GLSL_VERTEX_PATH);
            fragmentShader = Shader.loadShader(GL_FRAGMENT_SHADER, Const.GLSL_FRAGMENT_PATH);
        } else {
            vertexShader = Shader.loadShader(GL_VERTEX_SHADER, Const.GLSL_LEGACY_VERTEX_PATH);
            fragmentShader = Shader.loadShader(GL_FRAGMENT_SHADER, Const.GLSL_LEGACY_FRAGMENT_PATH);
        }

        // Create shader program
        program = new ShaderProgram();
        program.attachShader(vertexShader);
        program.attachShader(fragmentShader);
        if (defaultContext)
            program.bindFragmentDataLocation(0, Const.FRAG_COLOR);
        program.link();
        program.use();

        // Get width and height of framebuffer
        long window = GLFW.glfwGetCurrentContext();
        IntBuffer widthBuffer = BufferUtils.createIntBuffer(1);
        IntBuffer heightBuffer = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetFramebufferSize(window, widthBuffer, heightBuffer);
        int width = widthBuffer.get();
        int height = heightBuffer.get();

        // Specify Vertex Pointers
        specifyVertexAttributes();

        // Set texture uniform
        int uniTex = program.getUniformLocation(Const.TEX_IMAGE);
        program.setUniform(uniTex, 0);

        // Set model matrix to identity matrix
        Matrix4f model = new Matrix4f();
        int uniModel = program.getUniformLocation(Const.MODEL);
        program.setUniform(uniModel, model);

        // Set model matrix to identity matrix
        Matrix4f view = new Matrix4f();
        int uniView = program.getUniformLocation(Const.VIEW);
        program.setUniform(uniView, view);

        // Set projection matrix to an orthographic projection
        Matrix4f projection = Matrix4f.orthographic(0f, width, 0f, height, -1f, 1f);
        int uniProjection = program.getUniformLocation(Const.PROJECTION);
        program.setUniform(uniProjection, projection);

        // Enable blending
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Create fonts
        try {
            font = new Font(new FileInputStream(Const.INCONSOLATA), 16);
        } catch (FontFormatException | IOException e) {
            Logger.getLogger(Renderer.class.getName()).log(Level.CONFIG, null, e);
            font = new Font();
        }
        debugFont = new Font(12, false);
    }

    // Clears the drawing area
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    // Begin rendering
    public void begin() {
        if (drawing)
            throw new IllegalStateException("Renderer is already drawing!");
        drawing = true;
        numVertices = 0;
    }

    // End rendering
    public void end() {
        if (!drawing)
            throw new IllegalStateException("Renderer isn't drawing!");
        drawing = false;
        flush();
    }

    // Flushes the data to the GPU to let it get rendered
    private void flush() {
        if (numVertices > 0) {
            vertices.flip();

            if (vao != null)
                vao.bind();
            else {
                vbo.bind(GL_ARRAY_BUFFER);
                specifyVertexAttributes();
            }
            program.use();

            // Upload the new vertex data
            vbo.bind(GL_ARRAY_BUFFER);
            vbo.uploadSubData(GL_ARRAY_BUFFER, 0, vertices);

            // Draw batch
            glDrawArrays(GL_TRIANGLES, 0, numVertices);

            // Clear vertex data for next batch
            vertices.clear();
            numVertices = 0;
        }
    }

    // Calculates total width of a text
    public int getTextWidth(CharSequence text) {
        return font.getWidth(text);
    }

    // Calculates total height of a text
    public int getTextHeight(CharSequence text) {
        return font.getHeight(text);
    }

    // Calculates total width of a debug text
    public int getDebugTextWidth(CharSequence text) {
        return debugFont.getWidth(text);
    }

    // Calculates total height of a debug text
    public int getDebugTextHeight(CharSequence text) {
        return debugFont.getHeight(text);
    }

    // Draw text at specified position
    public void drawText(CharSequence text, float x, float y) {
        font.drawText(this, text, x, y);
    }

    // Draw debug text at specified position
    public void drawDebugText(CharSequence text, float x, float y) {
        debugFont.drawText(this, text, x, y);
    }

    // Draw text at specified position with specified color
    public void drawText(CharSequence text, float x, float y, Color c) {
        font.drawText(this, text, x, y, c);
    }

    // Draw debug text at specified position with specified color
    public void drawDebugText(CharSequence text, float x, float y, Color c) {
        debugFont.drawText(this, text, x, y, c);
    }

    // Draws the currently bound texture at specified coordinates
    public void drawTexture(Texture texture, float x, float y) {
        drawTexture(texture, x, y, Color.WHITE);
    }

    // Draws the currently bound texture on specified coordinates and with specified color
    private void drawTexture(Texture texture, float x, float y, Color c) {
        /* Vertex positions */
        float x1 = x;
        float y1 = y;
        float x2 = x1 + texture.getWidth();
        float y2 = y1 + texture.getHeight();

        /* Texture coordinates */
        float s1 = 0f;
        float t1 = 0f;
        float s2 = 1f;
        float t2 = 1f;

        drawTextureRegion(x1, y1, x2, y2, s1, t1, s2, t2, c);
    }

    /**
     * Draws a texture region with the currently bound texture on specified
     * coordinates.
     *
     * @param texture Used for getting width and height of the texture
     * @param x X position of the texture
     * @param y Y position of the texture
     * @param regX X position of the texture region
     * @param regY Y position of the texture region
     * @param regWidth Width of the texture region
     * @param regHeight Height of the texture region
     */
    public void drawTextureRegion(Texture texture, float x, float y, float regX, float regY, float regWidth, float regHeight) {
        drawTextureRegion(texture, x, y, regX, regY, regWidth, regHeight, Color.WHITE);
    }

    /**
     * Draws a texture region with the currently bound texture on specified
     * coordinates.
     *
     * @param texture Used for getting width and height of the texture
     * @param x X position of the texture
     * @param y Y position of the texture
     * @param regX X position of the texture region
     * @param regY Y position of the texture region
     * @param regWidth Width of the texture region
     * @param regHeight Height of the texture region
     * @param c The color to use
     */
    public void drawTextureRegion(Texture texture, float x, float y, float regX, float regY, float regWidth, float regHeight, Color c) {
        /* Vertex positions */
        float x1 = x;
        float y1 = y;
        float x2 = x + regWidth;
        float y2 = y + regHeight;

        /* Texture coordinates */
        float s1 = regX / texture.getWidth();
        float t1 = regY / texture.getHeight();
        float s2 = (regX + regWidth) / texture.getWidth();
        float t2 = (regY + regHeight) / texture.getHeight();

        drawTextureRegion(x1, y1, x2, y2, s1, t1, s2, t2, c);
    }

    /**
     * Draws a texture region with the currently bound texture on specified
     * coordinates.
     *
     * @param x1 Bottom left x position
     * @param y1 Bottom left y position
     * @param x2 Top right x position
     * @param y2 Top right y position
     * @param s1 Bottom left s coordinate
     * @param t1 Bottom left t coordinate
     * @param s2 Top right s coordinate
     * @param t2 Top right t coordinate
     */
    public void drawTextureRegion(float x1, float y1, float x2, float y2, float s1, float t1, float s2, float t2) {
        drawTextureRegion(x1, y1, x2, y2, s1, t1, s2, t2, Color.WHITE);
    }

    /**
     * Draws a texture region with the currently bound texture on specified
     * coordinates.
     *
     * @param x1 Bottom left x position
     * @param y1 Bottom left y position
     * @param x2 Top right x position
     * @param y2 Top right y position
     * @param s1 Bottom left s coordinate
     * @param t1 Bottom left t coordinate
     * @param s2 Top right s coordinate
     * @param t2 Top right t coordinate
     * @param c The color to use
     */
    public void drawTextureRegion(float x1, float y1, float x2, float y2, float s1, float t1, float s2, float t2, Color c) {
        if (vertices.remaining() < 7 * 6) {
            /* We need more space in the buffer, so flush it */
            flush();
        }

        float r = c.getRed() / 255f;
        float g = c.getGreen() / 255f;
        float b = c.getBlue() / 255f;

        vertices.put(x1).put(y1).put(r).put(g).put(b).put(s1).put(t1);
        vertices.put(x1).put(y2).put(r).put(g).put(b).put(s1).put(t2);
        vertices.put(x2).put(y2).put(r).put(g).put(b).put(s2).put(t2);

        vertices.put(x1).put(y1).put(r).put(g).put(b).put(s1).put(t1);
        vertices.put(x2).put(y2).put(r).put(g).put(b).put(s2).put(t2);
        vertices.put(x2).put(y1).put(r).put(g).put(b).put(s2).put(t1);

        numVertices += 6;
    }

    // Dispose renderer and clean up its used data
    public void dispose() {
        if (vao != null)
            vao.delete();
        vbo.delete();
        vertexShader.delete();
        fragmentShader.delete();
        program.delete();
        font.dispose();
    }

    // Shows if the OpenGL context supports version 3.2
    public boolean hasDefaultContext() {
        return defaultContext;
    }

    // Specifies the vertex pointers
    private void specifyVertexAttributes() {
        // Specify vertex pointer
        int posAttrib = program.getAttributeLocation(Const.POSITION);
        program.enableVertexAttribute(posAttrib);
        program.pointVertexAttribute(posAttrib, 2, 7 * Float.BYTES, 0);

        // Specify vertex pointer
        int colAttrib = program.getAttributeLocation(Const.COLOR);
        program.enableVertexAttribute(colAttrib);
        program.pointVertexAttribute(colAttrib, 3, 7 * Float.BYTES, 2 * Float.BYTES);

        // Specify texture pointer
        int texAttrib = program.getAttributeLocation(Const.TEXCOORD);
        program.enableVertexAttribute(texAttrib);
        program.pointVertexAttribute(texAttrib, 2, 7 * Float.BYTES, 5 * Float.BYTES);
    }
}
