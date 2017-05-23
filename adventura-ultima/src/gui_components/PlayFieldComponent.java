package se.wgco.jgf.adventura_ultima.gui_components;

import se.wgco.jgf.adventura_ultima.character_classes.PlayerCharacter;
import se.wgco.jgf.adventura_ultima.constants.GameWindowConstants;
import se.wgco.jgf.adventura_ultima.constants.WorldConstants;
import se.wgco.jgf.adventura_ultima.graphics_maps.EnumColorMap;
import se.wgco.jgf.adventura_ultima.graphics_maps.EnumShapeDataMap;
import se.wgco.jgf.adventura_ultima.play_field.WorldMap;
import se.wgco.jgf.adventura_ultima.tiles.TerrainTile;
import se.wgco.jgf.adventura_ultima.tiles.terrain_feature.TerrainFeatureType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Josef on 07/05/2014.
 * <p>
 *     A JComponent which paints the part of the game
 *     where the player is allowed to traverse the world map
 * </p>
 */
public class PlayFieldComponent extends JComponent {
    private final int PLAY_FIELD_WIDTH = GameWindowConstants.PLAY_FIELD_WIDTH;
    private final int PLAY_FIELD_HEIGHT = GameWindowConstants.PLAY_FIELD_HEIGHT;
    private final int TILE_SIDE = WorldConstants.TILE_SIDE;

    private WorldMap world = null;
    private PlayerCharacter character = null;

    public PlayFieldComponent(WorldMap world, PlayerCharacter character) {
        this.world = world;
        this.character = character;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paintMap(g2d);
        paintCharacter(g2d);
    }

    private void paintMap(Graphics2D g2d) {
        TerrainTile[][] terrainTiles = world.getCurrentTileBlock().getTerrainTiles();

        // Draws the terrain tiles
        for (int x = 0; x < terrainTiles.length; x++) {
            for (int y = 0; y < terrainTiles[0].length; y++) {
                TerrainTile currentTile = terrainTiles[x][y];
                int tileX = x * TILE_SIDE;
                int tileY = y * TILE_SIDE;
                g2d.setColor(EnumColorMap.terrainTileMap.get(currentTile.getTileType()));
                g2d.fillRect(tileX, tileY, TILE_SIDE, TILE_SIDE);
                for (TerrainFeatureType featureType : currentTile.getTileFeatures()) {
                    int[] baseXPoints = EnumShapeDataMap.terrainFeatureXPointsMap.get(featureType);
                    int[] baseYPoints = EnumShapeDataMap.terrainFeatureYPointsMap.get(featureType);
                    int pointNumber = EnumShapeDataMap.terrainFeaturePointNumberMap.get(featureType);
                    int[] adjustedXPoints = adjustPoints(baseXPoints, tileX);
                    int[] adjustedYPoints = adjustPoints(baseYPoints, tileY);
                    Color featureColor = EnumColorMap.terrainFeatureMap.get(featureType);
                    g2d.setColor(featureColor);
                    Polygon featureShape = new Polygon(adjustedXPoints, adjustedYPoints, pointNumber);
                    g2d.fill(featureShape);
                    g2d.setColor(Color.BLACK);
                    g2d.draw(featureShape);
                }
            }
        }

        // Draws a grid of black lines
        g2d.setColor(Color.BLACK);
        for (int x = 0; x <= terrainTiles.length; x++) {
            g2d.drawLine(x * TILE_SIDE, 0, x * TILE_SIDE, PLAY_FIELD_HEIGHT);
        }

        for (int y = 0; y <= terrainTiles[0].length; y++) {
            g2d.drawLine(0, y * TILE_SIDE, PLAY_FIELD_WIDTH, y * TILE_SIDE);
        }

        // Draws a black frame around the play field
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, GameWindowConstants.PLAY_FIELD_WIDTH - 1, GameWindowConstants.PLAY_FIELD_HEIGHT - 1);
    }

    private void paintCharacter(Graphics2D g2d) {
        int characterWidth = WorldConstants.STANDARD_CHARACTER_WIDTH;
        int characterHeight = WorldConstants.STANDARD_CHARACTER_HEIGHT;
        int widthDiff = (TILE_SIDE - characterWidth) / 2;
        int heightDiff = (TILE_SIDE - characterHeight) / 2;
        int characterXPos = TILE_SIDE * character.getXPosition() + widthDiff;
        int characterYPos = TILE_SIDE * character.getYPosition() + heightDiff;

        g2d.setColor(EnumColorMap.characterClassMap.get(character.getClassType()));
        g2d.fillOval(characterXPos, characterYPos, characterWidth, characterHeight);
    }

    private int[] adjustPoints(int[] points, int coordinate) {
        int[] temp = new int[points.length];
        for (int n = 0; n < temp.length; n++) {
            temp[n] = points[n] + coordinate;
        }
        return temp;
    }
}
