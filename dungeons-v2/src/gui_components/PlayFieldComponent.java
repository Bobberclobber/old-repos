package se.liu.ida.josfa969.dungeonsv2.gui_components;

import se.liu.ida.josfa969.dungeonsv2.Board;
import se.liu.ida.josfa969.dungeonsv2.character_actions.*;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.*;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.*;
import se.liu.ida.josfa969.dungeonsv2.enums.*;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;
import se.liu.ida.josfa969.dungeonsv2.help_classes.DungeonMaps;
import se.liu.ida.josfa969.dungeonsv2.threads.CharacterActionThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.AbstractMap;
import java.util.ConcurrentModificationException;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * A JComponent which paints the current room and all its inhabitants
 * <p/>
 * The play field is updated every tick and every time
 * the player does something or the room is switched
 * <p/>
 * The Play Field implements the Key Listener interface
 * in order to allow the player to control the character
 *
 * @see javax.swing.JComponent
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.BoardListener
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.RoomListener
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ObjectListener
 * @see java.awt.event.KeyListener
 */
class PlayFieldComponent extends JComponent implements BoardListener, RoomListener, ObjectListener, KeyListener {

    private final Board board;
    private Room currentRoom;
    private final PlayerCharacter playerCharacter;
    private CharacterType type;

    private CharacterActionThread moveUpThread = null;
    private CharacterActionThread moveDownThread = null;
    private CharacterActionThread moveLeftThread = null;
    private CharacterActionThread moveRightThread = null;
    private CharacterActionThread shootThread = null;

    private final AbstractMap<RoomType, Color> roomColorMap = DungeonMaps.getRoomColorMap();
    private final AbstractMap<CharacterType, Color> characterColorMap = DungeonMaps.getCharacterColorMap();
    private final AbstractMap<ProjectileType, Color> projectileColorMap = DungeonMaps.getProjectileColorMap();
    private final AbstractMap<EnemyType, Color> enemyColorMap = DungeonMaps.getEnemyColorMap();

    PlayFieldComponent(Board board) {
        this.board = board;
        this.currentRoom = board.getCurrentRoom();
        this.playerCharacter = currentRoom.getPlayerCharacter();
        this.type = playerCharacter.getCharacterType();
    }

    @Override
    public void boardChanged() {
        System.out.println(board);
        repaint();
    }

    @Override
    public void objectChanged() {
        repaint();
    }

    @Override
    public void roomChanged() {
        repaint();
    }

    // Warning suppressed since it is either a false positive or irrelevant
    @SuppressWarnings({"RefusedBequest", "SuppressionAnnotation"})
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Starts a Character Action Thread for either moving
     * or shooting depending on which button is pressed
     *
     * @param e A KeyEvent object used to
     *          check which key is pressed
     * @see java.awt.event.KeyEvent
     * @see se.liu.ida.josfa969.dungeonsv2.threads.CharacterActionThread
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction
     * @see se.liu.ida.josfa969.dungeonsv2.character_actions.MoveAction
     * @see se.liu.ida.josfa969.dungeonsv2.character_actions.ShootAction
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

            // Movement keys
            case KeyEvent.VK_W:
                moveCharacterUp();
                break;
            case KeyEvent.VK_A:
                moveCharacterLeft();
                break;
            case KeyEvent.VK_S:
                moveCharacterDown();
                break;
            case KeyEvent.VK_D:
                moveCharacterRight();
                break;

