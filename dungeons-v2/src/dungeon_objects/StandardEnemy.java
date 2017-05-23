package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.enums.EnemyType;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import java.awt.*;

/**
 * Created by Josef on 2014-03-18.
 * <p/>
 * A Standard Enemy Dungeon Object
 * <p/>
 * A Standard Enemy is represented by a small, black
 * rectangle which moves around in the room at random
 * <p/>
 * The player is awarded one point for killing it
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled
 */
public class StandardEnemy extends DungeonObject implements NonPlayerControlled {

    private int upSpeed;
    private int downSpeed;
    private int leftSpeed;
    private int rightSpeed;
    private int nextXCord;
    private int nextYCord;

    /**
     * Creates a StandardEnemy at the given coordinates
     *
     * @param xCord The x-coordinate at which the StandardEnemy is to be created
     * @param yCord The y-coordinate at which the StandardEnemy is to be created
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.enums.EnemyType
     */
    public StandardEnemy(int xCord, int yCord) {
        super(xCord, yCord, Constants.ENEMY_STANDARD_WIDTH, Constants.ENEMY_STANDARD_HEIGHT, Constants.ENEMY_STANDARD_HEALTH, EnemyType.STANDARD_ENEMY);

        upSpeed = Constants.ENEMY_STANDARD_SPEED;
        downSpeed = Constants.ENEMY_STANDARD_SPEED;
        leftSpeed = Constants.ENEMY_STANDARD_SPEED;
        rightSpeed = Constants.ENEMY_STANDARD_SPEED;
        nextXCord = -1;
        nextYCord = -1;
    }

    @Override
    public int getUpSpeed() {
        return upSpeed;
    }

    @Override
    public boolean isEnemy() {
        return true;
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

    /**
     * Randomizes a coordinate which the StandardEnemy will then move towards.
     * When the coordinate is reached, a new one is randomized
     */
    @Override
    public void move() {
        int lowerXMargin = nextXCord - Constants.ENEMY_STANDARD_SPEED;
        int upperXMargin = nextXCord + Constants.ENEMY_STANDARD_SPEED;

        int lowerYMargin = nextYCord - Constants.ENEMY_STANDARD_SPEED;
        int upperYMargin = nextYCord + Constants.ENEMY_STANDARD_SPEED;

        boolean nextXReached = (upperXMargin > xCord) && (xCord > lowerXMargin);
        boolean nextYReached = (upperYMargin > yCord) && (yCord > lowerYMargin);
        boolean nextPosReached = nextXReached && nextYReached;

        // Check if the first target is to be calculated
        boolean firstPos = (nextXCord == -1) && (nextYCord == -1);

        if (nextPosReached || firstPos) {
            calculateNextTarget();
        }

        // Changes the StandardEnemy's position based on where the target is
        if (xCord >= upperXMargin) {
            xCord -= Constants.ENEMY_STANDARD_SPEED;
        } else if (xCord <= lowerXMargin) {
            xCord += Constants.ENEMY_STANDARD_SPEED;
        }

        if (yCord >= upperYMargin) {
            yCord -= Constants.ENEMY_STANDARD_SPEED;
        } else if (yCord <= lowerYMargin) {
            yCord += Constants.ENEMY_STANDARD_SPEED;
        }

    }

    /**
     * Calculates a new coordinate such that
     * the StandardEnemy is always within the room's limits
     */
    private void calculateNextTarget() {
        int minX = 0;
        int maxX = Constants.ROOM_WIDTH - width;
        int rangeX = (maxX - minX) + 1;

        int minY = 0;
        int maxY = Constants.ROOM_HEIGHT - height;
        int rangeY = (maxY - minY) + 1;

        nextXCord = (int) (Math.random() * rangeX) + minX;
        nextYCord = (int) (Math.random() * rangeY) + minY;
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
        return new Rectangle(getXCord(), getYCord(), getWidth(), getHeight());
    }

    /**
     * On collision with a STANDARD_PROJECTILE-type Projectile, health is decreased by one
     * <br>
     * On collision with another enemy, a new random coordinate is randomized. If the two
     * DungeonObjects are tangle (i.e they intersect each other to such a degree that they
     * might get stuck together), this StandardEnemy's position is adjusted to compensate for this
     *
     * @param otherObject The DungeonObject which this StandardEnemy is colliding with
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     */
    @Override
    public void onCollision(DungeonObject otherObject) {
        if (otherObject.getClass() == Projectile.class && otherObject.getProjectileType() == ProjectileType.STANDARD_PROJECTILE) {
            health -= 1;
        } else if (otherObject instanceof NonPlayerControlled){
            // Checks if the boxes are "tangled"
            int xDist = Math.abs(getMiddleXCord() - otherObject.getMiddleXCord());
            int minXDist = (width / 2) + (otherObject.getWidth() / 2);

            int yDist = Math.abs(getMiddleYCord() - otherObject.getMiddleYCord());
            int minYDist = (height / 2) + (otherObject.getHeight() / 2);
            // Adjust the boxes if they are tangled
            if (xDist < minXDist) {
                int tangledXDist = minXDist - xDist;
                if (getMiddleXCord() < otherObject.getMiddleXCord()) {
                    xCord -= tangledXDist / 2;
                } else {
                    xCord += tangledXDist / 2;
                }
            }
            if (yDist < minYDist) {
                int tangledYDist = minYDist - yDist;
                if (getMiddleYCord() < otherObject.getMiddleYCord()) {
                    yCord -= tangledYDist / 2;
                } else {
                    yCord += tangledYDist / 2;
                }
            }
            calculateNextTarget();
        }
    }
}
