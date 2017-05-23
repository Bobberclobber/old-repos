package se.wgco.jgf.adventura_ultima.listeners;

import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.gui_components.PlayFieldComponent;
import se.wgco.jgf.adventura_ultima.navigation.Direction;
import se.wgco.jgf.adventura_ultima.play_field.TileBlock;
import se.wgco.jgf.adventura_ultima.play_field.WorldMap;
import se.wgco.jgf.adventura_ultima.tiles.TerrainType;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Josef on 08/05/2014.
 * <p>
 *     A listener class handling all key inputs which affect the character
 * </p>
 */
public class CharacterKeyListener implements KeyListener {
    private PlayFieldComponent playField = null;
    private PlayerCharacter character = null;
    private WorldMap world = null;
    private TileBlock currentTileBlock = null;
    private int blockMaxXIndex = 0;
    private int blockMaxYIndex = 0;

    public CharacterKeyListener(PlayFieldComponent playField, PlayerCharacter character, WorldMap world) {
        this.playField = playField;
        this.character = character;
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentTileBlock = world.getCurrentTileBlock();
        blockMaxXIndex = currentTileBlock.getTerrainTiles().length - 1;
        blockMaxYIndex = currentTileBlock.getTerrainTiles()[0].length - 1;
        switch (e.getKeyCode()) {
            // Movement keys
            case KeyEvent.VK_W:
                moveUp();
                break;
            case KeyEvent.VK_A:
                moveLeft();
                break;
            case KeyEvent.VK_S:
                moveDown();
                break;
            case KeyEvent.VK_D:
                moveRight();
                break;
        }
        playField.repaint();
    }

    private void moveUp() {
        int xPos = character.getXPosition();
        int yPos = character.getYPosition();
        if (yPos == 0) {
            world.switchToTileBlock(Direction.NORTH);
            character.setYPosition(blockMaxYIndex);
        } else {
            if (currentTileBlock.getTerrainTile(xPos, yPos - 1).getTileType() != TerrainType.WATER_TILE) {
                character.move(Direction.NORTH);
            }
        }
    }

    private void moveDown() {
        int xPos = character.getXPosition();
        int yPos = character.getYPosition();
        if (yPos == blockMaxYIndex) {
            world.switchToTileBlock(Direction.SOUTH);
            character.setYPosition(0);
        } else {
            if (currentTileBlock.getTerrainTile(xPos, yPos + 1).getTileType() != TerrainType.WATER_TILE) {
                character.move(Direction.SOUTH);
            }
        }
    }

    private void moveLeft() {
        int xPos = character.getXPosition();
        int yPos = character.getYPosition();
        if (xPos == 0) {
            world.switchToTileBlock(Direction.WEST);
            character.setXPosition(blockMaxXIndex);
        } else {
            if (currentTileBlock.getTerrainTile(xPos - 1, yPos).getTileType() != TerrainType.WATER_TILE) {
                character.move(Direction.WEST);
            }
        }
    }

    private void moveRight() {
        int xPos = character.getXPosition();
        int yPos = character.getYPosition();
        if (xPos == blockMaxXIndex) {
            world.switchToTileBlock(Direction.EAST);
            character.setXPosition(0);
        } else {
            if (currentTileBlock.getTerrainTile(xPos + 1, yPos).getTileType() != TerrainType.WATER_TILE) {
                character.move(Direction.EAST);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
