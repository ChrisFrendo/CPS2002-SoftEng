import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void setPositionSuccess() {
        boolean response = player1.setPosition(1, 2);

        assertTrue(response);

        int expectedX = 1;
        int responseX = player1.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 2;
        int responseY = player1.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionFailure() {
        boolean response = player1.setPosition(-1, 0);
        assertFalse(response);

        int expectedX = 0;
        int responseX = player1.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.getPositionY();

        assertEquals(expectedY, responseY);
    }
}
