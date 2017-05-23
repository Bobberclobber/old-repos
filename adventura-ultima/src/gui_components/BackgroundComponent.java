package se.wgco.jgf.adventura_ultima.gui_components;

import se.wgco.jgf.adventura_ultima.constants.GameWindowConstants;
import se.wgco.jgf.adventura_ultima.constants.WorldConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 07/05/2014.
 * <p>
 *     A JComponent which paints the background of the window
 * </p>
 */
public class BackgroundComponent extends JComponent {
    private final int WIDTH_SQUARE_NUMBER = GameWindowConstants.WINDOW_WIDTH;
    private final int HEIGHT_SQUARE_NUMBER = GameWindowConstants.WINDOW_HEIGHT;

    public BackgroundComponent() {
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GameWindowConstants.WINDOW_WIDTH, GameWindowConstants.WINDOW_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < WIDTH_SQUARE_NUMBER; i++) {
            for (int j = 0; j < HEIGHT_SQUARE_NUMBER; j++) {
                boolean evenRowBlack = j % 2 == 0 && i % 2 == 0;
                boolean oddRowBlack = j % 2 == 1 && i % 2 == 1;
                if (evenRowBlack || oddRowBlack) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.DARK_GRAY);
                }
                g2d.fillRect(i, j, 1, 1);
            }
        }
    }
}
