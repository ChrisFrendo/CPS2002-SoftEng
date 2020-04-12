package html;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import map.Tile;

@RunWith(MockitoJUnitRunner.class)
public class DirectorTest {

    @Mock
    private PageBuilder builder;

    @InjectMocks
    private Director director;

    @Before
    public void setUp() {
        director = new Director(builder);
    }

    @Test
    public void construct() throws Exception {
        final String MAP_TEMPLATE = "mapPageTemplate.html";

        // generating data
        Tile[][] tiles = {{}};

        // mocking builder methods
        Mockito.doNothing().when(builder).buildHeader();
        Mockito.doNothing().when(builder).buildMap(tiles);
        Mockito.doNothing().when(builder).loadTemplate(MAP_TEMPLATE);

        // making call
        director.construct(tiles);

        // verifying internal methods were called
        Mockito.verify(builder, Mockito.times(1)).buildHeader();
        Mockito.verify(builder, Mockito.times(1)).buildMap(tiles);
        Mockito.verify(builder, Mockito.times(1)).loadTemplate(MAP_TEMPLATE);
    }
}