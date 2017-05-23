package se.liu.ida.josfa969.dungeonsv2.gui_components;

import se.liu.ida.josfa969.dungeonsv2.Board;
import se.liu.ida.josfa969.dungeonsv2.DungeonMain;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.enums.DungeonButtonType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * A JFrame containing all different GUI-Components
 *
 * @see javax.swing.JFrame
 */
public class DungeonFrame extends JFrame {

    private DungeonMain dungeonMain = null;
    private JLayeredPane mainContentPane = null;
    private BackgroundComponent background = null;

    public DungeonFrame(DungeonMain dungeonMain, Board board) {
        super(Constants.TITLE);
        this.dungeonMain = dungeonMain;
        makeFrameContent(board);
    }

    /**
     * Creates the frame's content without creating a new frame.
     * The frame contains a JLayeredPane which in turn contains
     * a StatusBarComponent, a PlayFieldComponent and a BackgroundComponent.
     * The method also adds listeners to the board,
     * the current room and to the player character
     *
     * @param board The current board, used to retrieve
     *              the current room and the player character
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     * @see javax.swing.JFrame
     * @see javax.swing.JLayeredPane
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.BackgroundComponent
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.StatusBarComponent
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.PlayFieldComponent
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.BoardListener
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.RoomListener
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ObjectListener
     */
    public void makeFrameContent(Board board) {
        Room currentRoom = board.getCurrentRoom();
        // Creates the JComponents
        mainContentPane = new JLayeredPane();
        background = new BackgroundComponent();
        StatusBarComponent statusBar = new StatusBarComponent(board);
        PlayFieldComponent playField = new PlayFieldComponent(board);

        // Adds listeners
        board.addBoardListener(statusBar);
        board.addBoardListener(playField);
        currentRoom.addRoomListener(playField);
        currentRoom.getPlayerCharacter().addObjectListener(statusBar);
        currentRoom.getPlayerCharacter().addObjectListener(playField);
        this.addKeyListener(playField);

        // Modifies the JFrame
        this.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adds JComponents to the JFrame
        this.add(mainContentPane, BorderLayout.CENTER);

        /*
         * Modifies the JComponents
         */
        // DungeonMain content pane
        mainContentPane.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Background component
        background.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        background.setOpaque(true);

        // Play field component
        playField.setBounds(Constants.ROOM_X_CORD, Constants.ROOM_Y_CORD, Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
        playField.setOpaque(true);

        // Status bar component
        statusBar.setBounds(Constants.STATUS_BAR_X_CORD, Constants.STATUS_BAR_Y_CORD, Constants.STATUS_BAR_WIDTH, Constants.STATUS_BAR_HEIGHT);
        statusBar.setOpaque(true);

        // Adds components to the main content pane
        mainContentPane.add(background, 0, 0);
        mainContentPane.add(statusBar, 1, 0);
        mainContentPane.add(playField, 1, 0);

        // Makes the frame show up correctly
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Makes sure everything gets painted when the content is created
        board.notifyListeners();
    }

    /**
     * Removes all components from the mainContentPane
     * and adds a new BackgroundComponent and two
     * DungeonButtons, one for restarting and one for exiting
     *
     * @see javax.swing.JLayeredPane
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonButton
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.BackgroundComponent
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener
     */
    public void gameOver() {
        mainContentPane.removeAll();

        // Creates and modifies the buttons
        DungeonButton restartButton = new DungeonButton(DungeonButtonType.RESTART_BUTTON);
        DungeonButton exitButton = new DungeonButton(DungeonButtonType.EXIT_BUTTON);
        restartButton.setBounds(Constants.RESTART_BUTTON_X_CORD, Constants.RESTART_BUTTON_Y_CORD, Constants.DUNGEON_BUTTON_WIDTH, Constants.DUNGEON_BUTTON_HEIGHT);
        exitButton.setBounds(Constants.EXIT_BUTTON_X_CORD, Constants.EXIT_BUTTON_Y_CORD, Constants.DUNGEON_BUTTON_WIDTH, Constants.DUNGEON_BUTTON_HEIGHT);
        restartButton.setOpaque(true);
        exitButton.setOpaque(true);
        restartButton.addButtonClickListener(new RestartButtonListener());
        exitButton.addButtonClickListener(new ExitButtonListener());

        // Adds components to the game over screen
        mainContentPane.add(background, 2, 0);
        mainContentPane.add(restartButton, 3, 0);
        mainContentPane.add(exitButton, 3, 0);
    }

    /**
     * The listener for the restart button
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener
     */
    private class RestartButtonListener implements DungeonButtonListener {
        @Override
        public void onButtonClick() {
            mainContentPane.removeAll();
            dungeonMain.startGame();
        }
    }

    /**
     * The listener for the exit button
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener
     */
    private class ExitButtonListener implements DungeonButtonListener {
        @Override
        public void onButtonClick() {
            System.exit(0);
        }
    }
}
