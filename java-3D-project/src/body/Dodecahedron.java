package body;

import graphics.Canvas;
import math.Vector3f;

import java.awt.*;

/**
 * Created by Josef on 16/07/2015.
 * <p>
 *     A dodecahedron consisting of twelve pentagons
 * </p>
 */
// TODO: Try the Wikipedia approach for this body
public class Dodecahedron implements Body {
    private Pentagon p1, p2, p3, p4;
    private Pentagon p5, p6, p7, p8;
    private Pentagon p9, p10, p11, p12;

    // Create a dodecahedron with the given pentagon side length at the given position
    public Dodecahedron(double side, Vector3f pos) {

    }

    // Create a dodecahedron with the given internal offset
    public Dodecahedron(double side, Vector3f offset, Vector3f pos) {

    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        return this;
    }

    @Override
    public Body setColor(Color color) {
        return this;
    }
}
