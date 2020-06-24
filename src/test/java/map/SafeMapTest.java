package map;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SafeMapTest {
    private Map map;

    @Before
    public void setUp() {
        map = MapCreator.getMapInstance(MapCreator.MapType.SAFE);
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

    @Test
    public void generateMapWaterOverlap() {
        int size = 5;
        map.setMapSize(size, 4);

        Random r = Mockito.mock(Random.class);

        Mockito.when(r.nextDouble()).thenReturn(0.099);
        Mockito.when(r.nextInt(size)).thenReturn(0, 1, 0, 1, 2, 0);

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

        Mockito.when(r.nextDouble()).thenReturn(0.099);
        Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 1, 2, 2, 3);

        Tile[][] tiles = map.generateMap(r);

        assertEquals(size, tiles.length);
        assertEquals(size, tiles[0].length);
        assertEquals(tiles[2][3].getStatus(), Tile.Status.TREASURE);
    }
}
