package html;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import map.Tile;
import utils.FileUtils;

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
            URL res = Objects.requireNonNull(getClass().getClassLoader().getResource(resourceName));
            filepath = Paths.get(res.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException | NullPointerException e) {
            System.err.println("Error opening resource: " + e.getMessage());
            throw e;
        }
        page.html = FileUtils.readWholeFile(filepath);

    }


    @Override
    void buildHeader() {
        String playerName = "Player 1";

        page.html = page.html.replace("$header", "<h1>Water Tiles</h1><h2>" + playerName + "</h2>");
    }

    @Override
    void buildMap(Tile[][] gameMap) {
        int size = gameMap.length;
        StringBuilder mapHtml = new StringBuilder();

        for (Tile[] tiles : gameMap) {
            mapHtml.append("<tr>");
            for (int j = 0; j < size; j++) {
                mapHtml.append("<td>").append(tiles[j].getHtml()).append("</td>");
            }
            mapHtml.append("</tr>");
        }

        page.html = page.html.replace("$map", mapHtml.toString());
    }
}
