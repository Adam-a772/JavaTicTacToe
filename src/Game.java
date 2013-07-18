public class Game {
    private Player[] players;
    private TicTacToeBoard board;
    private int turn;
    private BoardIO boardIO;

    public Game(Player[] plrs, TicTacToeBoard brd, BoardIO brdIO){
        players = plrs;
        board = brd;
        boardIO = brdIO;
        turn = 0;
    }

    public int getTurn(){
        return turn;
    }

    public int takeTurn() {
        boardIO.printBoard(board.getState(), players[0].getSymbol(), players[1].getSymbol());
        int currentPlayer = turn % players.length;
        int[] move = players[currentPlayer].getMove(board.getState());
        board.makeMove(move[0], move[1], currentPlayer);
        ++turn;
        return board.winner();
    }

    public int play() {
        int size = board.getRow(0).length;
        for(int i = 0; i < size * size; i++){
            int winner = takeTurn();
            if (winner > -1){
                return winner;
            }
        }
        return -1;
    }
}
