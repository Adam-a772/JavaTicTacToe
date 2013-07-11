import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameStateLogicTest {
    GameStateLogic gameStateLogic;
    MockBoard board;

    @Before
    public void initialize() {
        gameStateLogic = new GameStateLogic();
        board = new MockBoard();
    }

    @Test
    public void defaultArrayDoesNotHaveWinner() {
        assertFalse(gameStateLogic.hasWinner(new int[]{-1, -1, -1}));
    }

    @Test
    public void arrayStartingWithNegOneDoesNotHaveWinner() {
        assertFalse(gameStateLogic.hasWinner(new int[]{-1, 0, 0}));
    }

    @Test
    public void arrayEndingWithNegOneDoesNotHaveWinner() {
        assertFalse(gameStateLogic.hasWinner(new int[]{1, 1, -1}));
    }

    @Test
    public void arrayWithZerosAndOnesDoesNotHaveWinner() {
        assertFalse(gameStateLogic.hasWinner(new int[]{1, 1, 0}));
    }

    @Test
    public void arrayOfOnesIsWinner() {
        assertTrue(gameStateLogic.hasWinner(new int[]{1, 1, 1}));
    }

    @Test
    public void arrayOfZerosIsWinner() {
        assertTrue(gameStateLogic.hasWinner(new int[]{0, 0, 0}));
    }

    @Test
    public void initiallyBoardHasNoWinner() {
        assertEquals(-1, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithFirstRowZerosHasWinner() {
        board.setState(new int[][]{{0, 0, 0}, {-1, -1, -1}, {-1, -1, -1}});
        assertEquals(0, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithTwoOnesHasNoWinner() {
        board.setState(new int[][]{{-1, 1, 1}, {-1, -1, -1}, {-1, -1, -1}});
        assertEquals(-1, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithFirstColumnZerosHasWinner() {
        board.setState(new int[][]{{0, -1, -1}, {0, -1, -1}, {0, -1, -1}});
        assertEquals(0, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithSecondColumnZerosHasWinner() {
        board.setState(new int[][]{{-1, 0, -1}, {-1, 0, -1}, {-1, 0, -1}});
        assertEquals(0, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithSecondRowZerosHasWinner() {
        board.setState(new int[][]{{-1, -1, -1}, {0, 0, 0}, {-1, -1, -1}});
        assertEquals(0, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithThirdColumnOnesHasWinner() {
        board.setState(new int[][]{{-1, -1, 1}, {-1, -1, 1}, {-1, -1, 1}});
        assertEquals(1, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithThirdRowOnesHasWinner() {
        board.setState(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {1, 1, 1}});
        assertEquals(1, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithForwardDiagonalOnesHasWinner() {
        board.setState(new int[][]{{-1, -1, 1}, {-1, 1, -1}, {1, -1, -1}});
        assertEquals(1, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithForwardDiagonalZerosHasWinner() {
        board.setState(new int[][]{{-1, -1, 0}, {-1, 0, -1}, {0, -1, -1}});
        assertEquals(0, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithBackwardDiagonalOnesHasWinner() {
        board.setState(new int[][]{{1, -1, -1}, {-1, 1, -1}, {-1, -1, 1}});
        assertEquals(1, gameStateLogic.winner(board));
    }

    @Test
    public void boardWithBackwardDiagonalZerosHasWinner() {
        board.setState(new int[][]{{0, -1, -1}, {-1, 0, -1}, {-1, -1, 0}});
        assertEquals(0, gameStateLogic.winner(board));
    }

    @Test
    public void boardTieHasNoWinner() {
        board.setState(new int[][]{{0, 0, 1}, {1, 1, 0}, {0, 1, 0}});
        assertEquals(-1, gameStateLogic.winner(board));
    }
}
