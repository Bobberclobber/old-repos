package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.PlayerControlled;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.enums.CharacterType;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * A Player Character Dungeon Object
 * <p/>
 * A Player Character is represented as a
 * green circle which is controlled by the player
 * <p/>
 * The player can move the character with the
 * WASD-keys and shoot using the arrow-keys
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.PlayerControlled
 */
public class PlayerCharacter extends DungeonObject implements PlayerControlled {

    private int upSpeed;
    private int downSpeed;
    private int leftSpeed;
    private int rightSpeed;
    private final List<Projectile> projectiles;

    /**
     * Creates a standard PlayerCharacter which starts in the middle of the room
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.enums.CharacterType
     */
    public PlayerCharacter() {
        super(CharacterType.STANDARD_CHARACTER);

        this.upSpeed = Constants.CHARACTER_STANDARD_SPEED;
        this.downSpeed = Constants.CHARACTER_STANDARD_SPEED;
        this.leftSpeed = Constants.CHARACTER_STANDARD_SPEED;
        this.rightSpeed = Constants.CHARACTER_STANDARD_SPEED;

        projectiles = new ArrayList<>();
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
    public Collection<Projectile> getProjectiles() {
        return projectiles;
    }

    @Override
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    @Override
    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    @Override
    public void clearProjectiles() {
        projectiles.clear();
    }

    /**
     * Increases or decreases the PlayerCharacter's
     * x-/y-coordinate depending on which Direction
     * is passed as the parameter (this is ultimately
     * controlled by the player)
     *
     * @param direction The Direction in which to move
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     * @see se.liu.ida.josfa969.dungeonsv2.threads.CharacterActionThread
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
        notifyListeners();
    }

    @Override
    public void moveRight() {
        int curX = getXCord();
        setXCord(curX + rightSpeed);
        notifyListeners();
    }

    @Override
    public void moveUp() {
        int curY = getYCord();
        setYCord(curY - upSpeed);
        notifyListeners();
    }

    @Override
    public void moveDown() {
        int curY = getYCord();
        setYCord(curY + downSpeed);
        notifyListeners();
    }

    /**
     * Creates a new Projectile travelling in the Direction
     * passed as the parameter (this is ultimately
     * controlled by the player)
     *
     * @param direction The Direction in which to shoot
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see se.liu.ida.josfa969.dungeonsv2.threads.CharacterActionThread
     */
    @Override
    public void shoot(Direction direction) {
        switch (direction) {
            case LEFT:
                shootLeft();
                break;
            case RIGHT:
                shootRight();
                break;
            case UP:
                shootUp();
                break;
            case DOWN:
                shootDown();
                break;
        }
    }

    @Override
    public void shootLeft() {
        int tempX = getMiddleXCord() - Constants.PROJECTILE_WIDTH/2;
        int tempY = getMiddleYCord() - Constants.PROJECTILE_HEIGHT/2;
        addProjectile(new Projectile(tempX, tempY, Direction.LEFT, ProjectileType.STANDARD_PROJECTILE));
    }

    @Override
    public void shootRight() {
        int tempX = getMiddleXCord() - Constants.PROJECTILE_WIDTH/2;
        int tempY = getMiddleYCord() - Constants.PROJECTILE_HEIGHT/2;
        addProjectile(new Projectile(tempX, tempY, Direction.RIGHT, ProjectileType.STANDARD_PROJECTILE));
    }

    @Override
    public void shootUp() {
        int tempX = getMiddleXCord() - Constants.PROJECTILE_WIDTH/2;
        int tempY = getMiddleYCord() - Constants.PROJECTILE_HEIGHT/2;
        addProjectile(new Projectile(tempX, tempY, Direction.UP, ProjectileType.STANDARD_PROJECTILE));
    }

    @Override
    public void shootDown() {
        int tempX = getMiddleXCord() - Constants.PROJECTILE_WIDTH/2;
        int tempY = getMiddleYCord() - Constants.PROJECTILE_HEIGHT/2;
        addProjectile(new Projectile(tempX, tempY, Direction.DOWN, ProjectileType.STANDARD_PROJECTILE));
    }

    @Override
    public boolean isPlayerControlled() {
        return true;
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
     * On collision with a StandardBoss, decrease health by five
     * <br>
     * On collision with any other enemy, decrease health by one
     *
     * @param otherObject The DungeonObject which this PlayerCharacter is colliding with
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     */
    @Override
    public void onCollision(DungeonObject otherObject) {
        if (otherObject instanceof NonPlayerControlled && ((NonPlayerControlled) otherObject).isEnemy()) {
            health -= 1;
            notifyListeners();
        } else if (otherObject.getClass() == Projectile.class && otherObject.getProjectileType() == ProjectileType.ENEMY_PROJECTILE) {
            health -= 5;
            notifyListeners();
        }
    }
}
