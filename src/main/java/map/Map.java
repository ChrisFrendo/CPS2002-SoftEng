package map;

import java.util.Random;

import exceptions.InvalidMapSizeException;
import utils.Path;

public class Map {
    private int size;
    private Tile[][] tiles;

    private static Map map = null;

    private Map() {

    }

    public static Map resetInstance() {
        map.size = 0;
        map.tiles = null;

        return map;
    }

    public static Map getInstance() {
        if (map == null)
            map = new Map();

        return map;
    }

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

    public int getSize() {
        return size;
    }

    public boolean isValidStartingPosition(int row, int column) {
        if (tiles == null) {
            throw new NullPointerException("Map is not initialized yet. Call generateMap");
        }

        if (!tiles[row][column].getStatus().equals(Tile.Status.GRASS))
            return false;

        return Path.isPath(tiles, row, column, tiles.length);
    }

    public boolean tileExists(int row, int column) {
        return row >= 0 && column >= 0 && row < this.size && column < this.size;
    }

    public Tile.Status getTileStatus(int row, int column) {
        return tiles[row][column].getStatus();
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

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

    private void generateTreasureTile(Random r) {

        int row = r.nextInt(this.size);
        int column = r.nextInt(this.size);

        if (tiles[row][column] == null) {
            this.tiles[row][column] = new TreasureTile();
        } else {
            generateTreasureTile(r);
        }
    }

    private void generateGrassTiles() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (tiles[i][j] == null)
                    tiles[i][j] = new GrassTile();
            }
        }
    }
}
