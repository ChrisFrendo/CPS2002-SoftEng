package player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import map.Map;

public class PlayerTest {
    private Player player1;

    @Before
    public void setUp() {
        player1 = new Player("Player 1");
        Map.getInstance().setMapSize(5, 2);
    }

    @After
    public void tearDown() {
        player1 = null;
        Map.resetInstance();
    }

    @Test
    public void getIdTest() {
        Player player2 = new Player("Player 2");

        assertEquals("Player 1", player1.getId());
        assertEquals("Player 2", player2.getId());
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

    @Test
    public void moveUpSuccess() {
        boolean response = player1.move(Direction.up);

        assertTrue(response);

        int expectedX = 0;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 1;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }


    @Test
    public void moveDownSuccess() {
        boolean response = player1.move(Direction.up);
        assertTrue(response);

        int expectedX = 0;
        int responseX = player1.position.getPositionX();
        assertEquals(expectedX, responseX);

        int expectedY = 1;
        int responseY = player1.position.getPositionY();
        assertEquals(expectedY, responseY);

        response = player1.move(Direction.down);
        assertTrue(response);

        expectedX = 0;
        responseX = player1.position.getPositionX();
        assertEquals(expectedX, responseX);

        expectedY = 0;
        responseY = player1.position.getPositionY();
        assertEquals(expectedY, responseY);
    }

    @Test
    public void moveRightValid() {
        boolean response = player1.move(Direction.right);

        assertTrue(response);

        int expectedX = 1;
        int responseX = player1.position.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.position.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void moveLeftSuccess() {
        boolean response = player1.move(Direction.right);
        assertTrue(response);

        int expectedX = 1;
        int responseX = player1.position.getPositionX();
        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.position.getPositionY();
        assertEquals(expectedY, responseY);

        response = player1.move(Direction.left);
        assertTrue(response);

        expectedX = 0;
        responseX = player1.position.getPositionX();
        assertEquals(expectedX, responseX);

        expectedY = 0;
        responseY = player1.position.getPositionY();
        assertEquals(expectedY, responseY);
    }
}
