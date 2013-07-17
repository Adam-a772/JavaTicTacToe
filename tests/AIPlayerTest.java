import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

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

    @Test
    public void shouldChooseLastCellWhenOnlyCellLeft(){
        assertArrayEquals(new int[]{0, 0}, player.getMove(new int[][]{{-1, 1, 0}, {0, 1, 1}, {1, 0, 0}}));
        assertArrayEquals(new int[]{2, 0}, player.getMove(new int[][]{{0, 1, 0}, {1, 1, 0}, {-1, 0, 1}}));
        assertArrayEquals(new int[]{2, 2}, player.getMove(new int[][]{{0, 0, 1}, {1, 1, 0}, {0, 1, -1}}));
        assertArrayEquals(new int[]{0, 2}, player.getMove(new int[][]{{1, 0, -1}, {0, 1, 1}, {0, 1, 0}}));
        assertArrayEquals(new int[]{1, 2}, player.getMove(new int[][]{{1, 0, 1}, {1, 0, -1}, {0, 1, 0}}));
        assertArrayEquals(new int[]{0, 1}, player.getMove(new int[][]{{1, -1, 0}, {0, 0, 1}, {1, 1, 0}}));
        assertArrayEquals(new int[]{1, 0}, player.getMove(new int[][]{{0, 1, 0}, {-1, 0, 1}, {1, 0, 1}}));
        assertArrayEquals(new int[]{2, 1}, player.getMove(new int[][]{{0, 1, 1}, {1, 0, 0}, {0, -1, 1}}));
    }

    @Test
    public void shouldBeAbleToDetermineWhoseTurnItIs(){
        assertEquals(0, player.currentPlayer(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
        assertEquals(1, player.currentPlayer(new int[][]{{0, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
        assertEquals(0, player.currentPlayer(new int[][]{{0, -1, -1}, {-1, 1, -1}, {-1, -1, -1}}));
        assertEquals(1, player.currentPlayer(new int[][]{{0, -1, -1}, {-1, 1, -1}, {-1, -1, 0}}));
        assertEquals(0, player.currentPlayer(new int[][]{{0, -1, -1}, {1, 1, -1}, {-1, -1, 0}}));
        assertEquals(1, player.currentPlayer(new int[][]{{0, -1, -1}, {1, 1, 0}, {-1, -1, 0}}));
        assertEquals(0, player.currentPlayer(new int[][]{{0, -1, 1}, {1, 1, 0}, {-1, -1, 0}}));
        assertEquals(1, player.currentPlayer(new int[][]{{0, -1, 1}, {1, 1, 0}, {0, -1, 0}}));
        assertEquals(0, player.currentPlayer(new int[][]{{0, -1, 1}, {1, 1, 0}, {0, 1, 0}}));
        assertEquals(1, player.currentPlayer(new int[][]{{0, 0, 1}, {1, 1, 0}, {0, 1, 0}}));
    }

    @Test
    public void shouldPerformDeepCopy(){
        int[][] intArr = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        int[][] copy = AIPlayer.deep2DArrayCopy(intArr);
        assertArrayEquals(intArr, copy);
        assertNotSame(intArr[0], copy[0]);
    }

    @Test
    public void shouldChooseWinningMove(){
        assertArrayEquals(new int[]{2, 2}, player.getMove(new int[][]{{0, -1, 1}, {0, 0, 1}, {1, -1, -1}}));
        assertArrayEquals(new int[]{0, 2}, player.getMove(new int[][]{{1, 1, -1}, {-1, 0, -1}, {0, 0, 1}}));
        assertArrayEquals(new int[]{2, 0}, player.getMove(new int[][]{{1, 0, 0}, {-1, 0, -1}, {-1, 1, 1}}));

    }
}
