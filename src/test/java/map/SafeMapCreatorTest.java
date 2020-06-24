package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SafeMapCreatorTest {
    private SafeMapCreator safeMapCreator;

    @Before
    public void setUp() {
        safeMapCreator = new SafeMapCreator();
    }

    @After
    public void tearDown() {
        safeMapCreator = null;
    }

    @Test
    public void getInstanceTest() {
        Map map = safeMapCreator.getMapInstance();

        assertTrue(map instanceof SafeMap);
    }

    @Test
    public void getInstanceTest2() {
        Map map = safeMapCreator.getMapInstance(5, 2);

        assertTrue(map instanceof SafeMap);
        assertEquals(5, map.size);
    }

    @Test
    public void getInstanceTestInvalidSize() {
        Map map = safeMapCreator.getMapInstance(2, 2);

        assertNull(map);
    }
}
