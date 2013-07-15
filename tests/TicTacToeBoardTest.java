import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TicTacToeBoardTest {
    TicTacToeBoard board;
    int[][] boardState;
    TicTacToeBoard board4;
    int[][] boardState4;

    @Before
    public void initialize() {
        board = new TicTacToeBoard(3);
        boardState = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        board4 = new TicTacToeBoard(4);
        boardState4 = new int[][]{{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}};
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
        assertFalse(board.hasWinner(new int[]{-1, -1, -1}));
    }

    @Test
    public void arrayStartingWithNegOneDoesNotHaveWinner() {
        assertFalse(board.hasWinner(new int[]{-1, 0, 0}));
    }

    @Test
    public void arrayEndingWithNegOneDoesNotHaveWinner() {
        assertFalse(board.hasWinner(new int[]{1, 1, -1}));
    }

    @Test
    public void arrayWithZerosAndOnesDoesNotHaveWinner() {
        assertFalse(board.hasWinner(new int[]{1, 1, 0}));
    }

    @Test
    public void arrayOfOnesIsWinner() {
        assertTrue(board.hasWinner(new int[]{1, 1, 1}));
    }

    @Test
    public void arrayOfZerosIsWinner() {
        assertTrue(board.hasWinner(new int[]{0, 0, 0}));
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

    // 4x4 tests
    @Test
    public void _4x4shouldHaveAnArrayOfNegativeOnesWhenConstructed() {
        assertArrayEquals(boardState4, board4.getState());
    }

    @Test
    public void _4x4shouldChangeCell_15_WhenAMoveIsMadeByPlayer_0() {
        boardState4[3][3] = 0;
        board4.makeMove(3, 3, 0);
        assertArrayEquals(boardState4, board4.getState());
    }

    @Test
    public void _4x4shouldChangeCell_15_WhenAMoveIsMadeByPlayer_1() {
        boardState4[3][3] = 1;
        board4.makeMove(3, 3, 1);
        assertArrayEquals(boardState4, board4.getState());
    }

    @Test
    public void _4x4shouldReturnTheFirstRow() {
        assertArrayEquals(new int[]{-1, -1, -1, -1}, board4.getRow(0));
    }

    @Test
    public void _4x4shouldReturnTheFirstRowAfterMove() {
        board4.makeMove(0, 1, 0);
        assertArrayEquals(new int[]{-1, 0, -1, -1}, board4.getRow(0));
    }

    @Test
    public void _4x4shouldReturnTheSecondRowAfterMove() {
        board4.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1, -1}, board4.getRow(1));
    }

    @Test
    public void _4x4shouldReturnTheFourthRowAfterMove() {
        board4.makeMove(3, 0, 0);
        board4.makeMove(3, 3, 1);
        assertArrayEquals(new int[]{0, -1, -1, 1}, board4.getRow(3));
    }

    @Test
    public void _4x4shouldReturnTheFirstColumn() {
        assertArrayEquals(new int[]{-1, -1, -1, -1}, board4.getColumn(0));
    }

    @Test
    public void _4x4shouldReturnTheFirstColumnAfterMove() {
        board4.makeMove(3, 0, 0);
        assertArrayEquals(new int[]{-1, -1, -1, 0}, board4.getColumn(0));
    }

    @Test
    public void _4x4shouldReturnTheSecondColumnAfterMove() {
        board4.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1, -1}, board4.getColumn(1));
    }

    @Test
    public void _4x4shouldReturnTheFourthColumnAfterMove() {
        board4.makeMove(0, 3, 0);
        board4.makeMove(3, 3, 1);
        assertArrayEquals(new int[]{0, -1, -1, 1}, board4.getColumn(3));
    }

    @Test
    public void _4x4shouldReturnTheForwardDiagonal() {
        assertArrayEquals(new int[]{-1, -1, -1, -1}, board4.getForwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheForwardDiagonalAfterMove() {
        board4.makeMove(1, 1, 0);
        assertArrayEquals(new int[]{-1, 0, -1, -1}, board4.getForwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheForwardDiagonalAfterAnotherMove() {
        board4.makeMove(1, 1, 1);
        assertArrayEquals(new int[]{-1, 1, -1, -1}, board4.getForwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheForwardDiagonalAfterADifferentMove() {
        board4.makeMove(0, 0, 0);
        board4.makeMove(3, 3, 1);
        assertArrayEquals(new int[]{0, -1, -1, 1}, board4.getForwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheBackwardDiagonal() {
        assertArrayEquals(new int[]{-1, -1, -1, -1}, board4.getBackwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheBackwardDiagonalAfterMove() {
        board4.makeMove(1, 2, 0);
        assertArrayEquals(new int[]{-1, 0, -1, -1}, board4.getBackwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheBackwardDiagonalAfterAnotherMove() {
        board4.makeMove(1, 2, 1);
        assertArrayEquals(new int[]{-1, 1, -1, -1}, board4.getBackwardDiagonal());
    }

    @Test
    public void _4x4shouldReturnTheBackwardDiagonalAfterADifferentMove() {
        board4.makeMove(0, 3, 0);
        board4.makeMove(3, 0, 1);
        assertArrayEquals(new int[]{0, -1, -1, 1}, board4.getBackwardDiagonal());
    }

    @Test
    public void _4x4defaultArrayDoesNotHaveWinner() {
        assertFalse(board4.hasWinner(new int[]{-1, -1, -1, -1}));
    }

    @Test
    public void _4x4arrayStartingWithNegOneDoesNotHaveWinner() {
        assertFalse(board4.hasWinner(new int[]{-1, 0, 0, 0}));
    }

    @Test
    public void _4x4arrayEndingWithNegOneDoesNotHaveWinner() {
        assertFalse(board4.hasWinner(new int[]{1, 1, 1, -1}));
    }

    @Test
    public void _4x4arrayWithZerosAndOnesDoesNotHaveWinner() {
        assertFalse(board4.hasWinner(new int[]{1, 1, 0, 0}));
    }

    @Test
    public void _4x4arrayOfOnesIsWinner() {
        assertTrue(board4.hasWinner(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void _4x4arrayOfZerosIsWinner() {
        assertTrue(board4.hasWinner(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void _4x4initiallyBoardHasNoWinner() {
        assertEquals(-1, board4.winner());
    }

    @Test
    public void _4x4boardWithFirstRowZerosHasWinner() {
        board4.setState(new int[][]{{0, 0, 0, 0}, {-1, -1, -1, -1}, {-1, -1, -1, -1}});
        assertEquals(0, board4.winner());
    }

    @Test
    public void _4x4boardWithFourthColumnOnesHasWinner() {
        board4.setState(new int[][]{{-1, -1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}, {-1, -1, -1, 1}});
        assertEquals(1, board4.winner());
    }

    @Test
    public void _4x4boardWithFourthRowOnesHasWinner() {
        board4.setState(new int[][]{{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {1, 1, 1, 1}});
        assertEquals(1, board4.winner());
    }

    @Test
    public void _4x4boardWithForwardDiagonalOnesHasWinner() {
        board4.setState(new int[][]{{-1, -1, -1, 1}, {-1, -1, 1, -1}, {-1, 1, -1, -1}, {1, -1, -1, -1}});
        assertEquals(1, board4.winner());
    }

    @Test
    public void _4x4boardWithBackwardDiagonalOnesHasWinner() {
        board.setState(new int[][]{{1, -1, -1, -1}, {-1, 1, -1, -1}, {-1, -1, 1, -1}, {-1, -1, -1, 1}});
        assertEquals(1, board.winner());
    }
}
