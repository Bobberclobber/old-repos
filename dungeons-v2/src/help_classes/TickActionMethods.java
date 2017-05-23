package se.liu.ida.josfa969.dungeonsv2.help_classes;

import se.liu.ida.josfa969.dungeonsv2.Board;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Mobile;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ProjectileHandler;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.Room;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.*;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;

import java.awt.*;
import java.util.List;

/**
 * Created by Josef on 2014-04-16.
 * <p/>
 * This class contains all the methods used by the
 * different Actions which the main game loop consists of
 * <p/>
 * These methods almost exclusively handle functionality
 * such as collision detection and movement
 */
public final class  TickActionMethods {

    /**
     * An object of a class used to handle collisions between objects.
     * When created, this class takes a Solid object as parameter,
     * the method executeCollisionHandling can then be executed with
     * a DungeonObject as the parameter
     */
    private static CollisionHandler collisionHandler = null;

    private TickActionMethods() {
    }

    /*
     * Methods used by the "updateObjects"-Action
     */

    /**
     * Update the given Array List of Dungeon Objects by looping
     * through it and for each Dungeon Object doing the following:
     * <br>
     *     1. If it is dead, increase the score and remove the object
     * <br>
     *     2. If the object is a StandardBoss, calculate its
     *     new coordinates and check for wall-collisions
     * <br>
     *     3. Move the object if it is mobile
     * <br>
     *     4. Check if the object is colliding with any other DungeonObject
     *
     * @param board The current board
     * @see se.liu.ida.josfa969.dungeonsv2.Board
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.CollisionHandler
     */
    public static void updateNPCObjects(Board board) {
        // Retrieve the required values
        Room currentRoom = board.getCurrentRoom();
        Rectangle roomBox = currentRoom.getCollisionBox();
        DungeonObject playerCharacter = currentRoom.getPlayerCharacter();
        List<DungeonObject> objects = currentRoom.getObjects();

        for (DungeonObject object : objects) {

            // Actions to take for all NPC Dungeon Objects
            if (!object.isPlayerControlled() && object.getClass() != TrapDoor.class) {
                // Checks if the Dungeon Object is dead/destroyed
                if (object.isDead()) {
                    if (object.getClass() == StandardBoss.class) {
                        board.increaseScoreBy(5);
                    } else {
                        board.increaseScoreBy(1);
                    }
                    currentRoom.removeObject(object);
                    board.notifyListeners();
                }
                // Sets the boss's target coordinates,
                // in this case the playerCharacter's current coordinates
                // This will make the boss chase the playerCharacter
                if (object.getClass() == StandardBoss.class) {
                    ((StandardBoss) object).setNextXCord(playerCharacter.getMiddleXCord());
                    ((StandardBoss) object).setNextYCord(playerCharacter.getMiddleYCord());

                    // Checks if the boss is colliding with the wall
                    if (((Solid) object).getCollisionBox().intersects(roomBox)) {
                        collisionHandler = new CollisionHandler(((Solid) object));
                        collisionHandler.executeCollisionHandling(object);
                    }
                }
                ((NonPlayerControlled) object).move();
            }

            // Loops through every other object and checks if they are colliding
            for (DungeonObject otherObject : objects) {
                // This check makes sure that the two objects are solid and that they
                // haven't been compared against each other before
                if ((objects.indexOf(otherObject) > objects.indexOf(object)) && object.isSolid() && otherObject.isSolid()) {
                    Rectangle objectBox = ((Solid) object).getCollisionBox();
                    Rectangle otherObjectBox = ((Solid) otherObject).getCollisionBox();
                    if (objectBox.intersects(otherObjectBox)) {
                        collisionHandler = new CollisionHandler(((Solid) object));
                        collisionHandler.executeCollisionHandling(otherObject);
                        collisionHandler = new CollisionHandler(((Solid) otherObject));
                        collisionHandler.executeCollisionHandling(object);
                        if (otherObject.getClass() == PlayerCharacter.class && object.getClass() == TrapDoor.class) {
                            board.switchLevel();
                        }
                    }
                }
            }
        }
    }

    /*
     * Methods used by the "updateProjectiles"-Action
     */

