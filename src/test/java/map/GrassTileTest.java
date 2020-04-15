package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GrassTileTest {

    private GrassTile grassTile;

    @Before
    public void setUp() {
        grassTile = new GrassTile();
    }

    @After
    public void tearDown() {
        grassTile = null;
    }

    @Test
    public void getHtml() {
        assertTrue(grassTile.getHtml(false).contains("HiddenTile"));

        assertTrue(grassTile.getHtml(true).contains("GrassTile"));
    }

    @Test
    public void getStatus() {
        assertEquals(Tile.Status.GRASS, grassTile.getStatus());
    }
}