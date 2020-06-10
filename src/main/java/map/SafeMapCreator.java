package map;

import java.util.Random;

public class SafeMapCreator extends MapCreator {
    public Map getMapInstance(int mapSize, int playerAmount) {
        if (SafeMap.getInstance().setMapSize(mapSize, playerAmount)) {
            return SafeMap.getInstance();
        }
        return null;
    }

    public Map getMapInstance() {
        return SafeMap.getInstance();
    }
}
