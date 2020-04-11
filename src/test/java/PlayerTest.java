import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    private Player player1;

    @Before
    public void setUp() {
        player1 = new Player();
    }

    @After
    public void tearDown() {
        player1 = null;
    }

    @Test
    public void initialPosition() {
        int expectedX = 0;
        int responseX = player1.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.getPositionY();

        assertEquals(expectedY, responseY);
    }
}
