package map;

/**
 * Abstract class for Map Creators used in factory pattern
 */
public abstract class MapCreator {

    /**
     * Enum used to distinguish between map types
     */
    public enum MapType {
        SAFE,
        HAZARDOUS
    }

    /**
     * Returns a Map instance depending on the type passed
     * Also sets up the map size in the process
     *
     * @param type         The type of map instnace to get
     * @param mapSize      The size of the map to create
     * @param playerAmount The number of players to play on the map
     * @return A singleton Map object
     */
    public static Map getMapInstance(MapType type, int mapSize, int playerAmount) {
        MapCreator creator = findCreatorType(type);
        return creator.getMapInstance(mapSize, playerAmount);
    }

    /**
     * Returns a Map instance depending on the type passed
     *
     * @param type The type of map instance to get
     * @return A singleton Map object
     */
    public static Map getMapInstance(MapType type) {
        MapCreator creator = findCreatorType(type);
        return creator.getMapInstance();
    }

    /**
     * Determines which map creator is needed depending on the type passed
     *
     * @param type The type of map wanted
     * @return A new MapCreator child
     */
    private static MapCreator findCreatorType(MapType type) {
        if (type.equals(MapType.SAFE)) {
            return new SafeMapCreator();
        }
        return new HazardousMapCreator();
    }

    /**
     * Abstract method to be used by the different concrete map creators
     * and also setup the map size in the process
     *
     * @param mapSize      The size of the map
     * @param playerAmount The number of players playing on the map
     * @return A Map Singleton
     */
    public abstract Map getMapInstance(int mapSize, int playerAmount);

    /**
     * Abstract method to be used by the different concrete map creators
     *
     * @return A Map Singleton
     */
    public abstract Map getMapInstance();
}
