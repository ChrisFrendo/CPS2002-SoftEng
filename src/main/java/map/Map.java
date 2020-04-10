package map;

import java.util.Random;

public class Map {
    private int size;
    private Tile[][] tiles;

    private static Map instance = null;

    public static Map getInstance() {
        if (instance == null)
            instance = new Map();

        return instance;
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
        tiles = new Tile[this.size][this.size];

        generateWaterTiles(r);
        generateTreasureTile(r);
        generateGrassTiles();

        return tiles;
    }

    private void generateWaterTiles(Random r) {
//      Random r = new Random();
        int totalNumTiles = this.size * this.size;
        int numWaterTiles = (int) Math.floor(0.20 * totalNumTiles);

        for (int i = 0; i < numWaterTiles; i++) {
            // randomly generate coordinates
            int x = r.nextInt(this.size);
            int y = r.nextInt(this.size);

            // if position in array is not empty, add new WaterTile
            // else do i-- and continue loop;
            if (tiles[x][y] == null) {
                tiles[x][y] = new WaterTile();
            } else {
                i--;
            }
        }

    }

    private void generateTreasureTile(Random r) {

        int x = r.nextInt(this.size);
        int y = r.nextInt(this.size);

        if (tiles[x][y] == null) {
            this.tiles[x][y] = new TreasureTile();
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
