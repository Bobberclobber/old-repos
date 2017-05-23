package se.liu.ida.josfa969.dungeonsv2.gui_components;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener;
import se.liu.ida.josfa969.dungeonsv2.enums.DungeonButtonType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Josef on 08/04/2014.
 * <p/>
 * A JComponent used to create a custom made button
 *
 * @see javax.swing.JComponent
 */
public class DungeonButton extends JComponent {

    private final Image[] buttonImageArray;
    private Image currentImage;
    private final Rectangle buttonRectangle = new Rectangle(0, 0, Constants.DUNGEON_BUTTON_WIDTH, Constants.DUNGEON_BUTTON_HEIGHT);
    private final List<DungeonButtonListener> listenerList1 = new ArrayList<>();

    /**
     * Creates a new DungeonButton of the given type and
     * adds a MouseListener and a MouseMotionListener to is
     *
     * @param buttonType Decides which List of Images will be used for this DungeonButton
     * @see se.liu.ida.josfa969.dungeonsv2.enums.DungeonButtonType
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseMotionListener
     */
    public DungeonButton(DungeonButtonType buttonType) {
        buttonImageArray = Constants.getImageArray(buttonType);
        currentImage = buttonImageArray[0];
        MouseAdapter mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    public void addButtonClickListener(DungeonButtonListener listener) {
        listenerList1.add(listener);
    }

    void notifyListeners() {
        for (DungeonButtonListener listener : listenerList1) {
            listener.onButtonClick();
        }
    }

    /**
     * Paints whichever Image is stored in the
     * currentImage-variable. The Image changes
     * depending on whether the user is hovering
     * over the button, clicking it or neither
     *
     * @param g A Graphics object used to draw graphics
     * @see java.awt.Graphics
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(currentImage, 0, 0, null);
    }

    /**
     * The Dungeon Button's mouse handler, used to detect
     * if the button is hovered over, clicked or neither,
     * and change the buttons appearance accordingly by
     * changing the image which is to be painted
     *
     * @see java.awt.event.MouseAdapter
     */
    // Warning suppressed since it is either a false positive or irrelevant
    @SuppressWarnings({"RefusedBequest", "SuppressionAnnotation"})
    private class MouseHandler extends MouseAdapter {

        /**
         * Changes the currentImage to the Image representing
         * a hovered button when the mouse is entering the button
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            currentImage = buttonImageArray[1];
            repaint();
        }

        /**
         * Changes the currentImage to the Image representing
         * an inactive button when the mouse is leaving the button
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
         */
        @Override
        public void mouseExited(MouseEvent e) {
            currentImage = buttonImageArray[0];
            repaint();
        }

        /**
         * Changes the currentImage to the Image representing
         * a clicked button when the user is clicking the button
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
         */
        @Override
        public void mousePressed(MouseEvent e) {
            currentImage = buttonImageArray[2];
            repaint();
        }

        /**
         * Notifies all listeners iff the mouse is released within the button's limit
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (buttonRectangle.contains(e.getPoint())) {
                notifyListeners();
            }
        }
    }

}
