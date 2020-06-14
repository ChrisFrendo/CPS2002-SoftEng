package team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import map.Map;
import map.SafeMap;
import player.Direction;
import player.Player;
import player.Position;

public class TeamTest {

    private Team team;
    private Map map;

    @Before
    public void setUp() {
        map = SafeMap.getInstance();
        team = new Team("Some Team");
    }

    @After
    public void tearDown() {
        team = null;
        map.resetInstance();
    }

    @Test
    public void incrementCurrentPlayerCount() {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        team.attach(player1);
        team.attach(player2);

        team.incrementCurrentPlayerCount();

        Player currentPlayer = team.getCurrentPlayer();

        assertEquals(player2, currentPlayer);
    }

    @Test
    public void incrementCurrentPlayerCountModuloTest() {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        team.attach(player1);
        team.attach(player2);

        team.incrementCurrentPlayerCount();
        team.incrementCurrentPlayerCount();

        Player currentPlayer = team.getCurrentPlayer();

        assertEquals(player1, currentPlayer);
    }

    @Test
    public void getCurrentPlayer() {
        Player player1 = Mockito.mock(Player.class);
        Player player2 = Mockito.mock(Player.class);

        team.attach(player1);
        team.attach(player2);

        Player currentPlayer = team.getCurrentPlayer();

        assertEquals(player1, currentPlayer);
    }

    @Test
    public void getTeamId() {
        assertEquals("Some Team", team.getTeamId());
    }

    @Test
    public void movingPlayersInTeamsTest() throws NoSuchFieldException {
        // creating test data
        Team team = new Team("Team 1");

        Random r = Mockito.mock(Random.class);
        Mockito.when(r.nextDouble()).thenReturn(0.09);
        Mockito.when(r.nextInt(5)).thenReturn(0, 1, 1, 1, 2, 0, 3, 2, 3, 2, 3, 1, 3, 2, 3, 4);

        // generating map
        assertTrue(map.setMapSize(5, 2));
        map.generateMap(r);

        // generating players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // using reflection to force current and starting positions
        FieldSetter.setField(player1, player1.getClass().getDeclaredField("currentPosition"), new Position(0, 3));
        FieldSetter.setField(player2, player2.getClass().getDeclaredField("currentPosition"), new Position(1, 0));
        FieldSetter.setField(player1, player1.getClass().getDeclaredField("startingPosition"), new Position(0, 3));
        FieldSetter.setField(player2, player2.getClass().getDeclaredField("startingPosition"), new Position(1, 0));

        // attaching teams to mocks
        player1.setTeam(team);
        player2.setTeam(team);

        // making separate moves on players
        player1.move(Direction.left);
        player2.move(Direction.right);

        // checking teams are set correctly
        assertEquals(team, player1.getTeam());
        assertEquals(team, player2.getTeam());

        // checking that the tile that player1 went to, ie (0,2) is present in visited tiles of player2
        assertTrue(player2.getVisitedTiles().contains(map.getTile(0, 2)));

        // checking that the tile that player2 went to, ie (1,1) is present in visited tiles of player1
        assertTrue(player1.getVisitedTiles().contains(map.getTile(1, 1)));

    }
}