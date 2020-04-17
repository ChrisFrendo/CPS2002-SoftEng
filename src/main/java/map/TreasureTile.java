package map;

public class TreasureTile extends Tile {

    private static final String HTML = "<img alt=\"Treasure tile\" src=\"images/tiles/TreasureTile.png\">";
    private static final String HTML_NOT_VISITED = "<img alt=\"Treasure tile\" src=\"images/tiles/HiddenTile.png\">";

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
