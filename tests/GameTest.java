import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class GameTest {
    private Game game;
    private StringWriter outputStream;
    private MockPlayer player0, player1;

    @Before
    public void initialize(){
        outputStream = new StringWriter();
        player0 = new MockPlayer("X");
        player1 = new MockPlayer("O");
        Player[] players = new Player[]{player0, player1};
        game = new Game(new StringReader(""), outputStream, players, new TicTacToeBoard());
    }

    @Test
    public void shouldStartAtTurn0(){
        assertEquals(0, game.getTurn());
    }

    @Test
    public void shouldAdvanceTurnEachTurn() {
        assertEquals(0, game.getTurn());
        game.takeTurn();
        assertEquals(1, game.getTurn());
        game.takeTurn();
        assertEquals(2, game.getTurn());
        game.takeTurn();
        assertEquals(3, game.getTurn());
    }

    @Test
    public void shouldGetMoveFromPlayer0OnTurn0() {
        game.takeTurn();
        assertEquals(1, player0.getTimesGetMove());
        assertEquals(0, player1.getTimesGetMove());

    }

    @Test
    public void shouldGetMoveFromPlayer1OnTurn1() {
        game.takeTurn();
        game.takeTurn();
        assertEquals(1, player0.getTimesGetMove());
        assertEquals(1, player1.getTimesGetMove());
    }

    @Test
    public void shouldGetMoveFromCorrectPlayerEachTurn() {
        for(int i = 0; i < 9; i++){
            game.takeTurn();
        }
        assertEquals(5, player0.getTimesGetMove());
        assertEquals(4, player1.getTimesGetMove());
    }
}
