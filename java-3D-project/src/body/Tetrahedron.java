package body;

import graphics.Canvas;
import math.Vector3f;
import math.Vertex;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *     A 3D tetrahedron consisting of 6 vertices with four faces
 * </p>
 */
public class Tetrahedron implements Body {
    private Vertex v1, v2, v3, v4, v5, v6;

    // Build the tetrahedron using the three required vectors
    public Tetrahedron(Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f pos) {
        v1 = new Vertex(new Vector3f(), vec1, pos);
        v2 = new Vertex(new Vector3f(), vec2, pos);
        v3 = new Vertex(new Vector3f(), vec3, pos);
        v4 = new Vertex(vec1, vec2, pos);
        v5 = new Vertex(vec1, vec3, pos);
        v6 = new Vertex(vec2, vec3, pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        canvas.addVertex(v1);
        canvas.addVertex(v2);
        canvas.addVertex(v3);
        canvas.addVertex(v4);
        canvas.addVertex(v5);
        canvas.addVertex(v6);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        v1.setColor(color);
        v2.setColor(color);
        v3.setColor(color);

        v4.setColor(color);
        v5.setColor(color);
        v6.setColor(color);
        return this;
    }
}
