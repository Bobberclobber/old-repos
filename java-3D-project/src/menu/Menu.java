package menu;

import graphics.Canvas;
import menu.rotation.*;
import menu.scaling.DecScaleListener;
import menu.scaling.IncScaleListener;
import menu.translation.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by Josef on 12/07/2015.
 * <p>
 *     The menu used to control what appears on the canvas
 * </p>
 */
public class Menu extends JFrame {
    public static int WIDTH = 120;
    public static int HEIGHT = 400;

    public Vector<JTextField> fields = new Vector<JTextField>();

    public Menu(Canvas canvas) {
        super("Menu");

        // Components
        JLayeredPane pane = new JLayeredPane();

        /*
        * TRANSLATIONS
        * */
        // X-Axis Translations
        addLabel(pane, "X-Axis Translation", 5);
        addIncDecPair(pane, 26, new XDecTranslationListener(canvas), new XIncTranslationListener(canvas));

        // Y-Axis Translations
        addLabel(pane, "Y-Axis Translation", 51);
        addIncDecPair(pane, 72, new YDecTranslationListener(canvas), new YIncTranslationListener(canvas));

        // Z-Axis Translations
        addLabel(pane, "Z-Axis Translation", 97);
        addIncDecPair(pane, 118, new ZDecTranslationListener(canvas), new ZIncTranslationListener(canvas));

        /*
        * ROTATIONS
        * */
        // X-Axis Rotation
        addLabel(pane, "X-Axis Rotation", 143);
        addIncDecPair(pane, 164, new XNegRotationListener(canvas), new XPosRotationListener(canvas));

        // Y-Axis Rotation
        addLabel(pane, "Y-Axis Rotation", 189);
        addIncDecPair(pane, 210, new YNegRotationListener(canvas), new YPosRotationListener(canvas));

        // Z-Axis Rotation
        addLabel(pane, "Z-Axis Rotation", 235);
        addIncDecPair(pane, 256, new ZNegRotationListener(canvas), new ZPosRotationListener(canvas));

        /*
        * SCALING
        * */
        // Decrease scale
        addLabel(pane, "Change scale", 281);
        addIncDecPair(pane, 302, new DecScaleListener(canvas), new IncScaleListener(canvas));

        // Set options
        pane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(pane, BorderLayout.CENTER);

        this.pack();
        this.setLocation(0, 0);
        this.setVisible(true);
    }

    private void addLabel(JLayeredPane pane, String text, int y) {
        JLabel xLabel = new JLabel(text);
        xLabel.setBounds(5, y, WIDTH-10, 20);
        pane.add(xLabel);
    }

    private void addIncDecPair(JLayeredPane pane, int y, MetaListener decLi, MetaListener incLi) {
        JButton decButton = new JButton(" - ");
        JButton incButton = new JButton(" + ");
        decButton.addMouseListener(decLi);
        incButton.addMouseListener(incLi);
        decButton.setBounds(5, y, 50, 20);
        incButton.setBounds(55, y, 50, 20);
        pane.add(decButton);
        pane.add(incButton);
    }
}
