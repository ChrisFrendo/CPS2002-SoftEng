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
public class MapTest {

    public static class mapNonParameterizedTest {
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

            Mockito.when(r.nextDouble()).thenReturn(0.05);
            Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

            map.generateMap(r);

            boolean result = map.isValidStartingPosition(0, 0);

            assertTrue(result);
        }

        @Test
        public void isValidStartingPositionInvalid() {
            int size = 5;
            map.setMapSize(size, 4);

            Random r = Mockito.mock(Random.class);

            Mockito.when(r.nextInt(size)).thenReturn(0, 0, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

            map.generateMap(r);

            boolean result = map.isValidStartingPosition(0, 0);

            assertFalse(result);
        }

        @Test(expected = NullPointerException.class)
        public void isValidStartingPositionNull() {
            map.resetInstance();

            map.isValidStartingPosition(2, 4);
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
        public void parameterTest() {
            Map map = MapCreator.getMapInstance(MapCreator.MapType.SAFE);
            assertEquals(fExpected, map.setMapSize(fInputSize, fInputNumPlayers));
        }
    }
}