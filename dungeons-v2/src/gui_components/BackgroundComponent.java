package se.liu.ida.josfa969.dungeonsv2.gui_components;

import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * A JComponent used to create the background of the frame
 *
 * @see javax.swing.JComponent
 */
public class BackgroundComponent extends JComponent {
    /**
     * Fills the window with a black Rectangle
     *
     * @param g A Graphics object used to draw graphics
     * @see java.awt.Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    // Clears the background of the component
        final Graphics2D g2d = (Graphics2D) g;
        // Paints the background black
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }
}
