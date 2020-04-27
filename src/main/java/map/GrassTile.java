package map;

/**
 * Subclass of Tile used to represent a Grass Tile on the map
 */
public class GrassTile extends Tile {

    /**
     * html string used to represent a grass tile
     */
    private static final String HTML = "<img alt=\"Grass tile\" src=\"images/tiles/GrassTile.png\">";

    @Override
    public String getHtml(boolean visited) {
        if (!visited) {
            return HTML_NOT_VISITED;
        }
        return HTML;
    }

    @Override
    public Status getStatus() {
        return Status.GRASS;
    }
}
