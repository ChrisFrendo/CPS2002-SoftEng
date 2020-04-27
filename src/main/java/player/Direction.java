package player;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum class with dictionary consisting of the
 * directions a player can move in
 */
public enum Direction {
    up('u'),
    down('d'),
    left('l'),
    right('r');

    private char direction;

    /**
     * Constructor for the direction
     * @param charDirection character representing the direction
     */
    Direction(char charDirection) {
        this.direction = charDirection;
    }

    /**
     * Getter method for the direction
     * @return the direction
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Lookup table
     */
    private static final Map<Character, Direction> lookup = new HashMap<>();

    /*
      Populate the lookup table on loading time
     */
    static {
        for (Direction env : Direction.values()) {
            lookup.put(env.getDirection(), env);
        }
    }

    /**
     * This method can be used for reverse lookup purpose
     *
     * @param charDirection character representing the direction to look for
     * @return the direction requested in enum form
     */
    public static Direction get(char charDirection) {
        return lookup.get(charDirection);
    }
}
