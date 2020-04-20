package html;

import java.util.List;

import map.Tile;
import player.Direction;
import player.Player;
import utils.FileHelperUtils;

public class MapPageBuilder extends PageBuilder {
    private Page page = new Page();

    @Override
    public Page getPage() {
        return page;
    }

    @Override
    void loadTemplate(String resourceName) throws Exception {

        String filepath;
        try {
            filepath = FileHelperUtils.getResourceFilePath(resourceName);
        } catch (NullPointerException e) {
            System.err.println("Error opening resource: " + e.getMessage());
            throw e;
        }
        page.html = FileHelperUtils.readWholeFile(filepath);

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

        movesList.forEach(direction -> movesHtml.append("<tr><td>").append(direction.toString()).append("</td></tr>"));

        page.html = page.html.replace("$pastmoves", movesHtml);
    }
}
