package player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import map.MapCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import map.Map;
import map.Tile;
import menu.GameEngine;
import utils.Path;

public class PlayerTest {
    private Player player1;

    private Tile[][] tiles;

    private Map map;

    @Before
    public void setUp() {
        map = MapCreator.getMapInstance(MapCreator.MapType.SAFE);
        map.setMapSize(5, 2);

        Random r = Mockito.mock(Random.class);
        Mockito.when(r.nextDouble()).thenReturn(0.09);
        Mockito.when(r.nextInt(5)).thenReturn(1, 1, 1, 2, 2, 0);

        tiles = map.generateMap(r);
        player1 = new Player("Player 1");
        player1.getCurrentPosition().setPosition(0, 0);
    }

    @After
    public void tearDown() {
        player1 = null;
        map = map.resetInstance();
    }

    @Test
    public void moveWaterTest() {
        // 0,0 -> 1,1
        player1.move(Direction.down);
        boolean result = player1.move(Direction.right);

        assertTrue(result);
        assertEquals(player1.startingPosition.getRow(), player1.getCurrentPosition().getRow());
        assertEquals(player1.startingPosition.getColumn(), player1.getCurrentPosition().getColumn());
    }

    @Test
    public void moveTreasureTest() {
        // 0,0 -> 1,0
        player1.getCurrentPosition().setPosition(1, 0);
        // 1,0 -> 2, 0
        boolean result = player1.move(Direction.down);

        assertTrue(result);
        assertEquals(2, player1.getCurrentPosition().getRow());
        assertEquals(0, player1.getCurrentPosition().getColumn());
        assertTrue(player1.isWinner());
    }

    @Test
    public void generateRandomPositionTest() {

        int row = player1.getCurrentPosition().getRow();
        int column = player1.getCurrentPosition().getColumn();

        assertEquals(tiles[row][column].getStatus(), Tile.Status.GRASS);
        assertTrue(Path.isPath(tiles, row, column, tiles.length));
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
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();


        boolean response = player1.move(Direction.up);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveUpSuccess() {
        player1.getCurrentPosition().setPosition(1, 1);

        // 1, 1
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();

        boolean response = player1.move(Direction.up);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertTrue(response);
        assertEquals(currentRow - 1, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveDownFailure() {
        player1.getCurrentPosition().setPosition(4, 0);

        // 4, 0
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();

        boolean response = player1.move(Direction.down);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveDownSuccess() {

        // 0, 0
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();

        boolean response = player1.move(Direction.down);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertTrue(response);
        assertEquals(currentRow + 1, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveLeftFailure() {
        player1.getCurrentPosition().setPosition(3, 0);

        // 3, 0
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();

        boolean response = player1.move(Direction.left);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
    }

    @Test
    public void moveLeftSuccess() {
        player1.getCurrentPosition().setPosition(1, 4);

        // 1, 4
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();

        boolean response = player1.move(Direction.left);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertTrue(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn - 1, responseColumn);
    }

    @Test
    public void moveRightFailure() {
        player1.getCurrentPosition().setPosition(3, 4);

        // 3, 4
        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();
        int pastMovesSize = player1.getPastMoves().size();
      
        boolean response = player1.move(Direction.right);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertFalse(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn, responseColumn);
        assertEquals(pastMovesSize, player1.getPastMoves().size());

    }

    @Test
    public void moveRightSuccess() {
        player1.getCurrentPosition().setPosition(1, 3);

        // 1, 3

        int currentRow = player1.getCurrentPosition().getRow();
        int currentColumn = player1.getCurrentPosition().getColumn();
        int pastMovesSize = player1.getPastMoves().size();

        boolean response = player1.move(Direction.right);

        int responseRow = player1.getCurrentPosition().getRow();
        int responseColumn = player1.getCurrentPosition().getColumn();

        assertTrue(response);
        assertEquals(currentRow, responseRow);
        assertEquals(currentColumn + 1, responseColumn);
        assertEquals(pastMovesSize + 1, player1.getPastMoves().size());
        assertEquals(Direction.right, player1.getPastMoves().get(pastMovesSize));
    }

    @Test
    public void getVisitedTiles() {
        assertEquals(1, player1.getVisitedTiles().size());

        player1.move(Direction.down);

        assertEquals(2, player1.getVisitedTiles().size());

        assertEquals(player1.getVisitedTiles().get(0).getStatus(), Tile.Status.GRASS);
    }
}
