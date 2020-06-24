package map;

import java.util.Random;

import exceptions.InvalidMapSizeException;

/**
 * Concrete Map for a Safe Map
 */
public class SafeMap extends Map {

    private static SafeMap map = null;


    private SafeMap() {
    }

    public static Map getInstance() {
        if (map == null)
            map = new SafeMap();

        return map;
    }

    /**
     * This method resets the single map instance
     *
     * @return the empty single map instance
     */
    public Map resetInstance() {
        map = null;
        return null;
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
        while (probability > 0.10) {
            probability = r.nextDouble();
        }

        generateWaterTiles(r, probability);
        generateTreasureTile(r);
        generateGrassTiles();

        return tiles;
    }
}
