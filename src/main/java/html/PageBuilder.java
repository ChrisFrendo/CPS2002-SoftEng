package html;

import map.Tile;

public abstract class PageBuilder {

    public abstract Page getPage();

    abstract void loadTemplate(String resourceName) throws Exception;

    abstract void buildHeader(String playerId);

    abstract void buildMap(Tile[][] gameMap);
}
