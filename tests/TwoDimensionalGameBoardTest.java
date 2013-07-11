import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TwoDimensionalGameBoardTest {
    TwoDimensionalGameBoard board;

    @Before
    public void initialize() {
        board = new MockBoard();
    }

    @Test
    public void shouldHaveAGetStateMethod() {
        assertArrayEquals(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}, board.getState());
    }

    @Test
    public void shouldHaveAGetRowMethod() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getRow(0));
    }

    @Test
    public void shouldHaveAGetColumnMethod() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getColumn(0));
    }

    @Test
    public void shouldHaveAGetForwardDiagonalMethod() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getForwardDiagonal());
    }

    @Test
    public void shouldHaveAGetBackwardDiagonalMethod() {
        assertArrayEquals(new int[]{-1, -1, -1}, board.getBackwardDiagonal());
    }

}