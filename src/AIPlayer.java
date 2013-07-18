import java.util.Arrays;
import java.util.TreeMap;

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
        int[] result = getMove(boardState, currentPlayer, currentPlayer);
        return new int[]{result[0], result[1]};
    }

    private int[] getMove(int[][] boardState, int currentPlayer, int movePlayer) {
        TreeMap<Integer, int[]> possibleMoves = new TreeMap<Integer, int[]>();
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == -1){
                    int[][] boardStateCopy = deep2DArrayCopy(boardState);
                    board.setState(boardStateCopy);
                    board.makeMove(row, col, movePlayer);
                    int outcome = board.winner();
                    if(outcome == 3){//tie
                        return new int[]{row, col, 0};
                    } else if(outcome == movePlayer){//a player wins
                        if(movePlayer == currentPlayer){//AIPlayer wins
                            return new int[]{row, col, 1};
                        } else {//other player wins
                            return new int[]{row, col, -1};
                        }
                    }
                    int nextPlayer = (movePlayer == 0) ? 1 : 0;
                    outcome = getMove(boardStateCopy, currentPlayer, nextPlayer)[2];
                    possibleMoves.put(outcome, new int[]{row, col, outcome});
                }
            }
        }
        if(currentPlayer == movePlayer){//want highest outcome
            return possibleMoves.get(possibleMoves.lastKey());
        } else {//want lowest outcome (highest outcome for other player)
            return possibleMoves.get(possibleMoves.firstKey());
        }
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
