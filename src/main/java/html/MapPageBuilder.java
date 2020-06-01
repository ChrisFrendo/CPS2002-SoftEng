package html;

import java.io.IOException;
import java.util.List;

import map.Tile;
import player.Direction;
import player.Player;
import utils.FileHelperUtils;

/**
 * A concrete builder used to generate the html web page
 */
public class MapPageBuilder extends PageBuilder {

    /**
     * An instance of Page used to store the current page and its html
     */
    private Page page = new Page();

    @Override
    public Page getPage() {
        return page;
    }

    @Override
    void loadTemplate(String resourceName) throws IOException {
        page.html = FileHelperUtils.readWholeFile(resourceName);
    }

    @Override
    void buildHeader(String playerId) {

        page.html = page.html.replace("$header", "<h1>Water Tiles</h1><h2>" + playerId + "</h2>");
    }

    @Override
    void buildMap(Tile[][] gameMap, Player player) {
        int size = gameMap.length;
        StringBuilder mapHtml = new StringBuilder();

        for (int i = 0; i < size; i++) {
            mapHtml.append("<tr>");
            for (int j = 0; j < size; j++) {
                boolean visited = false;
                if (player.getVisitedTiles().contains(gameMap[i][j]))
                    visited = true;

                String html = gameMap[i][j].getHtml(visited);
                if (player.getCurrentPosition().getRow() == i && player.getCurrentPosition().getColumn() == j)
                    html = html.replace("GrassTile", "GrassTilePlayer");

                mapHtml.append("<td>").append(html).append("</td>");
            }
            mapHtml.append("</tr>");
        }

        page.html = page.html.replace("$map", mapHtml.toString());
    }

    @Override
    void buildMoves(List<Direction> movesList) {
        StringBuilder movesHtml = new StringBuilder();

        for (int i = movesList.size() - 1; i >= 0; i--) {
            movesHtml.append("<tr><td>").append(movesList.get(i).toString()).append("</td></tr>");
        }


        page.html = page.html.replace("$pastmoves", movesHtml);
    }
}
