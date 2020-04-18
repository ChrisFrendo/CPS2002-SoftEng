package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WaterTileTest {

    private WaterTile waterTile;

    @Before
    public void setUp() {
        waterTile = new WaterTile();
    }

    @After
    public void tearDown() {
        waterTile = null;
    }

    @Test
    public void getHtml() {
        assertTrue(waterTile.getHtml(false).contains("HiddenTile"));

        assertTrue(waterTile.getHtml(true).contains("WaterTile"));
    }

    @Test
    public void getStatus() {
        assertEquals(Tile.Status.WATER, waterTile.getStatus());
    }
}