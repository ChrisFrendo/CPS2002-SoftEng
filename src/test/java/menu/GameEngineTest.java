package menu;

import map.Tile;
import menu.GameEngine;
import org.junit.*;

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
        gameEngine = null;
    }

    @Test
    public void createPlayersLess() {
        boolean response = gameEngine.validatePlayers(1);
        assertFalse(response);
    }

    @Test
    public void createPlayersMore() {
        boolean response = gameEngine.validatePlayers(9);
        assertFalse(response);
    }

    @Test
    public void createPlayersCorrect() {
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
}
