package map;

public abstract class Tile {

    public abstract String getHtml();

    public abstract Status getStatus();

    public enum Status {
        GRASS,
        WATER,
        TREASURE
    }
}
