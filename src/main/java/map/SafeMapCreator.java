package map;

import java.util.Random;

public class SafeMapCreator extends MapCreator {
    public Tile[][] createMap(int mapSize, int playerAmount) {
        if (SafeMap.getInstance().setMapSize(mapSize, playerAmount)) {
            return SafeMap.getInstance().generateMap(new Random());
        }
        return null;
    }
}
