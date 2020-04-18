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

    private Tile[][] tiles;

    @Before
    public void setUp() {
        Map map = Map.getInstance();
        map.setMapSize(5, 2);

        Random r = Mockito.mock(Random.class);
        Mockito.when(r.nextInt(5)).thenReturn(1, 1, 1, 2, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

        tiles = map.generateMap(r);
        player1 = new Player("Player 1");
        player1.currentPosition.setPosition(0, 0);
    }

    @After
    public void tearDown() {
        player1 = null;
        Map.resetInstance();
    }

    @Test
    public void moveWaterTest() {
        // 0,0 -> 1,1
        player1.move(Direction.down);
        boolean result = player1.move(Direction.right);

        assertTrue(result);
        assertEquals(player1.startingPosition.getRow(), player1.currentPosition.getRow());
        assertEquals(player1.startingPosition.getColumn(), player1.currentPosition.getColumn());
    }

    @Test
    public void moveTreasureTest() {
        // 0,0 -> 3,3
        player1.currentPosition.setPosition(3, 3);
        // 3,3 -> 3, 4
        boolean result = player1.move(Direction.right);

        assertTrue(result);
        assertEquals(3, player1.currentPosition.getRow());
        assertEquals(4, player1.currentPosition.getColumn());
        assertTrue(player1.isWinner());
    }

    @Test
    public void generateRandomPositionTest() {

        int row = player1.currentPosition.getRow();
        int column = player1.currentPosition.getColumn();

        assertEquals(tiles[row][column].getStatus(), Tile.Status.GRASS);
    }

    @Test
    public void getIdTest() {
        Player player2 = new Player("Player 2");

        assertEquals("Player 1", player1.getId());
        assertEquals("Player 2", player2.getId());
    }

    @Test
    public void moveUpFailure() {
        // 0, 0
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();


        boolean response = player1.move(Direction.up);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveUpSuccess() {
        player1.currentPosition.setPosition(1, 1);

        // 1, 1
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.up);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertTrue(response);
        assertEquals(currentRow - 1, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveDownFailure() {
        player1.currentPosition.setPosition(4, 0);

        // 4, 0
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.down);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveDownSuccess() {

        // 0, 0
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.down);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertTrue(response);
        assertEquals(currentRow + 1, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveLeftFailure() {
        player1.currentPosition.setPosition(3, 0);

        // 3, 0
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.left);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveLeftSuccess() {
        player1.currentPosition.setPosition(1, 4);

        // 1, 4
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.left);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertTrue(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn - 1, responseColumn);
    }

    @Test
    public void moveRightFailure() {
        player1.currentPosition.setPosition(3, 4);

        // 3, 4
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.right);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveRightSuccess() {
        player1.currentPosition.setPosition(1, 3);

        // 1, 3
        int currentRow = player1.currentPosition.getRow();
        int currentColumn = player1.currentPosition.getColumn();

        boolean response = player1.move(Direction.right);

        int responseRow = player1.currentPosition.getRow();
        int responseColumn = player1.currentPosition.getColumn();

        assertTrue(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn + 1, responseColumn);
    }

    @Test
    public void getVisitedTiles() {
        assertEquals(1, player1.getVisitedTiles().size());

        player1.move(Direction.down);

        assertEquals(2, player1.getVisitedTiles().size());

        assertEquals(player1.getVisitedTiles().get(0).getStatus(), Tile.Status.GRASS);
    }
}
