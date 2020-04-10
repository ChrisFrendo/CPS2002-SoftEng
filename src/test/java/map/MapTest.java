package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

@RunWith(Enclosed.class)
public class MapTest {

    public static class mapNonParameterizedTest {
        private Map map;

        @Before
        public void setUp() {
            map = Map.getInstance();
        }

        @After
        public void tearDown() {
            map = null;
        }

        @Test
        public void getInstance() {
            assertNotNull(map);
        }

        @Test
        public void getInstanceNotNull() {
            Map map1 = Map.getInstance();

            assertEquals(map, map1);
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

            Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

            Tile[][] tiles = map.generateMap(r);

            assertEquals(size, tiles.length);
            assertEquals(size, tiles[0].length);
        }

        @Test
        public void generateMapTreasureOverlapWater() {
            int size = 5;
            map.setMapSize(size, 4);

            Random r = Mockito.mock(Random.class);

            Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 2, 0, 2, 3, 3, 2, 3, 2, 3, 4);

            Tile[][] tiles = map.generateMap(r);

            assertEquals(size, tiles.length);
            assertEquals(size, tiles[0].length);
        }
    }

    @RunWith(Parameterized.class)
    public static class setMapSizeParameterizedTest {
        @Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {4, 2, false}, {51, 2, false}, {6, 5, false}, {30, 1, false}, {30, 9, false},
                    {10, 4, true}, {20, 7, true}
            });
        }

        @Parameter
        public int fInputSize;

        @Parameter(1)
        public int fInputNumPlayers;

        @Parameter(2)
        public boolean fExpected;

        @Test
        public void test() {
            assertEquals(fExpected, Map.getInstance().setMapSize(fInputSize, fInputNumPlayers));
        }
    }
}