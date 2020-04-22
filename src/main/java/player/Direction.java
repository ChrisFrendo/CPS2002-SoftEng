package player;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    up('u'),
    down('d'),
    left('l'),
    right('r');

    private char direction;

    Direction(char envUrl) {
        this.direction = envUrl;
    }

    public char getDirection() {
        return direction;
    }

    //Lookup table
    private static final Map<Character, Direction> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static {
        for (Direction env : Direction.values()) {
            lookup.put(env.getDirection(), env);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Direction get(char url) {
        return lookup.get(url);
    }
}
