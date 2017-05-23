package se.liu.ida.josfa969.dungeonsv2.character_actions;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

/**
 * Created by Josef on 05/05/2014.
 * <p/>
 * A CharacterAction used to make the given character fire in the given direction
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction
 */
public class ShootAction implements CharacterAction {
    private PlayerCharacter playerCharacter;
    private Direction direction;

    public ShootAction(PlayerCharacter playerCharacter, Direction direction) {
        this.playerCharacter = playerCharacter;
        this.direction = direction;
    }

    @Override
    public void perform() {
        playerCharacter.shoot(direction);
    }

    @Override
    public int getUpdateRate() {
        return Constants.CHARACTER_STANDARD_SHOOTING_RATE;
    }
}
