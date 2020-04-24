package map;

import java.util.Random;

import exceptions.InvalidMapSizeException;


/**
 * This singleton class contains fields and methods requirement to create and use a map
 */
public class Map {
    /**
     * The size of the map, map is a box so only one size element is needed
     */
    private int size;


    /**
     * 2D Tile array used to store the actual game map
     */
    private Tile[][] tiles;

    /**
     * Singleton instance of Map class
     */
    private static Map map = null;

    private Map() {
    }

    /**
     * This method resets the single map instance
     *
     * @return the empty single map instance
     */
    public static Map resetInstance() {
        map.size = 0;
        map.tiles = null;

        return map;
    }

    /**
     * Getter for the singleton map instance
     *
     * @return the single map instance
     */
    public static Map getInstance() {
        if (map == null)
            map = new Map();

        return map;
    }

    /**
     * This setter sets the size of the map provided that the parameters
     * are within the boundaries specified
     *
     * @param size         the length of the side of the desired square map
     * @param numOfPlayers the amount of players that will be using the map
     * @return true if the map size was successfully set, false otherwise
     */
    public boolean setMapSize(int size, int numOfPlayers) {
        if (size > 50 || numOfPlayers < 2 || numOfPlayers > 8)
            return false;

        if (numOfPlayers <= 4 && size < 5)
            return false;

        if (numOfPlayers >= 5 && size < 8)
            return false;

        this.size = size;
        return true;
    }

    /**
     * This method generated different tiles that make up the board
     *
     * @param r an instance of random used to generate the random numbers
     * @return the board that was generated
     */
    public Tile[][] generateMap(Random r) {
        if (this.size < 5) {
            throw new InvalidMapSizeException("Size should be greater than 5: Call setMapSize before");
        }

        tiles = new Tile[this.size][this.size];

        generateWaterTiles(r);
        generateTreasureTile(r);
        generateGrassTiles();

        return tiles;
    }

    /**
     * Getter for the map size
     *
     * @return map size
     */
    public int getSize() {
        return size;
    }

    /**
     * This method checks whether the indicated tile is a valid starting tile
     * i.e. a grass tile
     *
     * @param row    the row the tile is in
     * @param column the column the tile is in
     * @return true if the tile is valid, false otherwise
     */
    public boolean isValidStartingPosition(int row, int column) {
        if (tiles == null) {
            throw new NullPointerException("Map is not initialized yet. Call generateMap");
        }

        return tiles[row][column].getStatus().equals(Tile.Status.GRASS);
    }

    /**
     * This method checks whether the indicated tile exists
     * i.e. if the tile is stationed within the board
     *
     * @param row    the row the tile is in
     * @param column the column the tile is in
     * @return true if the tile exists, false otherwise
     */
    public boolean tileExists(int row, int column) {
        return row >= 0 && column >= 0 && row < this.size && column < this.size;
    }

    /**
     * This method returns what type of tile the indicated tile is.
     * A tile can be a grass, water or treasure tile.
     *
     * @param row    the row the tile is in
     * @param column the column the tile is in
     * @return the type of the indicated tile
     */
    public Tile.Status getTileStatus(int row, int column) {
        return tiles[row][column].getStatus();
    }

    /**
     * This method returns the tile that the parameters describe
     *
     * @param row    the row the tile is in
     * @param column the column the tile is in
     * @return the tile the parameters indicated
     */
    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    /**
     * This method generates the water tiles
     *
     * @param r an instance of random used to generate the random numbers
     */
    private void generateWaterTiles(Random r) {

        int totalNumTiles = this.size * this.size;
        int numWaterTiles = (int) Math.floor(0.20 * totalNumTiles);

        for (int i = 0; i < numWaterTiles; i++) {
            // randomly generate coordinates
            int row = r.nextInt(this.size);
            int column = r.nextInt(this.size);

            // if position in array is not empty, add new WaterTile
            // else do i-- and continue loop;
            if (tiles[row][column] == null) {
                tiles[row][column] = new WaterTile();
            } else {
                i--;
            }
        }

    }

    /**
     * This method generates a treasure tile
     *
     * @param r an instance of random used to generate the random numbers
     */
    private void generateTreasureTile(Random r) {

        int row = r.nextInt(this.size);
        int column = r.nextInt(this.size);

        if (tiles[row][column] == null) {
            this.tiles[row][column] = new TreasureTile();
        } else {
            generateTreasureTile(r);
        }
    }

    /**
     * This method generates the grass tiles by setting tiles without a status
     * as grass tiles
     */
    private void generateGrassTiles() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (tiles[i][j] == null)
                    tiles[i][j] = new GrassTile();
            }
        }
    }
}
