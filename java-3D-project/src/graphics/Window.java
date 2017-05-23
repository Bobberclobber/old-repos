package graphics;

import body.Sphere;
import math.Vector3f;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     The window used to display the bodies
 * </p>
 */
public class Window extends JFrame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public Canvas canvas;

    public Window() {
        super("Simulation Window");

        // Components
        JLayeredPane pane = new JLayeredPane();
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        pane.add(canvas);

        // Create bodies
        //(new Vertex(new Vector3f(), new Vector3f(100, 100, 50), new Vector3f(250, 300, 75))).addToCanvas(canvas);
        //(new Triangle(new Vector3f(100, -15, 0), new Vector3f(200, 50, 0), new Vector3f(500, 200, 0))).addToCanvas(canvas);
        //(new Parallelogram(new Vector3f(100, 0, 0), new Vector3f(0, 100, 0), new Vector3f(200, 500, 0))).addToCanvas(canvas);
        //(new Parallelogram2(new Vector3f(100, 0, 0), new Vector3f(0, 100, 0), new Vector3f(400, 500, 0))).addToCanvas(canvas);
        //(new Tetrahedron(new Vector3f(100, 25, 10), new Vector3f(10, 78, 60), new Vector3f(50, -50, 12), new Vector3f(250, 250, 0))).addToCanvas(canvas);
        //(new Tetrahedron2(new Vector3f(100, 25, 10), new Vector3f(10, 78, 60), new Vector3f(50, -50, 12), new Vector3f(650, 250, 0))).addToCanvas(canvas);
        //(new Hexahedron(new Vector3f(100, 0, 0), new Vector3f(0, 100, 0), new Vector3f(0, 0, 100), new Vector3f(450, 300, 0))).addToCanvas(canvas);
        //(new Hexahedron2(new Vector3f(100, 0, 0), new Vector3f(0, 100, 0), new Vector3f(0, 0, 100), new Vector3f(650, 300, 0))).addToCanvas(canvas);
        //(new Pentagon(75, new Vector3f(250, 250, 0))).addToCanvas(canvas);
        (new Sphere(50, 64, new Vector3f(475, 375, 0))).setColor(Color.GREEN).addToCanvas(canvas);

        // Set options
        pane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pane.setOpaque(true);
        pane.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(pane, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
