package map;

public class HazardousMapCreator {
    public Map getMapInstance(int mapSize, int playerAmount) {
        if (HazardousMap.getInstance().setMapSize(mapSize, playerAmount)) {
            return HazardousMap.getInstance();
        }
        return null;
    }

    public Map getMapInstance() {
        return HazardousMap.getInstance();
    }
}
