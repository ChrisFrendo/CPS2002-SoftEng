package map;

/**
 * Subclass of Tile used to represent a Water Tile on the map
 */
public class WaterTile extends Tile {

    /**
     * html string used to represent a water tile
     */
    private static final String HTML = "<img alt=\"Water tile\" src=\"images/tiles/WaterTile.png\">";

    @Override
    public String getHtml(boolean visited) {
        if (!visited) {
            return HTML_NOT_VISITED;
        }
        return HTML;
    }

    @Override
    public Status getStatus() {
        return Status.WATER;
    }
}
