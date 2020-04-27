package map;

/**
 * Abstract Tile class used to group all types of tiles that can be found on the map
 */
public abstract class Tile {

    /**
     * html string used to represent a hidden tile
     */
    static final String HTML_NOT_VISITED = "<img alt=\"Hidden tile\" src=\"images/tiles/HiddenTile.png\">";

    /**
     * Returns the html used to represent a tile
     *
     * @param visited Indicates if this tile has been visited or not, affects html returned
     * @return A String containing the html needed to represent this Tile
     */
    public abstract String getHtml(boolean visited);

    /**
     * @return The type of Tile
     */
    public abstract Status getStatus();

    /**
     * Enum used to distinguish between tile types
     */
    public enum Status {
        GRASS,
        WATER,
        TREASURE
    }
}
