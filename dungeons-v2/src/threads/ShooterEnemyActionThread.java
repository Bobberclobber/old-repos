package se.liu.ida.josfa969.dungeonsv2.threads;

import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.ShootingEnemy;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

/**
 * Created by Josef on 2014-04-09.
 * <p/>
 * A thread which makes a Shooting Enemy fire a
 * shot in every Direction every two seconds
 *
 * @see java.lang.Thread
 * @see java.lang.Runnable
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.ShootingEnemy
 */
public class ShooterEnemyActionThread implements Runnable {
    private Thread thread = null;
    private final String threadName;
    private ShootingEnemy enemy;
    private boolean finished;

    public ShooterEnemyActionThread(ShootingEnemy enemy) {
        this.threadName = "EnemyShootingThread";
        this.enemy = enemy;
        finished = false;
        System.out.println("Creating: " + "EnemyShootingThread");
    }

    /**
     * Make the enemy related to this thread fire a shot every two seconds
     *
     * @see se.liu.ida.josfa969.dungeonsv2.dungeon_objects.ShootingEnemy
     */
    @Override
    public void run() {
        System.out.println("Running: " + threadName);
        try {
            Thread.sleep(Constants.SHOOTING_ENEMY_FIRE_RATE);
            enemy.shoot();
            if (!finished) {
                run();
            }
        } catch (InterruptedException ignored) {
            System.out.println("Interrupted: " + threadName);
        }
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

}
