package map;

public class WaterTile extends Tile {

    private static final String HTML = "<img alt=\"Water tile\" src=\"images/tiles/WaterTile.png\">";
    private static final String HTML_NOT_VISITED = "<img alt=\"Water tile\" src=\"images/tiles/HiddenTile.png\">";

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
