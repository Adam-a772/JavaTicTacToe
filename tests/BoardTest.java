import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BoardTest {
    Board board;

    @Before
    public void initialize() {
        board = new Board();
    }

    @Test
    public void shouldHaveAnEmptyArrayWhenConstructed() {
        int[] arr = new int[9];
        assertArrayEquals(arr, board.getState());
    }
}
