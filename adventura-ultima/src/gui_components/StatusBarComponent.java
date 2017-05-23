package se.wgco.jgf.adventura_ultima.gui_components;

import se.wgco.jgf.adventura_ultima.constants.GameWindowConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 11/05/2014.
 * <p>
 *     A JComponent which paints the game's status bar
 * </p>
 */
public class StatusBarComponent extends JComponent{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draws a black frame around the status bar
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, GameWindowConstants.STATUS_BAR_WIDTH - 1, GameWindowConstants.STATUS_BAR_HEIGHT - 1);
    }
}
