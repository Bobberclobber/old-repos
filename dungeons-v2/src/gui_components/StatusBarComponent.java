package se.liu.ida.josfa969.dungeonsv2.gui_components;

import se.liu.ida.josfa969.dungeonsv2.Board;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.BoardListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ObjectListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 2014-04-06.
 * <p/>
 * A JComponent which displays the mini-map, the current level,
 * the player's score and the player's health bar
 * <p/>
 * The Status Bar is updated every time an enemy dies,
 * the player takes damage or the player switches rooms
 *
 * @see javax.swing.JComponent
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.BoardListener
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ObjectListener
 */
class StatusBarComponent extends JComponent implements BoardListener, ObjectListener {

    private final Board board;

    StatusBarComponent(Board board) {
        this.board = board;
    }

    @Override
    public void boardChanged() {
        repaint();
    }

    @Override
    public void objectChanged() {
        repaint();
    }

    /**
     * Paints the statusBar which contains a
     * mini-map of all the rooms, the current
     * level, the player's score and the player's health
     *
     * @param g A Graphics object used to paint the
     *          different components of the StatusBar
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     * @see java.awt.Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        Room currentRoom = board.getCurrentRoom();
        PlayerCharacter playerCharacter = currentRoom.getPlayerCharacter();

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paints a background for the status bar
        paintBackground(g2d);

        // Paints the mini-map
        paintMiniMap(currentRoom, g2d);

        // Draws the player's score
        drawCurrentScore(g2d);

        // Draws the current level
        drawCurrentLevel(g2d);

        // Paints the character's health bar
        paintHealthBar(playerCharacter, g2d);
    }

    /**
     * Paints the player's health bar by painting a
     * rectangle that is twice as long as how much
     * health the character has remaining
     *
     * @param playerCharacter The PlayerCharacter whose health
     *                        is used when painting the health bar
     * @param g2d A Graphics2D object used to paint the
     *            health bar
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     * @see java.awt.Graphics2D
     */
    private void paintHealthBar(PlayerCharacter playerCharacter, Graphics2D g2d) {
        if (playerCharacter.getHealth() > 0) {
            g2d.fillRect(Constants.STATUS_BAR_WIDTH - 2 * Constants.CHARACTER_STANDARD_HEALTH, 0, 2 * playerCharacter.getHealth(), Constants.HEALTH_BAR_HEIGHT);

            // Paints the character's health percentage on the health bar
            g2d.setColor(Color.BLACK);
            if (playerCharacter.getHealth() <= Constants.CHARACTER_STANDARD_HEALTH / 2) {
                g2d.setColor(Color.WHITE);
            }
            g2d.setFont(new Font("Arial", Font.BOLD, Constants.DUNGEON_BUTTON_FONT_SIZE));
            g2d.drawString(playerCharacter.getHealth() + "%", Constants.STATUS_BAR_WIDTH - Constants.HEALTH_PERCENTAGE_X_CORD, Constants.HEALTH_PERCENTAGE_Y_CORD);
        }
    }

    /**
     * Uses the level variable from the
     * DungeonMain class to draw a String
     * showing which the current level is
     *
     * @param g2d A Graphics2D object used to draw the String
     * @see se.liu.ida.josfa969.dungeonsv2.DungeonMain
     * @see java.awt.Graphics2D
     */
    private void drawCurrentLevel(Graphics2D g2d) {
        g2d.drawString("Level: " + board.getLevel(), Constants.LEVEL_TEXT_X_CORD, Constants.STATUS_TEXT_Y_CORD);
    }

    private void paintBackground(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Constants.STATUS_BAR_WIDTH, Constants.STATUS_BAR_HEIGHT);
    }

    private void drawCurrentScore(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, Constants.DUNGEON_BUTTON_FONT_SIZE));
        g2d.drawString("Score: " + board.getScore(), Constants.SCORE_TEXT_X_CORD, Constants.STATUS_TEXT_Y_CORD);
    }

    /**
     * Uses the explored and entered variables from each room to
     * paint a mini map (a grid of rectangles, where each rectangle
     * represents a room).
     * <br>
     * An empty slot isn't painted at all, the
     * current room is painted white, a room which has been entered
     * is painted gray and a room which can be accessed from a room
     * which has been entered is painted black
     *
     * @param currentRoom The current Room
     * @param g2d A Graphics2D object used to draw the
     *            rectangles which make up the mini-map
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     * @see java.awt.Graphics2D
     */
    private void paintMiniMap(Room currentRoom, Graphics2D g2d) {
        int mapRoomWidth = Constants.MAP_ROOM_WIDTH;
        int mapRoomHeight = Constants.MAP_ROOM_HEIGHT;
        // Paints a map of all the rooms
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < board.getBoardHeight(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++)
            {
                Room tempRoom = board.getRoom(j, i);
                // Paints every room which has been entered solid gray
                if (tempRoom != null && tempRoom.isEntered()) {
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(j * mapRoomWidth, i * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                }

                g2d.setColor(Color.WHITE);
                // Paints the current room solid white
                if (currentRoom.equals(tempRoom)) {
                    g2d.fillRect(j * mapRoomWidth, i * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                }

                // Paints a white frame around every room which is explored
                if (tempRoom != null && tempRoom.isExplored()) {
                    g2d.drawRect(j * mapRoomWidth, i * mapRoomHeight, mapRoomWidth, mapRoomHeight);
                }
            }
        }
    }
}
