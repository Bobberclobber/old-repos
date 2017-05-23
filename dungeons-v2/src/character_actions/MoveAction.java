package se.liu.ida.josfa969.dungeonsv2.character_actions;

import se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction;
import se.liu.ida.josfa969.dungeonsv2.dungeon_objects.PlayerCharacter;
import se.liu.ida.josfa969.dungeonsv2.enums.Direction;
import se.liu.ida.josfa969.dungeonsv2.help_classes.Constants;

/**
 * Created by Josef on 05/05/2014.
 * <p/>
 * A CharacterAction used to move the given character in the given direction
 *
 * @see se.liu.ida.josfa969.dungeonsv2.dungeon_interfaces.CharacterAction
 */
public class MoveAction implements CharacterAction {
    private PlayerCharacter playerCharacter;
    private Direction direction;

    public MoveAction(PlayerCharacter playerCharacter, Direction direction) {
        this.playerCharacter = playerCharacter;
        this.direction = direction;
    }

    @Override
    public void perform() {
        playerCharacter.move(direction);
    }

    @Override
    public int getUpdateRate() {
        return Constants.CHARACTER_STANDARD_MOVEMENT_RATE;
    }
}
