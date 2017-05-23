package body;

import graphics.Canvas;
import math.Vector3f;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *     A 3D tetrahedron consisting of 4 triangles
 * </p>
 */
public class Tetrahedron2 implements Body {
    private Triangle t1, t2, t3, t4;

    // Build the tetrahedron using the three required vectors
    public Tetrahedron2(Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f pos) {
        t1 = new Triangle(vec1, vec2, pos);
        t2 = new Triangle(vec1, vec3, pos);
        t3 = new Triangle(vec2, vec3, pos);
        t4 = new Triangle(vec2.subtract(vec1), vec3.subtract(vec1), vec1, pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        t1.addToCanvas(canvas);
        t2.addToCanvas(canvas);
        t3.addToCanvas(canvas);
        t4.addToCanvas(canvas);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        t1.setColor(color);
        t2.setColor(color);
        t3.setColor(color);
        t4.setColor(color);
        return this;
    }
}
