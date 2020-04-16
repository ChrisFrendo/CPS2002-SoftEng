package menu;

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
        boolean response = gameEngine.createPlayers(1);
        assertFalse(response);
    }

    @Test
    public void createPlayersMore() {
        boolean response = gameEngine.createPlayers(9);
        assertFalse(response);
    }

    @Test
    public void createPlayersCorrect() {
        boolean response = gameEngine.createPlayers(5);
        assertTrue(response);
    }

    @Test
    public void setMapSizeTrue() {
        boolean response = gameEngine.setMapSize(5, 2);
        assertTrue(response);
    }

    @Test
    public void setMapSizeFalse() {
        boolean response = gameEngine.setMapSize(1, 1);
        assertFalse(response);
    }
}
