package TicTacToe;

import java.util.Arrays;
import java.util.TreeMap;
import static TicTacToe.BoardMarker.*;

public class AIPlayer implements Player{
    private TicTacToeBoard board;
    private BoardMarker symbol;

    public AIPlayer(BoardMarker sym, TicTacToeBoard brd) {
        symbol = sym;
        board = brd;
    }

    @Override
    public BoardMarker getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove(BoardMarker[][] boardState) {
        int[] result = getMove(boardState, symbol);
        return new int[]{result[0], result[1]};
    }

    private int[] getMove(BoardMarker[][] boardState, BoardMarker movePlayer) {
        TreeMap<Integer, int[]> possibleMoves = new TreeMap<Integer, int[]>();
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == _){
                    BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                    board.setState(boardStateCopy);
                    board.makeMove(row, col, movePlayer);
                    BoardMarker outcome = board.winner();
                    if(outcome == T){//tie
                        return new int[]{row, col, 0};
                    } else if(outcome == movePlayer){//a player wins
                        if(movePlayer == symbol){//TicTacToe.AIPlayer wins
                            return new int[]{row, col, 1};
                        } else {//other player wins
                            return new int[]{row, col, -1};
                        }
                    }
                    BoardMarker nextPlayer = (movePlayer == X) ? O : X;
                    int outcomeScore = getMove(boardStateCopy, nextPlayer)[2];
                    possibleMoves.put(outcomeScore, new int[]{row, col, outcomeScore});
                }
            }
        }
        if(movePlayer == symbol){//want highest outcome
            return possibleMoves.get(possibleMoves.lastKey());
        } else {//want lowest outcome (highest outcome for other player)
            return possibleMoves.get(possibleMoves.firstKey());
        }
    }

    public static BoardMarker[][] deep2DArrayCopy(BoardMarker[][] arr){
        BoardMarker[][] copy = new BoardMarker[arr.length][];
        for(int i = 0; i < arr.length; i++){
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }
}
