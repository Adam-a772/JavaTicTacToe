public class AIPlayer implements Player{
    private TicTacToeBoard board;
    private String symbol;

    public AIPlayer(String sym, TicTacToeBoard brd) {
        symbol = sym;
        board = brd;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove(int[][] boardState) {
        for(int i = 0; i < 2; i++){
            if(boardState[i][i] == -1){
                return new int[]{i, i};
            }
        }
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == -1){
                    return new int[]{row, col};
                }
            }
        }
        return null;
    }
}
