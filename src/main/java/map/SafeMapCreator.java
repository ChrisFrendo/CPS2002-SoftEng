package map;

/**
 * Concrete creator for a safe map
 */
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
