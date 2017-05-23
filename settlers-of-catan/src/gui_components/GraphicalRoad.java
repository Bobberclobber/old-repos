package se.scramble_studios.nehro.settlers_of_catan.java.gui_components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 12/01/2015.
 * <p>
 *     A graphical representation of a road
 * </p>
 */
public abstract class GraphicalRoad extends JComponent {
    private Image roadGraphics;
    private Polygon roadPolygon;

    public GraphicalRoad(Image roadGraphics, Polygon roadPolygon) {
        this.roadGraphics = roadGraphics;
        this.roadPolygon = roadPolygon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.drawImage(roadGraphics, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.drawPolygon(roadPolygon);
    }
}
