package se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces;

import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.Projectile;

import java.util.Collection;

/**
 * Created by Josef on 2014-04-09.
 * <p/>
 * An interface defining the methods used by
 * Dungeon Objects required to handle projectiles
 */
public interface ProjectileHandler {
    public Collection<Projectile> getProjectiles();
    public void addProjectile(Projectile projectile); //Warning is false positive
    public void removeProjectile(Projectile projectile);
    public void clearProjectiles();
}
