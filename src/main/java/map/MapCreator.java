package map;

import java.util.Random;

public abstract class MapCreator {

    public static Tile[][] createMap(String type, int mapSize, int playerAmount) {
        MapCreator creator = findCreatorType(type);
        return creator.createMap(mapSize, playerAmount);
    }

    private static MapCreator findCreatorType(String type) {
        switch(type) {
            default:
                return new SafeMapCreator();
        }
    }

    public abstract Tile[][] createMap(int mapSize, int playerAmount);
}
