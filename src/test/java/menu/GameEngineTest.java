package menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import html.GenerateHtmlFiles;
import map.GrassTile;
import map.Tile;
import map.TreasureTile;
import map.WaterTile;
import player.Direction;
import player.Player;
import player.Position;
import utils.FileHelperUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GenerateHtmlFiles.class)
public class GameEngineTest {
    private GameEngine gameEngine;

    @Before
    public void setup() {
        gameEngine = GameEngine.getInstance();
    }

    @After
    public void teardown() {
        gameEngine = gameEngine.resetGameEngine();
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
        Tile[][] response = gameEngine.createMap(5, 2, "safe");
        assertNotNull(response);
    }

    @Test
    public void setMapSizeFalse() {
        Tile[][] response = gameEngine.createMap(1, 1, "safe");
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

    @Test
    public void getInstanceTest() {
        assertEquals(gameEngine, GameEngine.getInstance());
    }

    @Test
    public void createPlayersTest() {
        int x = 5;

        gameEngine.createMap(10, x, "safe");
        gameEngine.createPlayers(x);

        assertEquals(x, gameEngine.getPlayerList().size());
    }

    @Test
    public void writeHtmlTest() {
        // creating a mock singleton object
        GenerateHtmlFiles generateHtmlFiles = Mockito.mock(GenerateHtmlFiles.class);

        // using powermockito to mock static getInstance method to have it return our mock object
        PowerMockito.mockStatic(GenerateHtmlFiles.class);
        PowerMockito.doReturn(generateHtmlFiles).when(GenerateHtmlFiles.class);
        GenerateHtmlFiles.getInstance();


        FileHelperUtils.deleteDirectory("generatedHTML");

        // creating a mock player
        Player player = Mockito.mock(Player.class);

        // mocking player methods
        Mockito.when(player.getId()).thenReturn("Player 1");
        Mockito.when(player.getPastMoves()).thenReturn(new ArrayList<>());
        Mockito.when(player.getCurrentPosition()).thenReturn(new Position(0, 0));

        // mocking generateHtmlFile method to do nothing since it is an inner method
        Mockito.doNothing().when(generateHtmlFiles).generateHtmlFile(any(), any(), any());

        Tile[][] mockBoard = {{new GrassTile(), new GrassTile()}, {new TreasureTile(), new WaterTile()}};
        gameEngine.writeHtml(mockBoard, player, 0);

        FileHelperUtils.deleteDirectory("generatedHTML");
    }

    @Test(expected = RuntimeException.class)
    public void writeHtmlTestFail() {
        FileHelperUtils.deleteDirectory("generatedHTML");

        Player player = Mockito.mock(Player.class);

        Tile[][] mockBoard = {{new TreasureTile(), new WaterTile()}};
        gameEngine.writeHtml(mockBoard, player, 0);

        assertFalse(new File("generatedHTML/map_player_0.html").exists());

        FileHelperUtils.deleteDirectory("generatedHTML");
    }
}
