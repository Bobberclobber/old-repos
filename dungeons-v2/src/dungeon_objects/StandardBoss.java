package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.enums.EnemyType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import java.awt.*;

/**
 * Created by Josef on 2014-03-31.
 * <p/>
 * A Standard Boss Dungeon Object
 * <p/>
 * A Standard Boss is represented by a big,
 * orange rectangle which follows the player
 * <p/>
 * The Player is awarded five points for killing it
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled
 */
public class StandardBoss extends DungeonObject implements NonPlayerControlled {
    private int upSpeed;
    private int downSpeed;
    private int leftSpeed;
    private int rightSpeed;
    private int nextXCord;
    private int nextYCord;

    /**
     * Create a StandardBoss in the middle of the room
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.enums.EnemyType
     */
    public StandardBoss() {
        super(Constants.ROOM_MIDDLE_X, Constants.ROOM_MIDDLE_Y, Constants.BOSS_STANDARD_WIDTH, Constants.BOSS_STANDARD_HEIGHT, Constants.BOSS_STANDARD_HEALTH, EnemyType.STANDARD_BOSS);

        upSpeed = Constants.BOSS_STANDARD_SPEED;
        downSpeed = Constants.BOSS_STANDARD_SPEED;
        leftSpeed = Constants.BOSS_STANDARD_SPEED;
        rightSpeed = Constants.BOSS_STANDARD_SPEED;

        nextXCord = -1;
        nextYCord = -1;
    }

    public void setNextXCord(int nextXCord) {
        this.nextXCord = nextXCord;
    }

    public void setNextYCord(int nextYCord) {
        this.nextYCord = nextYCord;
    }

    /**
     * Moves the boss by calculating the difference
     * between the StandardBoss' current coordinates
     * and the PlayerCharacter's coordinates (the player's
     * coordinates are passed in as nextXCord/nextYCord in
     * the main game loop) and moves the Boss up/down and
     * right/left depending on the difference
     *
     * @see se.liu.ida.josfa969.dungeonsv2.DungeonMain
     */
    @Override
    public void move() {
        // Makes the boss move towards nextXCord and nextYCord
        int difX = getMiddleXCord() - nextXCord;
        int difY = getMiddleYCord() - nextYCord;

        // Moves the boss towards the target coordinate
        // A slight margin is added to avoid oscillation
        // Moves the boss along the x-axis
        if (difX > 5) {
            xCord -= leftSpeed;
        } else if (difX < -5) {
            xCord += rightSpeed;
        }

        // Moves the boss along the y-axis
        if (difY > 5) {
            yCord -= upSpeed;
        } else if (difY < -5) {
            yCord += downSpeed;
        }
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

    @Override
    public int getUpSpeed() {
        return upSpeed;
    }

    @Override
    public int getDownSpeed() {
        return downSpeed;
    }

    @Override
    public int getLeftSpeed() {
        return leftSpeed;
    }

    @Override
    public int getRightSpeed() {
        return rightSpeed;
    }

    @Override
    public void setUpSpeed(int upSpeed) {
        this.upSpeed = upSpeed;
    }

    @Override
    public void setDownSpeed(int downSpeed) {
        this.downSpeed = downSpeed;
    }

    @Override
    public void setLeftSpeed(int leftSpeed) {
        this.leftSpeed = leftSpeed;
    }

    @Override
    public void setRightSpeed(int rightSpeed) {
        this.rightSpeed = rightSpeed;
    }

    @Override
    public boolean isPlayerControlled() {
        return false;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public Rectangle getCollisionBox() {
        return new Rectangle(xCord, yCord, width, height);
    }

    /**
     * On collision with STANDARD_PROJECTILE-type
     * Projectile, health is reduced by one
     * <br>
     * On collision with wall (i.e if the Boss itself is
     * passed as parameter) the speed corresponding to
     * whatever side is colliding with the wall is set to 0
     *
     * @param otherObject The DungeonObject which this StandardBoss is colliding with
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     */
    @Override
    public void onCollision(DungeonObject otherObject) {
        if (otherObject.getClass() == Projectile.class) {
            health -= 1;
        }
        // If the boss is colliding with the wall, the boss-object itself is passed as the parameter
            if (this.equals(otherObject)) {
            // If the boss is colliding with the right wall
            if (getMiddleXCord() + width / 2 >= Constants.ROOM_WIDTH) {
                rightSpeed = 0;
            } else {
                rightSpeed = Constants.BOSS_STANDARD_SPEED;
            }

            // If the boss is colliding with the left wall
            if (getMiddleXCord() - width / 2 <= 0) {
                leftSpeed = 0;
            } else {
                leftSpeed = Constants.BOSS_STANDARD_SPEED;
            }

            // If the boss is colliding with the lower wall
            if (getMiddleYCord() + height / 2 >= Constants.ROOM_HEIGHT) {
                downSpeed = 0;
            } else {
                downSpeed = Constants.BOSS_STANDARD_SPEED;
            }

            // If the boss is colliding with the upper wall
            if (getMiddleYCord() - height / 2 <= 0) {
                upSpeed = 0;
            } else {
                upSpeed = Constants.BOSS_STANDARD_SPEED;
            }
        }
    }
}
