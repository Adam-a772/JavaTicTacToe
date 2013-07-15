import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TicTacToeBoardTest {
    TicTacToeBoard board;
    int[][] boardState;

    @Before
    public void initialize() {
        board = new TicTacToeBoard();
        boardState = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
    }

    @Test
    public void shouldHaveAnArrayOfNegativeOnesWhenConstructed() {
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldChangeCell_0_WhenAMoveIsMadeByPlayer_0() {
        boardState[0][0] = 0;
        board.makeMove(0, 0, 0);
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldChangeCell_0_WhenAMoveIsMadeByPlayer_1() {
        boardState[0][0] = 1;
        board.makeMove(0, 0, 1);
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldChangeCell_1_WhenAMoveIsMadeByPlayer_0() {
        boardState[0][1] = 0;
        board.makeMove(0, 1, 0);
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldChangeCell_8_WhenAMoveIsMadeByPlayer_0() {
        boardState[2][2] = 0;
        board.makeMove(2, 2, 0);
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldChangeCell_7_WhenAMoveIsMadeByPlayer_1() {
        boardState[2][1] = 1;
        board.makeMove(2, 1, 1);
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldChangeCell_048_WhenAMoveIsMadeByPlayer_010() {
        boardState[0][0] = 0;
        boardState[1][1] = 1;
        boardState[2][2] = 0;
        board.makeMove(0, 0, 0);
        board.makeMove(1, 1, 1);
        board.makeMove(2, 2, 0);
        assertArrayEquals(boardState, board.getState());
    }

    @Test
    public void shouldReturnTheFirstRow() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getRow(0));
    }

    @Test
    public void shouldReturnTheFirstRowAfterMove() {
        board.makeMove(0, 1, 0);
        assertArrayEquals(new int[]{-1, 0, -1}, board.getRow(0));
    }

    @Test
    public void shouldReturnTheSecondRowAfterMove() {
        board.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1}, board.getRow(1));
    }

    @Test
    public void shouldReturnTheThirdRowAfterMove() {
        board.makeMove(2, 0, 0);
        board.makeMove(2, 2, 1);
        assertArrayEquals(new int[]{0, -1, 1}, board.getRow(2));
    }

    @Test
    public void shouldReturnTheFirstColumn() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getColumn(0));
    }

    @Test
    public void shouldReturnTheFirstColumnAfterMove() {
        board.makeMove(1, 0, 0);
        assertArrayEquals(new int[]{-1, 0, -1}, board.getColumn(0));
    }

    @Test
    public void shouldReturnTheSecondColumnAfterMove() {
        board.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1}, board.getColumn(1));
    }

    @Test
    public void shouldReturnTheThirdColumnAfterMove() {
        board.makeMove(0, 2, 0);
        board.makeMove(2, 2, 1);
        assertArrayEquals(new int[]{0, -1, 1}, board.getColumn(2));
    }

    @Test
    public void shouldReturnTheForwardDiagonal() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getForwardDiagonal());
    }

    @Test
    public void shouldReturnTheForwardDiagonalAfterMove() {
        board.makeMove(1, 1, 0);
        assertArrayEquals(new int[]{-1, 0, -1}, board.getForwardDiagonal());
    }

    @Test
    public void shouldReturnTheForwardDiagonalAfterAnotherMove() {
        board.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1}, board.getForwardDiagonal());
    }

    @Test
    public void shouldReturnTheForwardDiagonalAfterADifferentMove() {
        board.makeMove(0, 0, 0);
        board.makeMove(2, 2, 1);
        assertArrayEquals(new int[]{0, -1, 1}, board.getForwardDiagonal());
    }

    @Test
    public void shouldReturnTheBackwardDiagonal() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getBackwardDiagonal());
    }

    @Test
    public void shouldReturnTheBackwardDiagonalAfterMove() {
        board.makeMove(1, 1, 0);
        assertArrayEquals(new int[]{-1, 0, -1}, board.getBackwardDiagonal());
    }

    @Test
    public void shouldReturnTheBackwardDiagonalAfterAnotherMove() {
        board.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1}, board.getBackwardDiagonal());
    }

    @Test
    public void shouldReturnTheBackwardDiagonalAfterADifferentMove() {
        board.makeMove(0, 2, 0);
        board.makeMove(2, 0, 1);
        assertArrayEquals(new int[]{0, -1, 1}, board.getBackwardDiagonal());
    }

    @Test
    public void defaultArrayDoesNotHaveWinner() {
        assertFalse(TicTacToeBoard.hasWinner(new int[]{-1, -1, -1}));
    }

    @Test
    public void arrayStartingWithNegOneDoesNotHaveWinner() {
        assertFalse(TicTacToeBoard.hasWinner(new int[]{-1, 0, 0}));
    }

    @Test
    public void arrayEndingWithNegOneDoesNotHaveWinner() {
        assertFalse(TicTacToeBoard.hasWinner(new int[]{1, 1, -1}));
    }

    @Test
    public void arrayWithZerosAndOnesDoesNotHaveWinner() {
        assertFalse(TicTacToeBoard.hasWinner(new int[]{1, 1, 0}));
    }

    @Test
    public void arrayOfOnesIsWinner() {
        assertTrue(TicTacToeBoard.hasWinner(new int[]{1, 1, 1}));
    }

    @Test
    public void arrayOfZerosIsWinner() {
        assertTrue(TicTacToeBoard.hasWinner(new int[]{0, 0, 0}));
    }

    @Test
    public void initiallyBoardHasNoWinner() {
        assertEquals(-1, board.winner());
    }

    @Test
    public void boardWithFirstRowZerosHasWinner() {
        board.setState(new int[][]{{0, 0, 0}, {-1, -1, -1}, {-1, -1, -1}});
        assertEquals(0, board.winner());
    }

    @Test
    public void boardWithTwoOnesHasNoWinner() {
        board.setState(new int[][]{{-1, 1, 1}, {-1, -1, -1}, {-1, -1, -1}});
        assertEquals(-1, board.winner());
    }

    @Test
    public void boardWithFirstColumnZerosHasWinner() {
        board.setState(new int[][]{{0, -1, -1}, {0, -1, -1}, {0, -1, -1}});
        assertEquals(0, board.winner());
    }

    @Test
    public void boardWithSecondColumnZerosHasWinner() {
        board.setState(new int[][]{{-1, 0, -1}, {-1, 0, -1}, {-1, 0, -1}});
        assertEquals(0, board.winner());
    }

    @Test
    public void boardWithSecondRowZerosHasWinner() {
        board.setState(new int[][]{{-1, -1, -1}, {0, 0, 0}, {-1, -1, -1}});
        assertEquals(0, board.winner());
    }

    @Test
    public void boardWithThirdColumnOnesHasWinner() {
        board.setState(new int[][]{{-1, -1, 1}, {-1, -1, 1}, {-1, -1, 1}});
        assertEquals(1, board.winner());
    }

    @Test
    public void boardWithThirdRowOnesHasWinner() {
        board.setState(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {1, 1, 1}});
        assertEquals(1, board.winner());
    }

    @Test
    public void boardWithForwardDiagonalOnesHasWinner() {
        board.setState(new int[][]{{-1, -1, 1}, {-1, 1, -1}, {1, -1, -1}});
        assertEquals(1, board.winner());
    }

    @Test
    public void boardWithForwardDiagonalZerosHasWinner() {
        board.setState(new int[][]{{-1, -1, 0}, {-1, 0, -1}, {0, -1, -1}});
        assertEquals(0, board.winner());
    }

    @Test
    public void boardWithBackwardDiagonalOnesHasWinner() {
        board.setState(new int[][]{{1, -1, -1}, {-1, 1, -1}, {-1, -1, 1}});
        assertEquals(1, board.winner());
    }

    @Test
    public void boardWithBackwardDiagonalZerosHasWinner() {
        board.setState(new int[][]{{0, -1, -1}, {-1, 0, -1}, {-1, -1, 0}});
        assertEquals(0, board.winner());
    }

    @Test
    public void boardTieHasNoWinner() {
        board.setState(new int[][]{{0, 0, 1}, {1, 1, 0}, {0, 1, 0}});
        assertEquals(-1, board.winner());
    }
}
