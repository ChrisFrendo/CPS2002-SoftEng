import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

    private HelloWorld helloWorld;

    @Before
    public void setUp() {
        helloWorld = new HelloWorld();
    }

    @After
    public void tearDown() {
        helloWorld = null;
    }

    @Test
    public void helloWorldTest() {
        String expected = "Hello, World2";
        String response = helloWorld.helloWorld();
        assertEquals(expected, response);
    }
}