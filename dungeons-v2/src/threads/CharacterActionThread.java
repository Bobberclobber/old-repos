package se.liu.ida.josfa969.dungeonsv2.threads;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction;

/**
 * Created by Josef on 03/04/2014.
 * <p/>
 * A thread which is started every time the player moves or shoots
 * <br>
 * The player is able to move in any direction
 * since a new thread is used for every movement key
 * <br>
 * The same thread is used for shooting in all directions
 * which makes the player unable to shoot in two directions at once
 *
 * @see java.lang.Thread
 * @see java.lang.Runnable
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
 * @see se.liu.ida.josfa969.dungeonsv2.threads.CharacterActionThread
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction
 * @see se.liu.ida.josfa969.dungeonsv2.character_actions.MoveAction
 * @see se.liu.ida.josfa969.dungeonsv2.character_actions.ShootAction
 */
public class CharacterActionThread implements Runnable {

    private Thread thread = null;
    private final String threadName;
    private CharacterAction action = null;
    private boolean finished;
    private int updateRate;

    public CharacterActionThread(String threadName) {
        this.threadName = threadName;
        finished = false;
        System.out.println("Creating: " + threadName);
    }

    /**
     * Make the given player character move or shoot
     * depending on which actionType was passed as a parameter
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter
     */
    @Override
    public void run() {
        System.out.println("Running: " + threadName);
        try {
            // Performs whatever action is
            // defined by the method setCharacterAction
            if (action != null) {
                action.perform();
            }
            Thread.sleep(updateRate);
            if (!finished) {
                run();
            }
        } catch (InterruptedException ignored) {
            System.out.println("Interrupted: " + threadName);
        }
    }

    /**
     * Defines which action this thread should perform
     *
     * @param action The CharacterAction which this thread should perform
     */
    public void setCharacterAction(CharacterAction action) {
        this.action = action;
        updateRate = action.getUpdateRate();
    }

    public void start() {
        System.out.println("Starting: " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public void stop() {
        System.out.println("Stopping: " + threadName);
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }
}
