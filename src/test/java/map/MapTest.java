package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

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