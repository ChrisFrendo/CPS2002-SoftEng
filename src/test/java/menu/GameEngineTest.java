package menu;

import map.Tile;
import menu.GameEngine;
import player.Direction;
import player.Player;

import org.junit.*;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.Assert.*;

public class GameEngineTest {
    private GameEngine gameEngine;

    @Before
    public void setup() {
        gameEngine = GameEngine.getInstance();
    }

    @After
    public void teardown() {
        gameEngine.resetGameEngine();
    }

    @Test
    public void validatePlayersLess() {
        boolean response = gameEngine.validatePlayers(1);
        assertFalse(response);
    }

    @Test
    public void validatePlayersMore() {
        boolean response = gameEngine.validatePlayers(9);
        assertFalse(response);
    }

    @Test
    public void validatePlayersCorrect() {
        boolean response = gameEngine.validatePlayers(5);
        assertTrue(response);
    }

    @Test
    public void setMapSizeTrue() {
        Tile[][] response = gameEngine.createMap(5, 2);
        assertNotNull(response);
    }

    @Test
    public void setMapSizeFalse() {
        Tile[][] response = gameEngine.createMap(1, 1);
        assertNull(response);
    }

    @Test
    public void handleInputTestSuccess() {
        Player player = Mockito.mock(Player.class);
        gameEngine.getPlayerList().add(player);

        Mockito.when(gameEngine.getPlayerList().get(0).move(Direction.down)).thenReturn(true);
        assertTrue(gameEngine.handleInput('d', 0));
    }

    @Test
    public void handleInputTestFailure() {
        Player player = Mockito.mock(Player.class);
        gameEngine.getPlayerList().add(player);

        Mockito.when(gameEngine.getPlayerList().get(0).move(Direction.down)).thenReturn(true);
        assertFalse(gameEngine.handleInput('e', 0));
    }
}
