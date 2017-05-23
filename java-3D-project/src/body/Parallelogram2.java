package body;

import graphics.Canvas;
import math.Vector3f;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *      A parallelogram consisting of two triangles
 * </p>
 */
public class Parallelogram2 implements Body {
    private Triangle t1, t2;

    // Pass in two vectors representing the two necessary axis
    public Parallelogram2(Vector3f vec1, Vector3f vec2, Vector3f pos) {
        t1 = new Triangle(vec1, vec2, pos);
        t2 = new Triangle(vec2.subtract(vec1), vec2, vec1, pos);
    }

    public Parallelogram2(Vector3f vec1, Vector3f vec2, Vector3f offset, Vector3f pos) {
        t1 = new Triangle(vec1, vec2, offset, pos);
        t2 = new Triangle(vec2.subtract(vec1), vec2, vec1.add(offset), pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        t1.addToCanvas(canvas);
        t2.addToCanvas(canvas);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        t1.setColor(color);
        t2.setColor(color);
        return this;
    }
}
