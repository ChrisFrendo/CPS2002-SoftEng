package player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import map.Map;
import map.Tile;

public class PlayerTest {
    private Player player1;

    private Map map;
    
    @Before
    public void setUp() {
        map = Map.getInstance();
        map.setMapSize(5, 2);
        map.generateMap(new Random());
        player1 = new Player("Player 1");
        player1.currentPosition.setPosition(0, 0);
    }

    @After
    public void tearDown() {
        player1 = null;
        Map.resetInstance();
    }

    @Test
    public void generateRandomPositionTest() {
        int size = 5;
        map.setMapSize(size, 2);

        Random r = Mockito.mock(Random.class);
        Mockito.when(r.nextInt(size)).thenReturn(0, 1, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

        Tile[][] tiles = map.generateMap(r);

        player1 = new Player("Player 1");
        int x = player1.currentPosition.getPositionX();
        int y = player1.currentPosition.getPositionY();

        assertEquals(tiles[x][y].getStatus(), Tile.Status.GRASS);
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
        int responseX = player1.currentPosition.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.currentPosition.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionSuccess() {

        int expectedX = 1;
        int expectedY = 1;

        boolean response = player1.currentPosition.setPosition(1, 1);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertTrue(response);
        assertEquals(expectedX, responseX);
        assertEquals(expectedY, responseY);
    }

    @Test
    public void setPositionFailure() {
        boolean response = player1.currentPosition.setPosition(0, -1);
        assertFalse(response);

        int expectedX = 0;
        int responseX = player1.currentPosition.getPositionX();

        assertEquals(expectedX, responseX);

        int expectedY = 0;
        int responseY = player1.currentPosition.getPositionY();

        assertEquals(expectedY, responseY);
    }

    @Test
    public void moveUpFailure() {
        // 0, 0
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.up);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertFalse(response);
        assertEquals(currentX, responseX);
        assertEquals(currentY, responseY);
    }

    @Test
    public void moveUpSuccess() {
        player1.currentPosition.setPosition(1, 1);

        // 1, 1
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.up);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertTrue(response);
        assertEquals(currentX, responseX);
        assertEquals(currentY - 1, responseY);
    }

    @Test
    public void moveDownFailure() {
        player1.currentPosition.setPosition(0, 4);

        // 0, 4
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.down);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertFalse(response);
        assertEquals(currentX, responseX);
        assertEquals(currentY, responseY);
    }

    @Test
    public void moveDownSuccess() {

        // 0, 0
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.down);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertTrue(response);
        assertEquals(currentX, responseX);
        assertEquals(currentY + 1, responseY);
    }

    @Test
    public void moveLeftFailure() {
        player1.currentPosition.setPosition(0, 3);

        // 0, 3
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.left);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertFalse(response);
        assertEquals(currentX, responseX);
        assertEquals(currentY, responseY);
    }

    @Test
    public void moveLeftSuccess() {
        player1.currentPosition.setPosition(1, 3);

        // 1, 3
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.left);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertTrue(response);
        assertEquals(currentX - 1, responseX);
        assertEquals(currentY, responseY);
    }

    @Test
    public void moveRightFailure() {
        player1.currentPosition.setPosition(4, 3);

        // 4, 3
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.right);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertFalse(response);
        assertEquals(currentX, responseX);
        assertEquals(currentY, responseY);
    }

    @Test
    public void moveRightSuccess() {
        player1.currentPosition.setPosition(1, 3);

        // 1, 3
        int currentX = player1.currentPosition.getPositionX();
        int currentY = player1.currentPosition.getPositionY();

        boolean response = player1.move(Direction.right);

        int responseX = player1.currentPosition.getPositionX();
        int responseY = player1.currentPosition.getPositionY();

        assertTrue(response);
        assertEquals(currentX + 1, responseX);
        assertEquals(currentY, responseY);
    }
}
