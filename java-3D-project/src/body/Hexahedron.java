package body;

import graphics.Canvas;
import math.Vector3f;
import math.Vertex;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *     A 3D parallelepiped consisting of 12 vertices and 6 faces
 * </p>
 */
public class Hexahedron implements Body {
    private Vertex v1, v2, v3, v4;
    private Vertex v5, v6, v7, v8;
    private Vertex v9, v10, v11, v12;

    // The three vectors needed to construct the body and the world position
    public Hexahedron(Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f pos) {
        // Three from origin
        v1 = new Vertex(new Vector3f(), vec1, pos);
        v2 = new Vertex(new Vector3f(), vec2, pos);
        v3 = new Vertex(new Vector3f(), vec3, pos);

        // Two from v1
        v4 = new Vertex(vec1, vec1.add(vec2), pos);
        v5 = new Vertex(vec1, vec1.add(vec3), pos);

        // Two from v2
        v6 = new Vertex(vec2, vec2.add(vec1), pos);
        v7 = new Vertex(vec2, vec2.add(vec3), pos);

        // Two from v3
        v8 = new Vertex(vec3, vec3.add(vec1), pos);
        v9 = new Vertex(vec3, vec3.add(vec2), pos);

        // Three for the far-away corner
        v10 = new Vertex(vec1.add(vec2), vec1.add(vec2).add(vec3), pos);
        v11 = new Vertex(vec1.add(vec3), vec1.add(vec2).add(vec3), pos);
        v12 = new Vertex(vec2.add(vec3), vec1.add(vec2).add(vec3), pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        canvas.addVertex(v1).addVertex(v2).addVertex(v3);
        canvas.addVertex(v4).addVertex(v5).addVertex(v6);
        canvas.addVertex(v7).addVertex(v8).addVertex(v9);
        canvas.addVertex(v10).addVertex(v11).addVertex(v12);
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
        v7.setColor(color);
        v8.setColor(color);

        v9.setColor(color);
        v10.setColor(color);
        v11.setColor(color);
        v12.setColor(color);
        return this;
    }
}
