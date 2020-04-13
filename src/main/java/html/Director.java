package html;

import map.Tile;
import player.Player;

public class Director {
    private final String MAP_TEMPLATE = "mapPageTemplate.html";
    private PageBuilder builder;

    public Director(PageBuilder builder) {
        this.builder = builder;
    }

    public void construct(Tile[][] gameMap, Player player) throws Exception {
        builder.loadTemplate(MAP_TEMPLATE);

        String playerId = player.getId();

        builder.buildHeader(playerId);

        builder.buildMap(gameMap);
    }
}
