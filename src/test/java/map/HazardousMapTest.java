package map;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import exceptions.InvalidMapSizeException;

public class HazardousMapTest {

    private Map map;

    @Before
    public void setUp() {
        map = MapCreator.getMapInstance(MapCreator.MapType.HAZARDOUS);
    }

    @After
    public void tearDown() {
        map = map.resetInstance();
    }

    @Test
    public void generateMap() {
        int size = 5;
        map.setMapSize(size, 4);
        Random r = new Random();

        Tile[][] tiles = map.generateMap(r);

        assertEquals(size, tiles.length);
        assertEquals(size, tiles[0].length);
    }

    @Test(expected = InvalidMapSizeException.class)
    public void generateMapInvalidSize() {
        int size = 2;
        map.setMapSize(size, 4);
        Random r = new Random();

        map.generateMap(r);
    }

    @Test
    public void generateMapWaterOverlap() {
        int size = 5;
        map.setMapSize(size, 4);

        Random r = Mockito.mock(Random.class);

        Mockito.when(r.nextDouble()).thenReturn(0.27);
        Mockito.when(r.nextInt(size)).thenReturn(0, 1, 0, 1, 2, 0, 2, 1, 2, 2, 3, 4, 4, 3, 3, 3);

        Tile[][] tiles = map.generateMap(r);

        assertEquals(size, tiles.length);
        assertEquals(size, tiles[0].length);
        assertEquals(Tile.Status.WATER, tiles[2][0].getStatus());
    }

    @Test
    public void generateMapTreasureOverlapWater() {
        int size = 5;
        map.setMapSize(size, 4);

        Random r = Mockito.mock(Random.class);

        Mockito.when(r.nextDouble()).thenReturn(0.27);
        Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 1, 3, 2, 3, 4, 2, 2, 4, 2, 4, 1, 0);

        Tile[][] tiles = map.generateMap(r);

        assertEquals(size, tiles.length);
        assertEquals(size, tiles[0].length);
        assertEquals(tiles[1][0].getStatus(), Tile.Status.TREASURE);
    }

}
