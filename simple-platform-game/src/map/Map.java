package se.scramble_studios.nehro.simple_platform_game.java.map;

import se.scramble_studios.nehro.simple_platform_game.java.core.Const;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Josef on 27/03/2015.
 * <p>
 *     A class representing the map
 * </p>
 */
public class Map {
    private String mapName = "";

    private int numBlockWidth = 0;
    private int numBlockHeight = 0;
    private float startx = 0;
    private float starty = 0;

    private char[][] blockMap = new char[0][0];
    private boolean mapInit = false;

    public char[][] initMap(String mapName) {
        FileReader fr = parseMapHeader(mapName);
        parseMap(fr);
        mapInit = true;
        return blockMap;
    }

    /**
     * Reads the header information of a file and sets the maps name,
     * numBlockWidth and numBlockHeight accordingly. The FileReader used for this is
     * returned in order to maintain the position
     *
     * @param map The name of the map whose header is to be parse
     * @return Returns a FileReader pointing to the position where the actual map begins
     */
    @SuppressWarnings("ConstantConditions")
    private FileReader parseMapHeader(String map) {
        String filePath = Const.MAP_FILE_ROOT + map + ".map";
        try {
            // Creates a file reader of the given map
            FileReader fr = new FileReader(filePath);
            String word = "";
            boolean parseName = false;
            boolean parseWidth = false;
            boolean parseHeight = false;
            boolean parseStartx = false;
            boolean parseStarty = false;
            while (true) {
                try {
                    // Reads a character
                    char c = (char)fr.read();
                    // Breaks if the character is the end of header sign
                    if (c == Const.END_OF_HEADER)
                        break;
                    if (Character.isLetter(c) || Character.isDigit(c) || c == ' ')
                        word += c;

                    // If a field value has been completely read
                    if (c == '\n') {
                        if (parseName) {
                            mapName = word;
                            parseName = false;
                        }
                        if (parseWidth) {
                            numBlockWidth = Integer.parseInt(word);
                            parseWidth = false;
                        }
                        if (parseHeight) {
                            numBlockHeight = Integer.parseInt(word);
                            parseHeight = false;
                        }
                        if (parseStartx) {
                            startx = Integer.parseInt(word);
                            parseStartx = false;
                        }
                        if (parseStarty) {
                            starty = Integer.parseInt(word);
                            parseStarty = false;
                        }
                        word = "";
                    }

                    // Sets flags depending on which header field is being read
                    if (word.equals(Const.PMAP_NAME)) {
                        parseName = true;
                        word = "";
                    }
                    if (word.equals(Const.PMAP_WIDTH)) {
                        parseWidth = true;
                        word = "";
                    }
                    if (word.equals(Const.PMAP_HEIGHT)) {
                        parseHeight = true;
                        word = "";
                    }
                    if (word.equals(Const.PMAP_STARTX)) {
                        parseStartx = true;
                        word = "";
                    }
                    if (word.equals(Const.PMAP_STARTY)) {
                        parseStarty = true;
                        word = "";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            blockMap = new char[numBlockHeight][numBlockWidth];
            return fr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        // This should not be reached
        return null;
    }

    private void parseMap(FileReader fr) {
        for (int h = numBlockHeight - 1; h >= 0; h--) {
            for (int w = 0; w < numBlockWidth; w++) {
                try {
                    // Reads a character
                    char c = (char) fr.read();
                    // Makes sure no invalid characters are added to the map
                    while(!isValid(c))
                        c = (char) fr.read();

                    // Adds the char
                    blockMap[h][w] = c;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private boolean isValid(char c) {
        return c == Const.BRICK_CHAR || c == Const.SKY_CHAR || c == Const.SPIKE_CHAR;
    }

    public int getWidthBlocks() {
        return numBlockWidth;
    }

    public int getHeightBlocks() {
        return numBlockHeight;
    }

    public int getWidth() {
        return numBlockWidth * Const.BLOCK_WIDTH;
    }

    public int getHeight() {
        return numBlockHeight * Const.BLOCK_HEIGHT;
    }

    public float getStartx() {
        return startx * Const.BLOCK_WIDTH;
    }

    public float getStarty() {
        return starty * Const.BLOCK_HEIGHT;
    }

    public String getMapName() {
        return mapName;
    }

    public char[][] getBlockMap() throws MapNotInitializedException {
        if (mapInit)
            return blockMap;
        else
            throw new MapNotInitializedException();
    }

    // Get block based on its coordinates
    public char getBlock(int w, int h) {
        char ret = Const.BRICK_CHAR;
        try {
            ret = blockMap[h][w];
        } catch (ArrayIndexOutOfBoundsException e) {
            //System.err.println("Index out of bounds!");
        }
        return ret;
    }

    public char getBlock(float x, float y) {
        char ret = Const.BRICK_CHAR;
        try {
            ret = blockMap[(int)(y / Const.BLOCK_HEIGHT)][(int)(x / Const.BLOCK_WIDTH)];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Index out of bounds!");
        }
        return ret;
    }

    public class MapNotInitializedException extends Exception {
        @Override
        public void printStackTrace() {
            super.printStackTrace();
            System.err.println("Block map has not been initialized.");
        }
    }
}
