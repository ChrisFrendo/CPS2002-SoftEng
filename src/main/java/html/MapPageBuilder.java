package html;

import map.Tile;
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

        for (Tile[] tiles : gameMap) {
            mapHtml.append("<tr>");
            for (int j = 0; j < size; j++) {
                boolean visited = false;
                if (player.getVisitedTiles().contains(tiles[j]))
                    visited = true;

                mapHtml.append("<td>").append(tiles[j].getHtml(visited)).append("</td>");
            }
            mapHtml.append("</tr>");
        }

        page.html = page.html.replace("$map", mapHtml.toString());
    }
}
