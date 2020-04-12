package html;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import map.GrassTile;
import map.Tile;
import map.TreasureTile;
import map.WaterTile;

public class MapPageBuilderTest {

    private final String MAP_TEMPLATE = "mapPageTemplate.html";

    private MapPageBuilder mapPageBuilder;

    @Before
    public void setUp() {
        mapPageBuilder = new MapPageBuilder();
    }

    @After
    public void tearDown() {
        mapPageBuilder = null;
    }

    @Test
    public void getPage() {
        Page page = mapPageBuilder.getPage();

        assertNotNull(page);
    }

    @Test
    public void loadTemplate() throws Exception {

        // making call
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);

        // assert
        assertNotNull(mapPageBuilder.getPage().getHtml());
    }

    @Test(expected = NullPointerException.class)
    public void loadTemplateInvalidFile() throws Exception {

        // making call
        mapPageBuilder.loadTemplate("nonExistent.html");

    }

    @Test
    public void buildHeader() throws Exception {
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);
        mapPageBuilder.buildHeader();

        assertTrue(mapPageBuilder.getPage().getHtml().contains("Water Tiles"));
    }

    @Test
    public void buildMap() throws Exception {
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);

        Tile[][] tiles = {{new GrassTile(), new WaterTile(), new GrassTile(), new TreasureTile()},
                {new GrassTile(), new WaterTile(), new GrassTile(), new GrassTile()},
                {new GrassTile(), new WaterTile(), new GrassTile(), new GrassTile()},
                {new GrassTile(), new WaterTile(), new GrassTile(), new GrassTile()}};

        mapPageBuilder.buildMap(tiles);

        assertTrue(mapPageBuilder.getPage().getHtml().contains("<img alt=\"Grass tile\" src=\"images/tiles/GrassTile.png\""));
        assertTrue(mapPageBuilder.getPage().getHtml().contains("<img alt=\"Grass tile\" src=\"images/tiles/WaterTile.png\""));
        assertTrue(mapPageBuilder.getPage().getHtml().contains("<img alt=\"Grass tile\" src=\"images/tiles/TreasureTile.png\""));
    }
}