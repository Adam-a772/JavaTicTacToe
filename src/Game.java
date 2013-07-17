public class Game {
    private Player[] players;
    private TicTacToeBoard board;
    private int turn;

    public Game(Player[] plrs, TicTacToeBoard brd){
        players = plrs;
        board = brd;
        turn = 0;
    }

    public int getTurn(){
        return turn;
    }

    public void takeTurn() {
        players[turn % players.length].getMove(board.getState());
        ++turn;
    }
}
