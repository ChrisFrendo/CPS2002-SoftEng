package map;

import java.util.Random;

public abstract class MapCreator {

    public static Map getMapInstance(String type, int mapSize, int playerAmount) {
        MapCreator creator = findCreatorType(type);
        return creator.getMapInstance(mapSize, playerAmount);
    }

    public static Map getMapInstance(String type) {
        MapCreator creator = findCreatorType(type);
        return creator.getMapInstance();
    }

    private static MapCreator findCreatorType(String type) {
        switch(type) {
            case "safe":
                return new SafeMapCreator();
            default:
                return new HazardousMapCreator();
        }
    }

    public abstract Map getMapInstance(int mapSize, int playerAmount);

    public abstract Map getMapInstance();
}
