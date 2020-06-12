package html;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import map.MapCreator;
import menu.GameEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import map.Map;
import map.Tile;
import player.Direction;
import player.Player;

@RunWith(MockitoJUnitRunner.class)
public class DirectorTest {

    @Mock
    private PageBuilder builder;

    @InjectMocks
    private Director director;

    @Before
    public void setUp() {
        Map map = MapCreator.getMapInstance(MapCreator.MapType.SAFE);

        map.setMapSize(5, 2);
        map.generateMap(new Random());
        director = new Director(builder);
    }

    @Test
    public void construct() throws Exception {
        final String MAP_TEMPLATE = "mapPageTemplate.html";
        Player player = Mockito.mock(Player.class);

        List<Direction> directions = new ArrayList<>();

        Mockito.when(player.getId()).thenReturn("Player 1");
        Mockito.when(player.getPastMoves()).thenReturn(directions);

        // generating data
        Tile[][] tiles = {{}};

        // mocking builder methods because we are just testing the director
        Mockito.doNothing().when(builder).buildHeader(anyString());
        Mockito.doNothing().when(builder).buildMap(tiles, player);
        Mockito.doNothing().when(builder).loadTemplate(MAP_TEMPLATE);
        Mockito.doNothing().when(builder).buildMoves(directions);
        // making call
        director.construct(tiles, player);

        // verifying internal methods were called
        Mockito.verify(builder, Mockito.times(1)).buildHeader(anyString());
        Mockito.verify(builder, Mockito.times(1)).buildMap(tiles, player);
        Mockito.verify(builder, Mockito.times(1)).loadTemplate(MAP_TEMPLATE);
        Mockito.verify(builder, Mockito.times(1)).buildMoves(directions);

    }
}