package map;

public class WaterTile extends Tile {

    private static final String HTML = "<img alt=\"Grass tile\" src=\"images/tiles/WaterTile.png\">";

    @Override
    public String getHtml() {
        return HTML;
    }

    @Override
    public Status getStatus() {
        return Status.WATER;
    }
}
