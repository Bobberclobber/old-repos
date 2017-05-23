package math;

import graphics.Canvas;

import java.awt.*;

/**
 * Created by Josef on 19/06/2015.
 * <p>
 *     Class describing a single vertex in a graphical triangle
 * </p>
 */
public class Vertex {
    public final String ID;

    private Vector3f internalStart;
    private Vector3f internalEnd;
    private Vector3f worldPos;

    // Vectors used to store the directions in which the vertex should be scaled
    private Vector3f startDirection;
    private Vector3f endDirection;

    // An optional color to be used when coloring this vertex
    public Color color;

    // The first two vectors are used to represent the vertex in the internal coordinate system
    // The third vector contains the vertex's world coordinates (based on the internal origin)
    public Vertex(Vector3f internalStart, Vector3f internalEnd, Vector3f worldPos) {
        this.internalStart = new Vector3f(internalStart.x, internalStart.y, internalStart.z);
        this.internalEnd = new Vector3f(internalEnd.x, internalEnd.y, internalEnd.z);
        this.worldPos = new Vector3f(worldPos.x, worldPos.y, worldPos.z);

        // Set the endDirection vector
        startDirection = internalStart.sNormalize();
        endDirection = internalEnd.sNormalize();

        // Set color
        this.color = Color.RED;

        // Set ID based on the vertex's world position and internal representation
        ID = internalStart.getID() + "." + internalEnd.getID() + "." + worldPos.getID();
    }

    // The first two vectors are used to represent the vertex in the internal coordinate system
    // The third vector contains the vertex's world coordinates (based on the internal origin)
    public Vertex(Vector3f internalStart, Vector3f internalEnd, Vector3f worldPos, Color color) {
        this.internalStart = new Vector3f(internalStart.x, internalStart.y, internalStart.z);
        this.internalEnd = new Vector3f(internalEnd.x, internalEnd.y, internalEnd.z);
        this.worldPos = new Vector3f(worldPos.x, worldPos.y, worldPos.z);

        // Set the endDirection vector
        startDirection = internalStart.sNormalize();
        endDirection = internalEnd.sNormalize();

        // Set color
        this.color = color;

        // Set ID based on the vertex's world position and internal representation
        ID = internalStart.getID() + internalEnd.getID() + worldPos.getID();
    }

    // Returns the vertex's length
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    // Returns the square of the vertex's length
    public double lengthSquared() {
        Vector3f v = internalEnd.subtract(internalStart);
        return v.x * v.x + v.y * v.y + v.z * v.z;
    }

    // Update the vertex's position in the world by increasing its
    // world coordinates by some given value
    public void translate(Vector3f vec) {
        worldPos = worldPos.add(vec);
    }

    // Rotate the two vectors used to represent the vertex, which causes the entire vertex to
    // rotate around the internal origin
    // Also update the direction vectors
    public void rotate(Matrix3f rMatrix) {
        internalStart = internalStart.matrixMul(rMatrix);
        internalEnd = internalEnd.matrixMul(rMatrix);
        startDirection = internalStart.sNormalize();
        endDirection = internalEnd.sNormalize();
    }

    // Changes the scale of the internalStart vector
    public void scale(double scale) {
        internalStart = internalStart.add(startDirection.scale(scale));
        internalEnd = internalEnd.add(endDirection.scale(scale));
    }

    public Vector3f getStart() {
        return internalStart.add(worldPos);
    }

    public Vector3f getEnd() {
        return internalEnd.add(worldPos);
    }

    public void addToCanvas(Canvas canvas) {
        canvas.addVertex(this);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
