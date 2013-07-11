import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    Player player;

    @Before
    public void initialize() {
        player = new MockPlayer("X");
    }

    @Test
    public void playerShouldHaveASymbol() {
        assertEquals("X", player.getSymbol());
    }

    @Test
    public void playersShouldBeAbleToReturnAMove() {
        assertArrayEquals(new int[]{0, 0}, player.getMove(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}));
    }
}