    /**
     * Takes a list of DungeonObjects and loops through it.
     * Every time a DungeonObject which is able to shoot
     * is encountered it is added to a List and its Projectiles to another
     *
     * @param objects The List of DungeonObjects which the method iterates over
     * @param shootingEnemies The List which the shooting enemies are added to
     * @param enemyProjectiles The List which the Projectiles are added to
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     */
    // Retrieve a list of all enemies capable of shooting
    public static void getShootingEnemies(List<DungeonObject> objects, List<DungeonObject> shootingEnemies, List<Projectile> enemyProjectiles) {
        for (DungeonObject object : objects) {
            if (object.getClass() == ShootingEnemy.class) {
                // Adds every shooting enemy to a list
                shootingEnemies.add(object);
                // Adds every shooting enemy's projectiles to a single list
                enemyProjectiles.addAll(((ProjectileHandler) object).getProjectiles());
            }
        }
    }

    /**
     * Update the given Array List of Projectiles by looping
     * through it and for each Projectile doing the following:
     * <br>
     *     1. Move the Projectile
     * <br>
     *     2. Check if the Projectile is colliding any of the
     *     DungeonObjects and calls either enemyProjectileHit
     *     or playerProjectileHit depending on if the Projectile
     *     was fired by the player or by an enemy
     * <br>
     *     3. Check if the Projectile is colliding with a wall
     *
     * @param objects An Iterable containing all DungeonObjects in the current room
     * @param projectiles An Iterable containing the Projectiles which are to be updated
     * @param removedProjectiles A List to which all projectiles which are to be removed are added
     * @param roomBox A Rectangle representing the walls of the current room
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see java.awt.Rectangle
     */
    public static void updateProjectiles(Iterable<DungeonObject> objects, Iterable<Projectile> projectiles, List<Projectile> removedProjectiles, Rectangle roomBox) {
        for (Projectile projectile : projectiles) {
            projectile.move(projectile.getDirection());
            Rectangle projectileCollisionBox = projectile.getCollisionBox();

            // Loops through every other object present in the room
            for (DungeonObject object : objects) {
                if (projectile.getProjectileType() == ProjectileType.ENEMY_PROJECTILE) {
                    enemyProjectileHit(removedProjectiles, projectile, projectileCollisionBox, object);
                } else {
                    playerProjectileHit(projectile, projectileCollisionBox, object, removedProjectiles);
                }
            }

            // Checks if the projectile is hitting the wall
            if (!roomBox.contains(projectileCollisionBox)) {
                removedProjectiles.add(projectile);
            }
        }
    }

    /**
     * Checks if the given projectile fired by an enemy
     * is colliding with the given object. If it is,
     * the object's collision handling is executed and
     * the projectile is added to the removedProjectiles List
     *
     * @param removedProjectiles A List to which the projectile will
     *                           be added if it is colliding with the object
     * @param projectile The Projectile which is to be examined
     * @param projectileCollisionBox A Rectangle, representing the projectile,
     *                               which is used to detect collisions
     * @param object The DungeonObject which the method is to
     *               check if the Projectile is colliding with
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.CollisionHandler
     * @see java.awt.Rectangle
     */
    public static void enemyProjectileHit(List<Projectile> removedProjectiles, Projectile projectile, Rectangle projectileCollisionBox, DungeonObject object) {
        if (object.isPlayerControlled() && object.isSolid()) {
            Rectangle otherCollisionBox = ((Solid) object).getCollisionBox();
            // Checks if the projectile hits the other object
            if (projectileCollisionBox.intersects(otherCollisionBox)) {
                System.out.println("Enemy shot hit");
                collisionHandler = new CollisionHandler(((Solid) object));
                collisionHandler.executeCollisionHandling(projectile);
                removedProjectiles.add(projectile);
            }
        }
    }

    /**
     * Checks if the given projectile fired by the player
     * is colliding with the given object. If it is,
     * the object's collision handling is executed and
     * the projectile is added to the removedProjectiles List
     *
     * @param removedProjectiles A List to which the projectile will
     *                           be added if it is colliding with the object
     * @param projectile The Projectile which is to be examined
     * @param projectileCollisionBox A Rectangle, representing the projectile,
     *                               which is used to detect collisions
     * @param object The DungeonObject which the method is to
     *               check if the Projectile is colliding with
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.CollisionHandler
     * @see java.awt.Rectangle
     */
    public static void playerProjectileHit(Projectile projectile, Rectangle projectileCollisionBox, DungeonObject object, List<Projectile> removedProjectiles) {
        if (!object.isPlayerControlled() && object.isSolid() && object.getClass() != TrapDoor.class) {
            Rectangle otherCollisionBox = ((Solid) object).getCollisionBox();
            // Checks if the projectile hits the other object
            if (projectileCollisionBox.intersects(otherCollisionBox)) {
                System.out.println("Shot hit");
                collisionHandler = new CollisionHandler(((Solid) object));
                collisionHandler.executeCollisionHandling(projectile);
                removedProjectiles.add(projectile);
            }
        }
    }

