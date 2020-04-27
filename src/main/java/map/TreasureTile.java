package map;

/**
 * Subclass of Tile used to represent a Treasure Tile on the map
 */
public class TreasureTile extends Tile {

    /**
     * html string used to represent a treasure tile
     */
    private static final String HTML = "<img alt=\"Treasure tile\" src=\"images/tiles/TreasureTile.png\">";

    @Override
    public String getHtml(boolean visited) {
        if (!visited) {
            return HTML_NOT_VISITED;
        }
        return HTML;
    }

    @Override
    public Status getStatus() {
        return Status.TREASURE;
    }
}
