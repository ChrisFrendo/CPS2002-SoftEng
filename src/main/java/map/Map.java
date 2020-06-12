package map;

import java.util.Random;

import utils.Path;


/**
 * This singleton class contains fields and methods requirement to create and use a map
 */
public abstract class Map {
    /**
     * The size of the map, map is a box so only one size element is needed
     */
    protected int size;

    /**
     * 2D Tile array used to store the actual game map
     */
    protected Tile[][] tiles;

    /**
     * Singleton instance of Map class
     */
    protected static Map map = null;

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

        if (!tiles[row][column].getStatus().equals(Tile.Status.GRASS))
            return false;

        return Path.isPath(tiles, row, column, tiles.length);
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
    void generateWaterTiles(Random r, double probability) {

        int totalNumTiles = this.size * this.size;
        int numWaterTiles = (int) Math.floor(probability * totalNumTiles);

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
    void generateTreasureTile(Random r) {

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
    void generateGrassTiles() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (tiles[i][j] == null)
                    tiles[i][j] = new GrassTile();
            }
        }
    }

    /**
     * Abstract method to generate a Map, each concrete map creates its own implementation depending on its map type
     *
     * @param random Random object used to generate random numbers
     * @return A 2D Tile array representing the game map
     */
    public abstract Tile[][] generateMap(Random random);
}
