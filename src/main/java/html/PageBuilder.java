package html;

import java.util.List;

import map.Tile;
import player.Direction;
import player.Player;

/**
 * Abstract class used to represent all different builders that follow this Builder pattern
 */
public abstract class PageBuilder {

    /**
     * Getter for the generated Page
     *
     * @return The Page generated by the builder
     */
    public abstract Page getPage();


    /**
     * Method used to load an html template
     *
     * @param resourceName The name of the html resource to load
     * @throws Exception Throws Exception if something goes wrong when loading the template
     */
    abstract void loadTemplate(String resourceName) throws Exception;


    /**
     * Method used to build the header of the html page
     *
     * @param playerId The id of the player to display on the page header
     */
    abstract void buildHeader(String playerId);

    /**
     * Method used to build the map in the html page
     *
     * @param gameMap The map to render in html
     * @param player  The player whose perspective to use for the map generation
     */
    abstract void buildMap(Tile[][] gameMap, Player player);

    /**
     * Method used to build the moves list in the html page
     *
     * @param movesList A list of moves done by the player
     */
    abstract void buildMoves(List<Direction> movesList);
}
