package graphics;

import math.Matrix3f;
import math.Vector3f;
import math.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     The canvas on which all graphics are painted
 * </p>
 */
public class Canvas extends JComponent {
    private ArrayList<Vertex> vertexArray;
    private HashSet<String> vertexIDs;

    public Canvas() {
        vertexArray = new ArrayList<Vertex>();
        vertexIDs = new HashSet<String>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

        // Render vertices
        for (Vertex v : vertexArray) {
            g2d.setColor(v.color);

            Vector3f start = v.getStart();
            Vector3f end = v.getEnd();
            g2d.drawLine((int) Math.round(start.x), (int) Math.round(start.y),
                    (int) Math.round(end.x), (int) Math.round(end.y));
        }
    }

    public Canvas addVertex(Vertex v) {
        if (!vertexIDs.contains(v.ID)) {
            vertexIDs.add(v.ID);
            vertexArray.add(v);
            repaint();
        }
        return this;
    }

    public void translateAll(Vector3f vec) {
        for (Vertex v : vertexArray)
            v.translate(vec);
        repaint();
    }

    public void scaleAll(double scale) {
        for (Vertex v : vertexArray)
            v.scale(scale);
        repaint();
    }

    public void rotateAll(Matrix3f rMatrix) {
        for (Vertex v : vertexArray)
            v.rotate(rMatrix);
        repaint();
    }
}
