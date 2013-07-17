import java.util.Arrays;

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
        int currentPlayer = currentPlayer(boardState);
        int[] winningMove = getWinningMove(boardState, currentPlayer);
        if(winningMove[0] >= 0 && winningMove[1] >= 0){
            return winningMove;
        }
        for(int i = 0; i < boardState.length; i++){
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

    private int[] getWinningMove(int[][] boardState, int currentPlayer) {
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == -1){
                    board.setState(deep2DArrayCopy(boardState));
                    board.makeMove(row, col, currentPlayer);
                    if(board.winner() == currentPlayer){
                        return new int[]{row, col};
                    }
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int[][] deep2DArrayCopy(int[][] intArr){
        int[][] copy = new int[intArr.length][];
        for(int i = 0; i < intArr.length; i++){
            copy[i] = Arrays.copyOf(intArr[i], intArr[i].length);
        }
        return copy;
    }

    public int currentPlayer(int[][] boardState) {
        int[] counts = new int[]{0, 0};
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] > -1){
                    ++counts[boardState[row][col]];
                }
            }
        }
        if(counts[0] > counts[1]){
            return 1;
        } else {
            return 0;
        }
    }
}
