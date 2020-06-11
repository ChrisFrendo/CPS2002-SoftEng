package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import exceptions.InvalidMapSizeException;

@RunWith(Enclosed.class)
public class HazardousMapTest {

    public static class mapNonParameterizedTest {
        private Map map;

        @Before
        public void setUp() {
            map = MapCreator.getMapInstance("hazardous");
        }

        @After
        public void tearDown() {
            map = Map.resetInstance();
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
        public void tileExistsTrue() {
            map.setMapSize(5, 2);
            boolean response = map.tileExists(2, 3);

            assertTrue(response);
        }

        @Test
        public void tileExistFalseXGreater() {
            map.setMapSize(5, 2);
            boolean response = map.tileExists(6, 1);

            assertFalse(response);
        }

        @Test
        public void tileExistFalseYGreater() {
            map.setMapSize(5, 2);
            boolean response = map.tileExists(2, 7);

            assertFalse(response);
        }

        @Test
        public void tileExistFalseXNegative() {
            map.setMapSize(5, 2);
            boolean response = map.tileExists(-2, 1);

            assertFalse(response);
        }

        @Test
        public void tileExistFalseYNegative() {
            map.setMapSize(5, 2);
            boolean response = map.tileExists(2, -1);

            assertFalse(response);
        }

        /*
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
        */


        @Test
        public void generateMapTreasureOverlapWater() {
            int size = 5;
            map.setMapSize(size, 4);

            Random r = new Random();


            Tile[][] tiles = map.generateMap(r);

            assertEquals(size, tiles.length);
            assertEquals(size, tiles[0].length);
        }


        @Test(expected = InvalidMapSizeException.class)
        public void generateMapInvalidSize() {
            Random r = Mockito.mock(Random.class);

            map.generateMap(r);
        }
    }
}
