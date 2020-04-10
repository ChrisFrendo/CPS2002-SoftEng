package map;

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

}
