package map;

public class GrassTile extends Tile {

    private static final String HTML = "<img alt=\"Grass tile\" src=\"images/tiles/GrassTile.png\">";

    @Override
    public String getHtml() {
        return HTML;
    }

    @Override
    public Status getStatus() {
        return Status.GRASS;
    }
}
