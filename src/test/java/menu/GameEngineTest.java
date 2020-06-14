package menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import map.Map;
import map.Tile;
import map.TreasureTile;
import map.WaterTile;
import player.Direction;
import player.Player;
import player.Position;
import team.Team;
import utils.FileHelperUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GenerateHtmlFiles.class)
public class GameEngineTest {
    private GameEngine gameEngine;

    @Before
    public void setup() {
        Map.getInstance();
        gameEngine = GameEngine.getInstance();
    }

    @After
    public void teardown() {
        gameEngine = gameEngine.resetGameEngine();
        Map.resetInstance();
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

        Mockito.when(player.move(Direction.down)).thenReturn(true);
        assertTrue(gameEngine.handleInput('d', player));
    }

    @Test
    public void handleInputTestFailure() {
        Player player = Mockito.mock(Player.class);

        Mockito.when(player.move(Direction.down)).thenReturn(true);
        assertFalse(gameEngine.handleInput('e', player));
    }

    @Test
    public void getInstanceTest() {
        assertEquals(gameEngine, GameEngine.getInstance());
    }

    @Test
    public void createPlayersTest() {
        int x = 5;

        gameEngine.createMap(10, x);
        List<Player> playerList = gameEngine.createPlayers(x);

        assertEquals(x, playerList.size());
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
        gameEngine.writeHtml(mockBoard, player);

        FileHelperUtils.deleteDirectory("generatedHTML");
    }

    @Test(expected = RuntimeException.class)
    public void writeHtmlTestFail() {
        FileHelperUtils.deleteDirectory("generatedHTML");

        Player player = Mockito.mock(Player.class);

        Tile[][] mockBoard = {{new TreasureTile(), new WaterTile()}};
        gameEngine.writeHtml(mockBoard, player);

        assertFalse(new File("generatedHTML/map_player_0.html").exists());

        FileHelperUtils.deleteDirectory("generatedHTML");
    }

    @Test
    public void createTeamsTest() {
        gameEngine.createMap(10, 5);
        gameEngine.createPlayers(5);

        List<Team> teams = gameEngine.createTeams(2);

        assertEquals(2, teams.size());
        assertEquals(3, teams.get(0).getObservers().size());
        assertEquals(2, teams.get(1).getObservers().size());
    }
}
