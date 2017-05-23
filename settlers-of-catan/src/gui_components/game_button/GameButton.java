package se.scramble_studios.nehro.settlers_of_catan.java.gui_components.game_button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 07/01/2015.
 * <p>
 * A JComponent used to create a custom made button.
 * This is a more general JComponent than the class DungeonButton.java which is its root.
 * </p>
 *
 * @see javax.swing.JComponent
 */
public class GameButton extends JComponent {

    private GameButtonType buttonType;
    private String buttonText;
    private Image[] buttonImageArray;
    private Image currentImage;
    private GameButtonComponent[] buttonComponentArray;
    private GameButtonComponent currentComponentButton;
    private final Rectangle buttonRectangle;
    private final List<GameButtonListener> listenerList1 = new ArrayList<GameButtonListener>();

    /**
     * <p>
     * Creates a new GameButton using the given array of images.
     * Three images are needed, one for when no event is occurring,
     * one when the mouse is hovering over the button and one when
     * the button is clicked. They should be in the array in that order.
     * [no_event_img, hovering_img, clicked_img]
     * </p>
     *
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseMotionListener
     */
    public GameButton(Image[] buttonImageArray, int width, int height) {
        this.buttonType = GameButtonType.CUSTOM_BUTTON;
        this.buttonImageArray = buttonImageArray;
        this.currentImage = buttonImageArray[0];
        this.buttonRectangle = new Rectangle(0, 0, width, height);
        MouseAdapter mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    /**
     * <p>
     * Uses one of the predefined image arrays to make a button.
     * </p>
     *
     * @see java.awt.event.MouseListener
     * @see java.awt.event.MouseMotionListener
     */
    public GameButton(String buttonText, int width, int height) {
        this.buttonType = GameButtonType.PRESET_BUTTON;
        this.buttonText = buttonText;
        this.buttonComponentArray = makeButtonComponentArray(buttonType, width, height);
        this.currentComponentButton = buttonComponentArray[0];
        this.buttonRectangle = new Rectangle(0, 0, width, height);
        MouseAdapter mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    public void addButtonClickListener(GameButtonListener listener) {
        listenerList1.add(listener);
    }

    void notifyListeners() {
        for (GameButtonListener listener : listenerList1) {
            listener.onButtonClick();
        }
    }

    /**
     * <p>
     * Paints whichever Image is stored in the
     * currentImage-variable. The Image changes
     * depending on whether the user is hovering
     * over the button, clicking it or neither
     * </p>
     *
     * @param g A Graphics object used to draw graphics
     * @see java.awt.Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (buttonType == GameButtonType.CUSTOM_BUTTON)
            g2d.drawImage(currentImage, 0, 0, null);
        else
            currentComponentButton.paintComponent(g);
    }

    /**
     * <p>
     * The Dungeon Button's mouse handler, used to detect
     * if the button is hovered over, clicked or neither,
     * and change the buttons appearance accordingly by
     * changing the image which is to be painted
     * </p>
     *
     * @see java.awt.event.MouseAdapter
     */
    // Warning suppressed since it is either a false positive or irrelevant
    @SuppressWarnings({"RefusedBequest", "SuppressionAnnotation"})
    private class MouseHandler extends MouseAdapter {

        /**
         * <p>
         * Changes the currentImage to the Image representing
         * a hovered button when the mouse is entering the button
         * </p>
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            if (buttonType == GameButtonType.CUSTOM_BUTTON)
                currentImage = buttonImageArray[1];
            else
                currentComponentButton = buttonComponentArray[1];
            repaint();
        }

        /**
         * <p>
         * Changes the currentImage to the Image representing
         * an inactive button when the mouse is leaving the button
         * </p>
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         */
        @Override
        public void mouseExited(MouseEvent e) {
            if (buttonType == GameButtonType.CUSTOM_BUTTON)
                currentImage = buttonImageArray[0];
            else
                currentComponentButton = buttonComponentArray[0];
            repaint();
        }

        /**
         * <p>
         * Changes the currentImage to the Image representing
         * a clicked button when the user is clicking the button
         * </p>
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (buttonType == GameButtonType.CUSTOM_BUTTON)
                currentImage = buttonImageArray[2];
            else
                currentComponentButton = buttonComponentArray[2];
            repaint();
        }

        /**
         * <p>
         * Notifies all listeners iff the mouse is released within the button's limit
         * </p>
         *
         * @param e A MouseEvent object
         * @see java.awt.event.MouseEvent
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if (buttonType == GameButtonType.CUSTOM_BUTTON)
                currentImage = buttonImageArray[1];
            else
                currentComponentButton = buttonComponentArray[1];
            repaint();
            if (buttonRectangle.contains(e.getPoint())) {
                notifyListeners();
            }
        }
    }

    /**
     * Created by Josef on 2014-04-09.
     * <p>
     * An enum class containing the different types of buttons.
     * </p>
     */
    public static enum GameButtonType {
        PRESET_BUTTON, CUSTOM_BUTTON
    }

    public static enum GameEventType {
        NO_EVENT, HOVERED, CLICKED
    }

    private GameButtonComponent[] makeButtonComponentArray(GameButtonType type, int width, int height) {
        return new GameButtonComponent[]{
                new GameButtonComponent(buttonText, GameEventType.NO_EVENT, width, height),
                new GameButtonComponent(buttonText, GameEventType.HOVERED, width, height),
                new GameButtonComponent(buttonText, GameEventType.CLICKED, width, height)};
    }

    /**
     * Created by Josef on 08/04/2014.
     * <p>
     * A listener interface which is called
     * whenever the user clicks any of the in-game buttons.
     * </p>
     */
    public static interface GameButtonListener {
        public void onButtonClick();
    }

    /**
     * Created by Josef on 09/01/2015.
     * <p>
     * A JComponent which represents a single GameButton
     * </p>
     */
    public class GameButtonComponent extends JComponent {
        private String buttonText;
        private GameButton.GameEventType buttonEvent;
        private int width;
        private int height;

        public GameButtonComponent(String buttonText, GameButton.GameEventType event, int width, int height) {
            this.buttonText = buttonText;
            this.buttonEvent = event;
            this.width = width;
            this.height = height;
        }

        /**
         * <p>
         * Paints a rectangle with black, grey or white background depending on the button event
         * </p>
         *
         * @param g A Java Graphics object
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            switch (buttonEvent) {
                case NO_EVENT:
                    g2d.setColor(Color.BLACK);
                    break;
                case HOVERED:
                    g2d.setColor(Color.GRAY);
                    break;
                case CLICKED:
                    g2d.setColor(Color.WHITE);
                    break;
            }
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(Color.WHITE);
            FontMetrics fm = g2d.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(buttonText, g2d);
            int x = (width - (int) r.getWidth()) / 2;
            int y = (height - (int) r.getHeight()) / 2 + fm.getAscent();
            g.drawString(buttonText, x, y);
        }
    }
}