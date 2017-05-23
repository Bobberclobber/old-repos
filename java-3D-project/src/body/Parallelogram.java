package body;

import graphics.Canvas;
import math.Vector3f;
import math.Vertex;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *      A parallelogram constructed of five vertices
 * </p>
 */
public class Parallelogram implements Body {
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Vertex v4;
    private Vertex v5;

    // Pass in two vectors representing the two necessary axis
    public Parallelogram(Vector3f vec1, Vector3f vec2, Vector3f pos) {
        v1 = new Vertex(new Vector3f(), vec1, pos);
        v2 = new Vertex(new Vector3f(), vec2, pos);
        v3 = new Vertex(vec1, vec1.add(vec2), pos);
        v4 = new Vertex(vec2, vec2.add(vec1), pos);
        v5 = new Vertex(vec1, vec2, pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        canvas.addVertex(v1);
        canvas.addVertex(v2);
        canvas.addVertex(v3);
        canvas.addVertex(v4);
        canvas.addVertex(v5);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        v1.setColor(color);
        v2.setColor(color);
        v3.setColor(color);
        v4.setColor(color);
        v5.setColor(color);
        return this;
    }
}
