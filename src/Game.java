import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

public class Game {
    private PrintWriter outputStream;
    private Scanner inputStream;
    private Player[] players;
    private TicTacToeBoard board;
    private int turn;

    public Game(Readable reader, Writer writer, Player[] plrs, TicTacToeBoard brd){
        outputStream = new PrintWriter(writer, true);
        inputStream = new Scanner(reader);
        players = plrs;
        board = brd;
        turn = 0;
    }

    public int getTurn(){
        return turn;
    }

    public void takeTurn() throws IOException {
        players[turn % players.length].getMove(board.getState());
        ++turn;
    }
}
