package body;

import graphics.Canvas;
import math.Vector3f;

import java.awt.*;

/**
 * Created by Josef on 14/07/2015.
 * <p>
 *     A 3D parallelepiped consisting of 6 parallelograms
 * </p>
 */
public class Hexahedron2 implements Body {
    private Parallelogram2 p1, p2, p3;
    private Parallelogram2 p4, p5, p6;

    // The three vectors needed to construct the body and the world position
    public Hexahedron2(Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f pos) {
        p1 = new Parallelogram2(vec1, vec2, pos);
        p2 = new Parallelogram2(vec1, vec3, pos);
        p3 = new Parallelogram2(vec2, vec3, pos);

        p4 = new Parallelogram2(vec1, vec2, vec3, pos);
        p5 = new Parallelogram2(vec1, vec3, vec2, pos);
        p6 = new Parallelogram2(vec2, vec3, vec1, pos);
    }

    // Create a hexahedron with the given internal offset
    public Hexahedron2(Vector3f vec1, Vector3f vec2, Vector3f vec3, Vector3f offset, Vector3f pos) {
        p1 = new Parallelogram2(vec1, vec2, offset, pos);
        p2 = new Parallelogram2(vec1, vec3, offset, pos);
        p3 = new Parallelogram2(vec2, vec3, offset, pos);

        p4 = new Parallelogram2(vec1, vec2, vec3.add(offset), pos);
        p5 = new Parallelogram2(vec1, vec3, vec2.add(offset), pos);
        p6 = new Parallelogram2(vec2, vec3, vec1.add(offset), pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        p1.addToCanvas(canvas);
        p2.addToCanvas(canvas);
        p3.addToCanvas(canvas);

        p4.addToCanvas(canvas);
        p5.addToCanvas(canvas);
        p6.addToCanvas(canvas);

        return this;
    }

    @Override
    public Body setColor(Color color) {
        p1.setColor(color);
        p2.setColor(color);
        p3.setColor(color);

        p4.setColor(color);
        p5.setColor(color);
        p6.setColor(color);

        return this;
    }
}
