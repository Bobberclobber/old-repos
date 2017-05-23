package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ControlledMovement;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Mobile;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import java.awt.*;

/**
 * Created by Josef on 2014-03-17.
 * <p/>
 * A Projectile Dungeon Object
 * <p/>
 * A Projectile is represented by a small
 * red or cyan circle which moves in a straight line
 * <p/>
 * Depending on which type the projectile is,
 * it damages either the player or the player's enemies
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Solid
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Mobile
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ControlledMovement
 */
public class Projectile extends DungeonObject implements Solid, Mobile, ControlledMovement {

    private int upSpeed;
    private int downSpeed;
    private int leftSpeed;
    private int rightSpeed;
    private final Direction direction;

    /**
     * Creates a projectile travelling in the given direction
     *
     * @param xCord The x-coordinate from which the projectile should originate
     * @param yCord The y-coordinate from which the projectile should originate
     * @param direction The Direction in which the projectile is to travel
     * @param projectileType Which type of Projectile is to be created
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     */
    public Projectile(int xCord, int yCord, Direction direction, ProjectileType projectileType) {
        super(xCord, yCord, Constants.PROJECTILE_WIDTH, Constants.PROJECTILE_HEIGHT, projectileType);

        int tempSpeed = 0;
        switch (projectileType) {
            case STANDARD_PROJECTILE:
                tempSpeed = Constants.PROJECTILE_STANDARD_SPEED;
                break;
            case ENEMY_PROJECTILE:
                tempSpeed = Constants.ENEMY_PROJECTILE_STANDARD_SPEED;
                break;
        }

        this.upSpeed = tempSpeed;
        this.downSpeed = tempSpeed;
        this.leftSpeed = tempSpeed;
        this.rightSpeed = tempSpeed;
        this.direction = direction;
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

    // Warning suppressed since it is either a false positive or irrelevant
    @SuppressWarnings({"RefusedBequest", "SuppressionAnnotation"})
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Moves the Projectile in a straight
     * line in the given Direction
     *
     * @param direction The Direction in which the projectile is to travel
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     */
    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }

    @Override
    public void moveLeft() {
        int curX = getXCord();
        setXCord(curX - leftSpeed);
    }

    @Override
    public void moveRight() {
        int curX = getXCord();
        setXCord(curX + rightSpeed);
    }

    @Override
    public void moveUp() {
        int curY = getYCord();
        setYCord(curY - upSpeed);
    }

    @Override
    public void moveDown() {
        int curY = getYCord();
        setYCord(curY + downSpeed);
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

    @Override
    public void onCollision(DungeonObject otherObject) {
    }
}