            // Shooting keys
            case KeyEvent.VK_UP:
                shoot(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                shoot(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                shoot(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                shoot(Direction.RIGHT);
                break;
        }
    }

    // Make the player character fire a shot in the given direction
    private void shoot(Direction direction) {
        if (shootThread == null || shootThread.isFinished()) {
            shootThread = new CharacterActionThread("ShootThread");
            shootThread.setCharacterAction(new ShootAction(playerCharacter, direction));
            shootThread.start();
        }
    }

    private void moveCharacterRight() {
        if (moveRightThread == null || moveRightThread.isFinished()) {
            if (moveLeftThread != null) {
                moveLeftThread.stop();
            }
            moveRightThread = new CharacterActionThread("MoveRightThread");
            moveRightThread.setCharacterAction(new MoveAction(playerCharacter, Direction.RIGHT));
            moveRightThread.start();
        }
    }

    private void moveCharacterDown() {
        if (moveDownThread == null || moveDownThread.isFinished()) {
            if (moveUpThread != null) {
                moveUpThread.stop();
            }
            moveDownThread = new CharacterActionThread("MoveDownThread");
            moveDownThread.setCharacterAction(new MoveAction(playerCharacter, Direction.DOWN));
            moveDownThread.start();
        }
    }

    private void moveCharacterLeft() {
        if (moveLeftThread == null || moveLeftThread.isFinished()) {
            if (moveRightThread != null) {
                moveRightThread.stop();
            }
            moveLeftThread = new CharacterActionThread("MoveLeftThread");
            moveLeftThread.setCharacterAction(new MoveAction(playerCharacter, Direction.LEFT));
            moveLeftThread.start();
        }
    }

    private void moveCharacterUp() {
        if (moveUpThread == null || moveUpThread.isFinished()) {
            if (moveDownThread != null) {
                moveDownThread.stop();
            }
            moveUpThread = new CharacterActionThread("MoveUpThread");
            moveUpThread.setCharacterAction(new MoveAction(playerCharacter, Direction.UP));
            moveUpThread.start();
        }
    }

    /**
     * Stops the given movement thread if any of the movement keys are released
     * Stops all shooting threads if any of the shooting keys are released
     *
     * @param e A KeyEvent object used to
     *          check which key is pressed
     * @see java.awt.event.KeyEvent
     * @see se.liu.ida.josfa969.dungeonsv2.threads.CharacterActionThread
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                moveUpThread.stop();
                break;
            case KeyEvent.VK_A:
                moveLeftThread.stop();
                break;
            case KeyEvent.VK_S:
                moveDownThread.stop();
                break;
            case KeyEvent.VK_D:
                moveRightThread.stop();
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                shootThread.stop();
                break;
        }
    }

    /**
     * Paints the current room and all its
     * inhabitants in the middle of the frame
     *
     * @param g A Graphics object used to
     *          paint the graphical representations
     *          of the room and all its inhabitants
     * @see java.awt.Graphics
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Retrieves required values
        currentRoom = board.getCurrentRoom();
        type = playerCharacter.getCharacterType();
        Iterable<Door> doors = currentRoom.getDoors();
        Iterable<Projectile> projectiles = playerCharacter.getProjectiles();
        Iterable<DungeonObject> objects = currentRoom.getObjects();

        // Clears the background of the component
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Paints the current room
        paintCurrentRoom(g2d);

        // Paints the doors
        paintDoors(doors, g2d);

        // Paints the playerCharacter's projectiles
        paintProjectiles(projectiles, g2d);

        // Paints the current room's population (this includes the playerCharacter as well as stationary objects)
        paintPopulation(objects, g2d);
    }

    /**
     * Loops through the given Iterable containing
     * DungeonObjects and uses one of the Maps
     * (depending on if the object is an enemy or a
     * player character) from the DungeonMaps class
     * to determine which color each object should be
     *
     * @param objects An Iterable containing the
     *                DungeonObjects of the current room
     * @param g2d A Graphics2D object used to paint the
     *            DungeonObjects in the objects Iterable
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.RoomPopulations
     * @see java.awt.Graphics2D
     */
    private void paintPopulation(Iterable<DungeonObject> objects, Graphics2D g2d) {
        for (DungeonObject object : objects) {
            if (object instanceof NonPlayerControlled && ((NonPlayerControlled) object).isEnemy()) {
                g2d.setColor(enemyColorMap.get(object.getEnemyType()));
                g2d.fillRect(object.getXCord(), object.getYCord(), object.getWidth(), object.getHeight());
                // If the enemy is a Shooting Enemy, paint its projectiles
                if (object.getClass() == ShootingEnemy.class)
                {
                    // Warning suppressed since removing or replacing the cast results in an error
                    // Code maturity issues ignored since no good solution exists for them
                    //noinspection CastConflictsWithInstanceof
                    paintProjectiles(((ProjectileHandler) object).getProjectiles(), g2d);
                }
            } else if (object.getClass() == PlayerCharacter.class) {
                g2d.setColor(characterColorMap.get(type));
                g2d.fillOval(object.getXCord(), object.getYCord(), object.getWidth(), object.getHeight());
            } else if (object.getClass() == TrapDoor.class) {
                g2d.setColor(Color.BLUE);
                g2d.fillRect(object.getXCord(), object.getYCord(), object.getWidth(), object.getHeight());
            }
        }
    }

    /**
     * Loops through an Iterable containing
     * all the doors of the current room
     *
     * @param doors An Iterable containing the current room's doors
     * @param g2d A Graphics2D object used to paint the
     *            Doors in the doors Iterable
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     * @see java.awt.Graphics2D
     */
    private void paintDoors(Iterable<Door> doors, Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        for (Door door : doors) {
            g2d.fillRect(door.getXCord(), door.getYCord(), door.getWidth(), door.getHeight());
        }
    }

    private void paintCurrentRoom(Graphics2D g2d) {
        g2d.setColor(roomColorMap.get(currentRoom.getType()));
        g2d.fill(currentRoom.getCollisionBox());
    }

    /**
     * Paints the projectiles contained in the given Iterable
     *
     * @param projectiles An Iterable containing all projectiles
     *                    fired by DungeonObjects in the current Room
     * @param g2d A Graphics2D object used to paint the
     *            Projectiles in the projectiles Iterable
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.DungeonMaps
     * @see java.awt.Graphics2D
     */
    private void paintProjectiles(Iterable<Projectile> projectiles, Graphics2D g2d) {
        try {
            for (Projectile projectile : projectiles) {
                g2d.setColor(projectileColorMap.get(projectile.getProjectileType()));
                g2d.fillOval(projectile.getXCord(), projectile.getYCord(), projectile.getWidth(), projectile.getHeight());
            }
        } catch (ConcurrentModificationException ignored) {
            System.out.println("<------------------>\nConcurrent Modification Exception\n<------------------>");
        }
    }
}
