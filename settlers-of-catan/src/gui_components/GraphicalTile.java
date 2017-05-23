package se.scramble_studios.nehro.settlers_of_catan.java.gui_components;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 09/01/2015.
 * <p>
 *     A graphical representation of a tile.
 * </p>
 */
public abstract class GraphicalTile extends JComponent {
    private Image tileGraphics;

    public GraphicalTile(Image tileGraphics) {
        this.tileGraphics = tileGraphics;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(tileGraphics, 0, 0, null);
    }

    protected void Temp() {

    }
}
