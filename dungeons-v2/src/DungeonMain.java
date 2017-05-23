package se.liu.ida.josfa969.dungeonsv2;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ProjectileHandler;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.*;
import se.liu.ida.josfa969.dungeonsv2.enums.DungeonButtonType;
import se.liu.ida.josfa969.dungeonsv2.gui_components.BackgroundComponent;
import se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonButton;
import se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonFrame;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;
import se.liu.ida.josfa969.dungeonsv2.help_classes.TickActionMethods;

import javax.swing.*;
import javax.swing.Timer; // Warning on this is false positive
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List; // Warning on this is false positive

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * The main class of the program
 * <p/>
 * This class contains the main-method, variables for keeping
 * track of the score and the current level, and the main game loop
 * <p/>
 * The main class when started creates the main menu
 * containing a start button and an exit button
 * <p/>
 * The main game loop updates all the Non Player Controlled
 * object's positions and checks all Dungeon Objects
 * for collisions with other Dungeon Objects
 *
 * @see javax.swing.JFrame
 */

public class DungeonMain extends JFrame {

    private boolean running = true;

    private static JFrame menuFrame = null;
    private Board board = null;
    private Room currentRoom = null;
    private PlayerCharacter playerCharacter = null;

    private DungeonFrame frame = null;

    /**
     * The timer which controls the updateObjects Action
     *
     * @see java.util.Timer
     * @see javax.swing.Action
     */
    private Timer objectTimer = null;

    /**
     * The timer which controls the updateProjectiles Action
     *
     * @see java.util.Timer
     * @see javax.swing.Action
     */
    private Timer projectileTimer = null;

    /**
     * The timer which controls the wallCollisionCheck Action
     * @see java.util.Timer
     * @see javax.swing.Action
     */
    private Timer wallCollisionCheckTimer = null;

    /**
     * The timer which controls the deathCheck Action
     * @see java.util.Timer
     * @see javax.swing.Action
     */
    private Timer deathCheckTimer = null;

    public static void main(String[] args) {
        menuFrame = new DungeonMain();
    }

