package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TreasureTileTest {
    private TreasureTile treasureTile;

    @Before
    public void setUp() {
        treasureTile = new TreasureTile();
    }

    @After
    public void tearDown() {
        treasureTile = null;
    }

    @Test
    public void getHtml() {
        assertTrue(treasureTile.getHtml(false).contains("HiddenTile"));

        assertTrue(treasureTile.getHtml(true).contains("TreasureTile"));
    }

    @Test
    public void getStatus() {
        assertEquals(Tile.Status.TREASURE, treasureTile.getStatus());
    }
}