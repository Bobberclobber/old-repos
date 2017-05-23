package body;

import graphics.Canvas;
import math.Vector3f;
import math.Vertex;

import java.awt.*;
import java.util.Vector;

/**
 * Created by Josef on 16/07/2015.
 * <p>
 *     A sphere consisting of a shitload of vertices
 * </p>
 */
public class Sphere implements Body {
    private Vector<Vertex> vertices;
    private Vector<Vector3f> vectors;

    // Create a sphere with the given radius at the given position
    public Sphere(double r, Vector3f pos) {
        vertices = new Vector<Vertex>();
        vectors = new Vector<Vector3f>();

        try {
            createSphere2(r, 8, pos);
        } catch (IllegalComplexityException e) {
            e.printStackTrace();
        }
    }

    // Create a sphere with the given complexity.
    // The complexity determines how many vertices are used to create the sphere
    public Sphere(double r, int c, Vector3f pos) {
        vertices = new Vector<Vertex>();
        vectors = new Vector<Vector3f>();

        try {
            createSphere2(r, c, pos);
        } catch (IllegalComplexityException e) {
            e.printStackTrace();
        }
    }

    private void createSphere2(double r, int c, Vector3f pos) throws IllegalComplexityException {
        // Make sure we only get even complexity numbers
        if (c % 2 != 0)
            throw new IllegalComplexityException();

        // The degree in radians between each vertex
        double deg = 2 * Math.PI / c;

        /*
        * The vectors needed to create the vertices
        * */
        // The circles centered around the sphere mid-point
        for (int i = 0; i < c/2; i++) {
            for (int j = 0; j < c; j++) {
                double x = r * Math.cos(j*deg);
                double y = r * Math.cos(i*deg) * Math.sin(j*deg);
                double z = r * Math.sin(j*deg) * Math.sin(i*deg);
                vectors.add(new Vector3f(x, y, z));
            }
        }

        // Add the orthogonal circles
        for (int i = 0; i < (c/2)-1; i++) {
            // The current x-position
            double x = r * Math.cos(deg + i*deg);

            // Add one orthogonal circle
            for (int j = 0; j < c; j++) {
                double y = r * Math.sin(deg + i * deg) * Math.sin(j*deg);
                double z = r * Math.sin(deg + i * deg) * Math.cos(j*deg);
                vectors.add(new Vector3f(x, y, z));
            }
        }

        // Create the vertices
        for (int i = 0; i < vectors.size(); i++) {
            int first = (i / c) * c + i % c;
            int second = (i / c) * c + (first + 1) % c;
            vertices.add(new Vertex(vectors.get(first), vectors.get(second), pos));
        }
    }

    @Override
    public Body addToCanvas(Canvas canvas) {
        for (Vertex v : vertices)
            v.addToCanvas(canvas);
        return this;
    }

    @Override
    public Body setColor(Color color) {
        for (Vertex v : vertices)
            v.setColor(color);
        return this;
    }

    public class IllegalComplexityException extends Exception {
        @Override
        public void printStackTrace() {
            super.printStackTrace();
            System.err.println("The complexity must be an even number");
        }
    }
}
