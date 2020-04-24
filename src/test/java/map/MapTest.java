package map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.booleanThat;

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
public class MapTest {

    public static class mapNonParameterizedTest {
        private Map map;

        @Before
        public void setUp() {
            map = Map.getInstance();
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

        @Test(expected = InvalidMapSizeException.class)
        public void generateMapInvalidSize() {
            Random r = Mockito.mock(Random.class);

            map.generateMap(r);
        }

        @Test
        public void isValidStartingPosition() {
            int size = 5;
            map.setMapSize(size, 4);

            Random r = Mockito.mock(Random.class);

            Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

            map.generateMap(r);

            boolean result = Map.getInstance().isValidStartingPosition(0, 0);

            assertTrue(result);
        }

        @Test
        public void isValidStartingPositionInvalid() {
            int size = 5;
            map.setMapSize(size, 4);

            Random r = Mockito.mock(Random.class);

            Mockito.when(r.nextInt(size)).thenReturn(0, 0, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

            map.generateMap(r);

            boolean result = Map.getInstance().isValidStartingPosition(0, 0);

            assertFalse(result);
        }

        @Test(expected = NullPointerException.class)
        public void isValidStartingPositionNull() {
            Map.resetInstance();

            Map.getInstance().isValidStartingPosition(2, 4);
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