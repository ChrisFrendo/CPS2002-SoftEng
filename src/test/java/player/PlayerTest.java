package player;

import map.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player1;

    @Before
    public void setUp() {
        player1 = new Player();
        Map.getInstance().setMapSize(5, 2);
    }

    @After
    public void tearDown() {
        player1 = null;
        Map.resetInstance();
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
    public void setPositionSuccess() {
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
    public void setPositionFailure() {
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
