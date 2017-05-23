package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

/**
 * Created by Josef on 2014-03-04.
 * <p/>
 * An interface defining the getters and setters
 * used by Dungeon Objects which can move around
 */
public interface Mobile {
    public int getUpSpeed();
    public int getDownSpeed();
    public int getLeftSpeed();
    public int getRightSpeed();
    public void setUpSpeed(int upSpeed);
    public void setDownSpeed(int downSpeed);
    public void setLeftSpeed(int leftSpeed);
    public void setRightSpeed(int rightSpeed);
}