    /**
     * Creates the start menu
     *
     * @see javax.swing.JFrame
     * @see javax.swing.JLayeredPane
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.BackgroundComponent
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonButton
     */
    private DungeonMain() {
        super(Constants.TITLE);

        // Creates the JComponents
        JLayeredPane contentPane = new JLayeredPane();
        BackgroundComponent background = new BackgroundComponent();
        DungeonButton startButton = new DungeonButton(DungeonButtonType.START_BUTTON);
        DungeonButton exitButton = new DungeonButton(DungeonButtonType.EXIT_BUTTON);

        // Adds listeners
        startButton.addButtonClickListener(new StartButtonListener());
        exitButton.addButtonClickListener(new ExitButtonListener());

        // Modifies the JFrame
        this.setPreferredSize(new Dimension(Constants.START_MENU_WIDTH, Constants.START_MENU_HEIGHT));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Adds JComponents to the frame
        this.add(contentPane, BorderLayout.CENTER);

        /*
         * Modifies the JComponents
         */
        // Content pane
        contentPane.setBounds(0, 0, Constants.START_MENU_WIDTH, Constants.START_MENU_HEIGHT);

        // Background
        background.setBounds(0, 0, Constants.START_MENU_WIDTH, Constants.START_MENU_HEIGHT);
        background.setOpaque(true);

        // Start button
        startButton.setBounds(Constants.MENU_START_BUTTON_X_CORD, Constants.MENU_START_BUTTON_Y_CORD, Constants.DUNGEON_BUTTON_WIDTH, Constants.DUNGEON_BUTTON_HEIGHT);
        startButton.setOpaque(true);

        // Exit button
        exitButton.setBounds(Constants.MENU_EXIT_BUTTON_X_CORD, Constants.MENU_EXIT_BUTTON_Y_CORD, Constants.DUNGEON_BUTTON_WIDTH, Constants.DUNGEON_BUTTON_HEIGHT);
        exitButton.setOpaque(true);

        // Adds components to the content pane
        contentPane.add(background, 0, 0);
        contentPane.add(startButton, 1, 0);
        contentPane.add(exitButton, 1, 0);

        // Makes the frame show up correctly
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * The listener for the start button
     *
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonButton
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener
     */
    private class StartButtonListener implements DungeonButtonListener {
        @Override
        public void onButtonClick() {
            menuFrame.setVisible(false);
            startGame();
        }
    }

    /**
     * The listener for the exit button
     *
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonButton
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.DungeonButtonListener
     */
    private class ExitButtonListener implements DungeonButtonListener {
        @Override
        public void onButtonClick() {
            System.exit(0);
        }
    }

    /**
     * Starts the game by setting running to true,
     * resetting the level and score,
     * creating a new board and adding the character
     *
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     */
    public void startGame() {
        running = true;
        board = new Board();
        board.setScore(0);
        board.setLevel(1);
        currentRoom = board.getCurrentRoom();

        // Creates the playerCharacter and adds it to the board
        playerCharacter = new PlayerCharacter();
        currentRoom.addCharacter(playerCharacter);

        // Makes the frame or replaces its content if it already exists
        if (frame == null) {
            frame = new DungeonFrame(this, board);
        } else {
            frame.makeFrameContent(board);
        }

        // Prints a text view of the empty board
        System.out.print(board);

        // Starts the game loop
        gameLoop();
    }

    /**
     * Stops the game by stopping all the
     * timers and calling the game over
     * method in the DungeonFrame class
     *
     * @see se.liu.ida.josfa969.dungeonsv2.gui_components.DungeonFrame
     */
    private void stopGame() {
        objectTimer.stop();
        projectileTimer.stop();
        wallCollisionCheckTimer.stop();
        deathCheckTimer.stop();
        frame.gameOver();
    }

    /**
     * Starts the main game loop. The game loop consists
     * of several smaller loops each of which have a specific task.
     * <p/>
     * The loops are:
     * <br>
     * updateObjects - Updates all Dungeon Objects and checks for collisions with each other
     * <br>
     * updateProjectiles - Updates all projectiles and checks for collisions with Dungeon Objects
     * <br>
     * wallCollisionCheck - Checks if the player is colliding with a wall
     * <br>
     * deathCheck - Checks if the player is dead
     *
     * @see java.util.Timer
     * @see javax.swing.Action
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.TickActionMethods
     */
    private void gameLoop() {
        // This action updates all the Dungeon Objects one step
        final Action updateObjects = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieves required data
                    currentRoom = board.getCurrentRoom();
                    Rectangle characterBox = playerCharacter.getCollisionBox();

                    TickActionMethods.updateNPCObjects(board);

                    // Check if the playerCharacter is entering a door
                    for (Door door : currentRoom.getDoors()) {
                        if (characterBox.intersects(door.getCollisionBox())) {
                            board.switchRoom(door);
                        }
                    }

                    currentRoom.notifyListeners();

                } catch (ConcurrentModificationException ignored) {
                    TickActionMethods.concurrentModificationException();
                }
            }
        };

        // Update all the projectiles one step
        final Action updateProjectiles = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieves required data
                    currentRoom = board.getCurrentRoom();
                    Rectangle roomBox = currentRoom.getCollisionBox();
                    List<DungeonObject> objects = currentRoom.getObjects();
                    Iterable<Projectile> projectiles = playerCharacter.getProjectiles();

                    // Defines new variables used for detecting the projectile's collisions
                    List<DungeonObject> shootingEnemies = new ArrayList<>();
                    List<Projectile> enemyProjectiles = new ArrayList<>();
                    List<Projectile> removedProjectiles = new ArrayList<>();

                    // Fill the shootingEnemies- and enemyProjectiles-Lists
                    TickActionMethods.getShootingEnemies(objects, shootingEnemies, enemyProjectiles);

                    // Update all the playerCharacter's projectiles
                    TickActionMethods.updateProjectiles(objects, projectiles, removedProjectiles, roomBox);

                    // Update all enemy projectiles
                    TickActionMethods.updateProjectiles(objects, enemyProjectiles, removedProjectiles, roomBox);

                    // Removes the projectiles which hit a target
                    for (Projectile projectile : removedProjectiles) {
                        playerCharacter.removeProjectile(projectile);
                        for (DungeonObject object : shootingEnemies) {
                            ((ProjectileHandler) object).removeProjectile(projectile);
                        }
                    }

                    currentRoom.notifyListeners();
                } catch (ConcurrentModificationException ignored) {
                    TickActionMethods.concurrentModificationException();
                }
            }
        };

        final Action wallCollisionCheck = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieves required data
                    currentRoom = board.getCurrentRoom();
                    Rectangle roomBox = currentRoom.getCollisionBox();
                    Rectangle characterBox = playerCharacter.getCollisionBox();

                    // Check if the character is colliding with the walls
                    TickActionMethods.checkWallCollision(playerCharacter, roomBox, characterBox);

                    currentRoom.notifyListeners();
                } catch (ConcurrentModificationException ignored) {
                    TickActionMethods.concurrentModificationException();
                }
            }
        };

        // Check if the character has died
        final Action deathCheck = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    // Ends the game if the character is dead
                    if (playerCharacter.getHealth() <= 0) {
                        running = false;
                        currentRoom.notifyListeners();
                    }
                } else {
                    // Stop the game if the running flag is set to false
                    stopGame();
                }
            }
        };

        // Set up and start the game loop
        objectTimer = new Timer(Constants.GAME_UPDATE_RATE, updateObjects);
        projectileTimer = new Timer(Constants.GAME_UPDATE_RATE, updateProjectiles);
        wallCollisionCheckTimer = new Timer(Constants.GAME_UPDATE_RATE, wallCollisionCheck);
        deathCheckTimer = new Timer(Constants.GAME_UPDATE_RATE, deathCheck);

        objectTimer.setCoalesce(true);
        projectileTimer.setCoalesce(true);
        wallCollisionCheckTimer.setCoalesce(true);
        deathCheckTimer.setCoalesce(true);

        objectTimer.start();
        projectileTimer.start();
        wallCollisionCheckTimer.start();
        deathCheckTimer.start();
    }

}
