package world;

import world.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Josef on 14/06/2015.
 * <p>
 *     A frame used to test the map generation
 * </p>
 */
public class TestFrame extends JFrame {
    private final int SIZE = 1025;

    private boolean COLOR = false;

    public static void main(String[] args) {
        new TestFrame();
    }

    public TestFrame() {
        super("Test Frame");

        // Components
        JLayeredPane contentPane = new JLayeredPane();
        Canvas canvas = new Canvas();
        canvas.setBounds(0, 26, SIZE, SIZE);
        contentPane.add(canvas);

        JButton regButton = new JButton("Re-Generate");
        regButton.addActionListener(new RegenListener(canvas));
        regButton.setBounds(3, 3, 150, 20);
        contentPane.add(regButton);

        JButton colorButton = new JButton("Color");
        colorButton.addActionListener(new ColorListener(canvas));
        colorButton.setBounds(156, 3, 150, 20);
        contentPane.add(colorButton);

        // Set options
        contentPane.setPreferredSize(new Dimension(SIZE, SIZE + 26));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(contentPane, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class RegenListener implements ActionListener {
        private Canvas canvas;

        public RegenListener(Canvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.generate();
            canvas.repaint();
        }
    }

    private class ColorListener implements ActionListener {
        private Canvas canvas;

        public ColorListener(Canvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            COLOR = !COLOR;
            canvas.repaint();
        }
    }

    private class Canvas extends JComponent {
        private double[][] values;
        private Tile[][] tiles;

        public Canvas() {
            values = new double[SIZE][SIZE];
            tiles = new Tile[SIZE][SIZE];
            generate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw one tile on every pixel
            for (int x = 0; x < SIZE; x++) {
                for (int y = 0; y < SIZE; y++) {
                    if (COLOR)
                        g2d.setColor(tiles[x][y].getColor());
                    else {
                        int rgb = (int) (255 * values[x][y]);
                        g2d.setColor(new Color(rgb, rgb, rgb));
                    }
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }

        public void generate() {
            // Generate and retrieve the two different maps
            MapGenerator generator = new MapGenerator(SIZE, 0.5);
            generator.generate();

            try {
                values = generator.getValues();
                tiles = generator.getTiles();
            } catch (MapGenerator.MapNotGeneratedException e) {
                e.printStackTrace();
            }
        }
    }
}
