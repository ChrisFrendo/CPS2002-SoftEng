package html;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import map.GrassTile;
import map.Tile;
import map.TreasureTile;
import map.WaterTile;
import player.Direction;
import player.Player;
import player.Position;

public class MapPageBuilderTest {

    private final String MAP_TEMPLATE = "resources/mapPageTemplate.html";

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

    @Test(expected = IOException.class)
    public void loadTemplateInvalidFile() throws IOException {

        // making call
        mapPageBuilder.loadTemplate("nonExistent.html");

    }

    @Test
    public void buildHeader() throws Exception {
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);
        mapPageBuilder.buildHeader("1");

        assertTrue(mapPageBuilder.getPage().getHtml().contains("Water Tiles"));
    }

    @Test
    public void buildMap() throws Exception {
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);
        GrassTile grassTile = new GrassTile();
        WaterTile waterTile = new WaterTile();
        TreasureTile treasureTile = new TreasureTile();

        Player player = Mockito.mock(Player.class);

        Mockito.when(player.getVisitedTiles()).thenReturn(new ArrayList<>(Arrays.asList(grassTile, waterTile, treasureTile)));
        Mockito.when(player.getCurrentPosition()).thenReturn(new Position(1, 1));


        Tile[][] tiles = {{grassTile, waterTile, new GrassTile(), treasureTile},
                {new GrassTile(), new WaterTile(), new GrassTile(), new GrassTile()},
                {new GrassTile(), new WaterTile(), new GrassTile(), new GrassTile()},
                {new GrassTile(), new WaterTile(), new GrassTile(), new GrassTile()}};

        mapPageBuilder.buildMap(tiles, player);

        assertTrue(mapPageBuilder.getPage().getHtml().contains("<img alt=\"Grass tile\" src=\"images/tiles/GrassTile.png\""));
        assertTrue(mapPageBuilder.getPage().getHtml().contains("<img alt=\"Water tile\" src=\"images/tiles/WaterTile.png\""));
        assertTrue(mapPageBuilder.getPage().getHtml().contains("<img alt=\"Treasure tile\" src=\"images/tiles/TreasureTile.png\""));
    }

    @Test
    public void buildMovesEmpty() throws Exception {
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);

        mapPageBuilder.buildMoves(new ArrayList<>());

        assertFalse(mapPageBuilder.getPage().getHtml().contains("$pastmoves"));
    }

    @Test
    public void buildMoves() throws Exception {
        mapPageBuilder.loadTemplate(MAP_TEMPLATE);

        mapPageBuilder.buildMoves(new ArrayList<>(Arrays.asList(Direction.up, Direction.down)));

        assertFalse(mapPageBuilder.getPage().getHtml().contains("$pastmoves"));
        assertTrue(mapPageBuilder.getPage().getHtml().contains("<tr><td>up</td></tr>"));
        assertTrue(mapPageBuilder.getPage().getHtml().contains("<tr><td>down</td></tr>"));

    }
}