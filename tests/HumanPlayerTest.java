import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanPlayerTest {
    HumanPlayer player;
    ConsoleIO io;
    StringWriter playerWriter;

    @Before
    public void setUp() {
        initialize("0");
    }

    public void initialize(String inputForPlayer) {
        playerWriter = new StringWriter();
        io = new ConsoleIO(new StringReader(inputForPlayer), playerWriter);
        player = new HumanPlayer("X", io);
    }

    @Test
    public void shouldReturnSymbol() {
        assertEquals("X", player.getSymbol());
    }

    @Test
    public void symbolShouldBeSettable() {
        player = new HumanPlayer("O", io);
        assertEquals("O", player.getSymbol());
    }

    @Test
    public void shouldGetMove() throws IOException {
        assertArrayEquals(new int[]{0, 0}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
    }

    @Test
    public void shouldGetMoveFromInput() throws IOException {
        initialize("8");
        assertArrayEquals(new int[]{2, 2}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
    }

    @Test
    public void shouldAskForMoveWhenGettingMove() throws IOException {
        player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}});
        assertTrue(playerWriter.toString().trim().matches(".*Where.*move.*cell number.*"));
    }

    @Test
    public void shouldNotifyAndRepromptIfBadResponseIsGiven() throws IOException {
        initialize("bad\n3stillbad\n5");
        assertArrayEquals(new int[]{1, 2}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
        BufferedReader reader = new BufferedReader(new StringReader(playerWriter.toString()));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
        assertTrue(reader.readLine().trim().matches(".*not valid.*"));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
        assertTrue(reader.readLine().trim().matches(".*not valid.*"));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
    }

    @Test
    public void shouldNotifyAndRepromptIfNegativeResponseIsGiven() throws IOException {
        initialize("-1\n3\n");
        assertArrayEquals(new int[]{1, 0}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
        BufferedReader reader = new BufferedReader(new StringReader(playerWriter.toString()));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
        assertTrue(reader.readLine().trim().matches(".*not valid.*"));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
    }

    @Test
    public void shouldNotifyAndRepromptIfTooLargeResponseIsGiven() throws IOException {
        initialize("9\n12\n8\n");
        assertArrayEquals(new int[]{2, 2}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
        BufferedReader reader = new BufferedReader(new StringReader(playerWriter.toString()));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
        assertTrue(reader.readLine().trim().matches(".*not valid.*"));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
        assertTrue(reader.readLine().trim().matches(".*not valid.*"));
        assertTrue(reader.readLine().trim().matches(".*Where.*move.*cell number.*"));
    }
}
