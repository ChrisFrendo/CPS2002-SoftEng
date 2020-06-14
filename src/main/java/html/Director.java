package html;

import map.Tile;
import player.Player;

/**
 * Used to direct the building of individual components of a given builder
 */
public class Director {

    /**
     * Used to store the actual builder and call its methods in the construct method
     */
    private PageBuilder builder;

    public Director(PageBuilder builder) {
        this.builder = builder;
    }

    /**
     * Method used to construct the page using the builder defined in the constructor
     *
     * @param gameMap The map which is to be represented in html
     * @param player  The player whose perspective this page will be in
     * @throws Exception Throws an exception when loading the html template does not work
     */
    public void construct(Tile[][] gameMap, Player player) throws Exception {
        String MAP_TEMPLATE = "resources/mapPageTemplate.html";
        builder.loadTemplate(MAP_TEMPLATE);

        String playerId = player.getId();

        builder.buildHeader(playerId);

        builder.buildMap(gameMap, player);

        builder.buildMoves(player.getPastMoves());
    }
}
