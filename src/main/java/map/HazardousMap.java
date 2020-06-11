package map;

import exceptions.InvalidMapSizeException;

import java.util.Random;

public class HazardousMap extends Map{
    private HazardousMap() {
    }

    public static Map getInstance() {
        if (map == null)
            map = new HazardousMap();

        return map;
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

        double probability = 1;
        while(probability < 0.25 || probability > 0.35) {
            System.out.println(probability);
            probability = r.nextDouble();
        }

        generateWaterTiles(r, probability);
        generateTreasureTile(r);
        generateGrassTiles();

        return tiles;
    }
}
