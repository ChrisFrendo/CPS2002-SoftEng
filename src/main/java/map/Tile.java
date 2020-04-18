package map;

public abstract class Tile {

    public abstract String getHtml(boolean visited);

    public abstract Status getStatus();

    public enum Status {
        GRASS,
        WATER,
        TREASURE
    }
}
