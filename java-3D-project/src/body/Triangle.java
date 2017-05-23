package body;

import graphics.Canvas;
import math.Vector3f;
import math.Vertex;

import java.awt.*;

/**
 * Created by Josef on 18/06/2015.
 * <p>
 *     A triangle constructed of three vertices
 * </p>
 */
public class Triangle implements Body {
    public Vertex v1, v2, v3;

    // Pass in two vectors for representing the triangle internally, and one for
    // representing its coordinates in the world
    public Triangle(Vector3f vec1, Vector3f vec2, Vector3f pos) {
        v1 = new Vertex(new Vector3f(), vec1, pos);
        v2 = new Vertex(new Vector3f(), vec2, pos);
        v3 = new Vertex(vec1, vec2, pos);
    }

    // Create a triangle with the given internal offset
    public Triangle(Vector3f vec1, Vector3f vec2, Vector3f offset, Vector3f pos) {
        v1 = new Vertex(offset, vec1.add(offset), pos);
        v2 = new Vertex(offset, vec2.add(offset), pos);
        v3 = new Vertex(vec1.add(offset), vec2.add(offset), pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        canvas.addVertex(v1);
        canvas.addVertex(v2);
        canvas.addVertex(v3);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        v1.setColor(color);
        v2.setColor(color);
        v3.setColor(color);
        return this;
    }
}
