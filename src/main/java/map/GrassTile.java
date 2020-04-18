package map;

public class GrassTile extends Tile {

    private static final String HTML = "<img alt=\"Grass tile\" src=\"images/tiles/GrassTile.png\">";
    private static final String HTML_NOT_VISITED = "<img alt=\"Grass tile\" src=\"images/tiles/HiddenTile.png\">";

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
