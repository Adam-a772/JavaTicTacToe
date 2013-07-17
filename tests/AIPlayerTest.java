import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AIPlayerTest {
    AIPlayer player;
    TicTacToeBoard board;

    @Before
    public void initialize(){
        board = new TicTacToeBoard();
        player = new AIPlayer("X", board);
    }

    @Test
    public void shouldReturnSymbol(){
        assertEquals("X", player.getSymbol());
        player = new AIPlayer("O", board);
        assertEquals("O", player.getSymbol());
    }

    @Test
    public void shouldChooseCell0WhenBoardEmpty(){
        assertArrayEquals(new int[]{0, 0}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
    }

    @Test
    public void shouldChooseCell4WhenOnlyCell0Taken(){
        assertArrayEquals(new int[]{1, 1}, player.getMove(new int[][]{{0, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
    }
}
