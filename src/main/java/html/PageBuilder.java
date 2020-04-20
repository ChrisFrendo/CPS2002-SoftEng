package html;

import java.util.List;

import map.Tile;
import player.Direction;
import player.Player;

public abstract class PageBuilder {

    public abstract Page getPage();

    abstract void loadTemplate(String resourceName) throws Exception;

    abstract void buildHeader(String playerId);

    abstract void buildMap(Tile[][] gameMap, Player player);

    abstract void buildMoves(List<Direction> movesList);
}
