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
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionSuccessX() {
        boolean response = player1.position.setPosition(1, 0);

        assertTrue(response);

        int expectedX = 1;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionSuccessY() {
        boolean response = player1.position.setPosition(0, 1);

        assertTrue(response);

        int expectedX = 0;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 1;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionSuccessXY() {
        boolean response = player1.position.setPosition(1, 1);

        assertTrue(response);

        int expectedX = 1;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 1;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }


    @Test
    public void setPositionFailureX() {
        boolean response = player1.position.setPosition(-1, 0);
        assertFalse(response);

        int expectedX = 0;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionFailureY() {
        boolean response = player1.position.setPosition(0, -1);
        assertFalse(response);

        int expectedX = 0;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }
}