    /*
     * Methods used by the "wallCollisionCheck"-Action
     */

    /**
     * Uses the checkSide-method to check each of
     * the character's side for collisions with the wall
     *
     * @param playerCharacter The PlayerCharacter whose sides
     *                        are to be checked for collisions with the walls
     * @param roomBox A Rectangle representing the walls of the current room
     * @param characterBox A Rectangle, representing the character,
     *                     which is used to detect collisions
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     * @see java.awt.Rectangle
     */
    public static void checkWallCollision(DungeonObject playerCharacter, Rectangle roomBox, Rectangle characterBox) {
        // Upper side
        checkSide(playerCharacter, characterBox, Direction.UP, roomBox);
        // Lower side
        checkSide(playerCharacter, characterBox, Direction.DOWN, roomBox);
        // Left side
        checkSide(playerCharacter, characterBox, Direction.LEFT, roomBox);
        // Right side
        checkSide(playerCharacter, characterBox, Direction.RIGHT, roomBox);
    }

    /**
     * Depending on which Direction is passed in,
     * this method checks if the player is colliding
     * with a wall by checking if the roomBox
     * contains the middle point of whichever
     * side corresponds to the Direction
     * <br>
     * If any of the sides are colliding with a wall
     * the corresponding speed is set to 0, and then
     * to its regular value again once the side isn't
     * colliding anymore
     *
     * @param playerCharacter The PlayerCharacter whose side
     *                        is to be checked for collisions with the walls
     * @param characterBox A Rectangle, representing the character,
     *                     which is used to detect collisions
     * @param side A Direction indicating which side should be checked for collisions
     * @param roomBox A Rectangle representing the walls of the current room
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     * @see java.awt.Rectangle
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     */
    public static void checkSide(DungeonObject playerCharacter, Rectangle characterBox, Direction side, Rectangle roomBox) {
        int characterCenterX = (int) characterBox.getCenterX();
        int characterCenterY = (int) characterBox.getCenterY();
        int halfCharacterWidth = Constants.CHARACTER_WIDTH / 2;
        int halfCharacterHeight = Constants.CHARACTER_HEIGHT / 2;

        // Defines the four middle points on each of the playerCharacter's sides
        Point upperCharacterPoint = new Point(characterCenterX, characterCenterY - halfCharacterHeight - ((Mobile) playerCharacter).getUpSpeed());
        Point lowerCharacterPoint = new Point(characterCenterX, characterCenterY + halfCharacterHeight + ((Mobile) playerCharacter).getDownSpeed());
        Point leftCharacterPoint = new Point(characterCenterX - halfCharacterWidth - ((Mobile) playerCharacter).getLeftSpeed(), characterCenterY);
        Point rightCharacterPoint = new Point(characterCenterX + halfCharacterWidth + ((Mobile) playerCharacter).getRightSpeed(), characterCenterY);

        switch (side) {
            case LEFT:
                if (!roomBox.contains(leftCharacterPoint)) {
                    ((Mobile) playerCharacter).setLeftSpeed(0);
                } else {
                    ((Mobile) playerCharacter).setLeftSpeed(Constants.CHARACTER_STANDARD_SPEED);
                }
                break;

            case RIGHT:
                if (!roomBox.contains(rightCharacterPoint)) {
                    ((Mobile) playerCharacter).setRightSpeed(0);
                } else {
                    ((Mobile) playerCharacter).setRightSpeed(Constants.CHARACTER_STANDARD_SPEED);
                }
                break;

            case UP:
                if (!roomBox.contains(upperCharacterPoint)) {
                    ((Mobile) playerCharacter).setUpSpeed(0);
                } else {
                    ((Mobile) playerCharacter).setUpSpeed(Constants.CHARACTER_STANDARD_SPEED);
                }
                break;

            case DOWN:
                if (!roomBox.contains(lowerCharacterPoint)) {
                    ((Mobile) playerCharacter).setDownSpeed(0);
                } else {
                    ((Mobile) playerCharacter).setDownSpeed(Constants.CHARACTER_STANDARD_SPEED);
                }
                break;
        }
    }

    public static void concurrentModificationException() {
        System.out.println("<------------------>\nConcurrent Modification Exception\n<------------------>");
    }
}
