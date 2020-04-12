package html;

import map.Tile;

public class Director {
    private final String MAP_TEMPLATE = "mapPageTemplate.html";
    private PageBuilder builder;

    public Director(PageBuilder builder) {
        this.builder = builder;
    }

    public void construct(Tile[][] gameMap) throws Exception {
        builder.loadTemplate(MAP_TEMPLATE);
        builder.buildHeader();
        builder.buildMap(gameMap);
    }
}
