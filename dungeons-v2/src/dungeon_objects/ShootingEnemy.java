package se.liu.ida.josfa969.dungeonsv2.dungeon_objects;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled;
import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Shooter;
import se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.enums.EnemyType;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;
import se.liu.ida.josfa969.dungeonsv2.threads.ShooterEnemyActionThread;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Josef on 2014-04-09.
 * <p/>
 * A Shooting Enemy Dungeon Object
 * <p/>
 * A Shooting Enemy is represented by a small, stationary, purple
 * rectangle which periodically fire a shot in four different directions
 * <p/>
 * The player is awarded one point for killing it
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.NonPlayerControlled
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.Shooter
 */
public class ShootingEnemy extends DungeonObject implements NonPlayerControlled, Shooter {

    private final ArrayList<Projectile> projectiles;
    private final ShooterEnemyActionThread shootingThread;

    /**
     * Creates a ShootingEnemy at the given coordinates.
     * On creation the Thread handling the shooting
     * is created and started
     *
     * @param xCord The x-coordinate at which the ShootingEnemy is spawned
     * @param yCord The y-coordinate at which the ShootingEnemy is spawned
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     * @see se.liu.ida.josfa969.dungeonsv2.enums.EnemyType
     * @see se.liu.ida.josfa969.dungeonsv2.threads.ShooterEnemyActionThread
     */
    public ShootingEnemy(int xCord, int yCord) {
        super(xCord, yCord, Constants.ENEMY_STANDARD_WIDTH, Constants.ENEMY_STANDARD_HEIGHT, Constants.ENEMY_STANDARD_HEALTH, EnemyType.SHOOTING_ENEMY);

        projectiles = new ArrayList<>();
        shootingThread = new ShooterEnemyActionThread(this);
        shootingThread.start();
    }

    @Override
    public void move() {
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

    @Override
    public int getUpSpeed() {
        return 0;
    }

    @Override
    public int getDownSpeed() {
        return 0;
    }

    @Override
    public int getLeftSpeed() {
        return 0;
    }

    @Override
    public int getRightSpeed() {
        return 0;
    }

    @Override
    public void setUpSpeed(int upSpeed) {
    }

    @Override
    public void setDownSpeed(int downSpeed) {
    }

    @Override
    public void setLeftSpeed(int leftSpeed) {
    }

    @Override
    public void setRightSpeed(int rightSpeed) {
    }

    @Override
    public ArrayList<Projectile> getProjectiles() {
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
     * Fires a Projectile of type ENEMY_PROJECTILE in each of the four Directions
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see se.liu.ida.josfa969.dungeonsv2.enums.Direction
     */
    @Override
    public void shoot() {
        // Fires a shot in all four directions
        int tempX = getMiddleXCord() - Constants.PROJECTILE_WIDTH / 2;
        int tempY = getMiddleYCord() - Constants.PROJECTILE_HEIGHT / 2;
        addProjectile(new Projectile(tempX, tempY, Direction.LEFT, ProjectileType.ENEMY_PROJECTILE));
        addProjectile(new Projectile(tempX, tempY, Direction.RIGHT, ProjectileType.ENEMY_PROJECTILE));
        addProjectile(new Projectile(tempX, tempY, Direction.UP, ProjectileType.ENEMY_PROJECTILE));
        addProjectile(new Projectile(tempX, tempY, Direction.DOWN, ProjectileType.ENEMY_PROJECTILE));
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
     * On collision with a Projectile of type
     * STANDARD_PROJECTILE the enemy's health is reduced by 1
     *
     * @param otherObject The DungeonObject which this ShootingEnemy is colliding with
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_meta.DungeonObject
     */
    @Override
    public void onCollision(DungeonObject otherObject) {
        if (otherObject.getClass() == Projectile.class && otherObject.getProjectileType() == ProjectileType.STANDARD_PROJECTILE) {
            health -= 1;
            if(health == 0) {
                shootingThread.stop();
            }
        }
    }
}
