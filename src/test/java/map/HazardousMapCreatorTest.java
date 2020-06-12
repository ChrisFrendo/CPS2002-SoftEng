package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HazardousMapCreatorTest {
    private HazardousMapCreator hazardousMapCreator;
    private Map map;

    @Before
    public void setUp() {
        hazardousMapCreator = new HazardousMapCreator();
    }

    @After
    public void tearDown() {
        hazardousMapCreator = null;
        if (map != null)
            map = map.resetInstance();
    }

    @Test
    public void getInstanceTest() {
        map = hazardousMapCreator.getMapInstance();

        assertTrue(map instanceof HazardousMap);
    }

    @Test
    public void getInstanceTest2() {
        map = hazardousMapCreator.getMapInstance(5, 2);

        assertTrue(map instanceof HazardousMap);
        assertEquals(5, map.size);
    }

    @Test
    public void getInstanceTestInvalidSize() {
        map = hazardousMapCreator.getMapInstance(2, 2);

        assertNull(map);
    }

}