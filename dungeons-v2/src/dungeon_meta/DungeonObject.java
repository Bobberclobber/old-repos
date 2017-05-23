package se.liu.ida.josfa969.dungeonsv2.dungeon_meta;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.ObjectListener;
import se.liu.ida.josfa969.dungeonsv2.enums.CharacterType;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.enums.EnemyType;
import se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josef on 2014-03-01.
 * <p/>
 * The meta class for any object which may appear in a room
 * (e.g enemies or treasure)
 * <p/>
 * Each type of object has a constructor defining the variables
 * required in order to represent the object graphically
 */
public abstract class DungeonObject {

    protected int xCord;
    protected int yCord;
    protected int width;
    protected int height;
    protected int health;
    private CharacterType characterType = null;
    private EnemyType enemyType = null;
    private ProjectileType projectileType = null;
    private Direction direction = null;
    private List<ObjectListener> listeners = null;

    /**
     * Constructor for creating a PlayerCharacter Dungeon Object
     *
     * @param characterType Defines what type PlayerCharacter is to be created
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     * @see se.liu.ida.josfa969.dungeonsv2.enums.CharacterType
     */
    // Warning suppressed in order to allow several constructors
    @SuppressWarnings("SuppressionAnnotation")
    protected DungeonObject(CharacterType characterType) {
        this.xCord = Constants.CHARACTER_START_X;
        this.yCord = Constants.CHARACTER_START_Y;
        this.width = Constants.CHARACTER_WIDTH;
        this.height = Constants.CHARACTER_HEIGHT;
        this.health = Constants.CHARACTER_STANDARD_HEALTH;
        this.characterType = characterType;
        listeners = new ArrayList<>();
    }

    /**
     * Constructor for creating a Projectile Dungeon Object
     *
     * @param xCord The x-coordinate at which the Projectile is to be created
     * @param yCord The y-coordinate at which the Projectile is to be created
     * @param width The width of the Projectile
     * @param height The height of the Projectile
     * @param projectileType The type of Projectile which is to be created
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile
     * @see se.liu.ida.josfa969.dungeonsv2.enums.ProjectileType
     */
    protected DungeonObject(int xCord, int yCord, int width, int height, ProjectileType projectileType) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.width = width;
        this.height = height;
        this.projectileType = projectileType;
        listeners = new ArrayList<>();
    }

    /**
     * Constructor for creating an Enemy Dungeon Object
     *
     * @param xCord The x-coordinate at which the Enemy is to be created
     * @param yCord The y-coordinate at which the Enemy is to be created
     * @param width The width of the Enemy
     * @param height The height of the Enemy
     * @param health The health of the Enemy
     * @param enemyType The type of Enemy which is to be created
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.StandardEnemy
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.StandardBoss
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.ShootingEnemy
     */
    protected DungeonObject(int xCord, int yCord, int width, int height, int health, EnemyType enemyType) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.width = width;
        this.height = height;
        this.health = health;
        this.enemyType = enemyType;
        listeners = new ArrayList<>();
    }

    /**
     * Constructor for creating a Door Dungeon Object
     *
     * @param direction Defines which wall the door
     *                  should be on. This decides the
     *                  coordinates and dimensions of the door
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     */
    protected DungeonObject(Direction direction) {
        this.direction = direction;
        switch (direction){
            case LEFT:
                makeLeftDoor();
                break;
            case RIGHT:
                makeRightDoor();
                break;
            case UP:
                makeUpDoor();
                break;
            case DOWN:
                makeDownDoor();
                break;
            default:
                break;
        }
    }

    /**
     * Constructor for creating a Trap Door Dungeon Object
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.TrapDoor
     */
    protected DungeonObject() {
        xCord = Constants.TRAP_DOOR_X_CORD;
        yCord = Constants.TRAP_DOOR_Y_CORD;
        width = Constants.TRAP_DOOR_SIDE;
        height = Constants.TRAP_DOOR_SIDE;
    }

    /**
     * Set the correct coordinates and dimensions for a left-door
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    private void makeLeftDoor() {
        xCord = Constants.LEFT_DOOR_X_CORD;
        yCord = Constants.LEFT_DOOR_Y_CORD;
        width = Constants.DOOR_SHORT_SIDE;
        height = Constants.DOOR_LONG_SIDE;
    }

    /**
     * Set the correct coordinates and dimensions for a right-door
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    private void makeRightDoor() {
        xCord = Constants.RIGHT_DOOR_X_CORD;
        yCord = Constants.RIGHT_DOOR_Y_CORD;
        width = Constants.DOOR_SHORT_SIDE;
        height = Constants.DOOR_LONG_SIDE;
    }

    /**
     * Set the correct coordinates and dimensions for an up-door
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    private void makeUpDoor() {
        xCord = Constants.UPPER_DOOR_X_CORD;
        yCord = Constants.UPPER_DOOR_Y_CORD;
        width = Constants.DOOR_LONG_SIDE;
        height = Constants.DOOR_SHORT_SIDE;
    }

    /**
     * Set the correct coordinates and dimensions for a down-door
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Door
     * @see se.liu.ida.josfa969.dungeonsv2.help_classes.Constants
     */
    private void makeDownDoor() {
        xCord = Constants.LOWER_DOOR_X_CORD;
        yCord = Constants.LOWER_DOOR_Y_CORD;
        width = Constants.DOOR_LONG_SIDE;
        height = Constants.DOOR_SHORT_SIDE;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }

    public void setXCord(int xCord) {
        this.xCord = xCord;
    }

    public void setYCord(int yCord) {
        this.yCord = yCord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHealth() {
        return health;
    }

    public int getMiddleXCord() {
        return xCord + width/2;
    }

    public int getMiddleYCord() {
        return yCord + height/2;
    }

    public abstract boolean isPlayerControlled();

    public abstract boolean isSolid();

    public boolean isDead() {
        return health <= 0;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addObjectListener(ObjectListener listener) {
        listeners.add(listener);
    }

    protected void notifyListeners() {
        for (ObjectListener listener : listeners) {
            listener.objectChanged();
        }
    }
}
