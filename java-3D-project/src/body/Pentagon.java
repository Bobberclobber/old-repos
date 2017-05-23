package body;

import graphics.Canvas;
import math.Vector3f;

import java.awt.*;

/**
 * Created by Josef on 16/07/2015.
 * <p>
 *     A pentagon consisting of 5 triangles
 * </p>
 */
public class Pentagon implements Body {
    private final double DEG = 2 * Math.PI / 5;
    private Triangle t1, t2, t3, t4, t5;

    // Create a pentagon with the given side length at the given position
    public Pentagon(double side, Vector3f pos) {
        // The five vectors needed to create the triangles
        Vector3f vec1 = new Vector3f(side, 0, 0);
        Vector3f vec2 = new Vector3f(side * Math.cos(DEG), side * Math.sin(DEG), 0);
        Vector3f vec3 = new Vector3f(side * Math.cos(2*DEG), side * Math.sin(2*DEG), 0);
        Vector3f vec4 = new Vector3f(side * Math.cos(3*DEG), side * Math.sin(3*DEG), 0);
        Vector3f vec5 = new Vector3f(side * Math.cos(4*DEG), side * Math.sin(4*DEG), 0);

        // The triangles
        t1 = new Triangle(vec1, vec2, pos);
        t2 = new Triangle(vec2, vec3, pos);
        t3 = new Triangle(vec3, vec4, pos);
        t4 = new Triangle(vec4, vec5, pos);
        t5 = new Triangle(vec5, vec1, pos);
    }

    // Create a pentagon with the given internal offset
    public Pentagon(int side, Vector3f offset, Vector3f pos) {
        // The five vectors needed to create the triangles
        Vector3f vec1 = new Vector3f(side, 0, 0);
        Vector3f vec2 = new Vector3f(side * Math.cos(DEG), side * Math.sin(DEG), 0);
        Vector3f vec3 = new Vector3f(side * Math.cos(2*DEG), side * Math.sin(2*DEG), 0);
        Vector3f vec4 = new Vector3f(side * Math.cos(3*DEG), side * Math.sin(3*DEG), 0);
        Vector3f vec5 = new Vector3f(side * Math.cos(4*DEG), side * Math.sin(4*DEG), 0);

        // The triangles
        t1 = new Triangle(vec1, vec2, offset, pos);
        t2 = new Triangle(vec2, vec3, offset, pos);
        t3 = new Triangle(vec3, vec4, offset, pos);
        t4 = new Triangle(vec4, vec5, offset, pos);
        t5 = new Triangle(vec5, vec1, offset, pos);
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        t1.addToCanvas(canvas);
        t2.addToCanvas(canvas);
        t3.addToCanvas(canvas);
        t4.addToCanvas(canvas);
        t5.addToCanvas(canvas);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        t1.setColor(color);
        t2.setColor(color);
        t3.setColor(color);
        t4.setColor(color);
        t5.setColor(color);
        return this;
    }
}
