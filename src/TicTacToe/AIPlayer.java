package TicTacToe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import static TicTacToe.BoardMarker.*;

public class AIPlayer implements Player{
    private TicTacToeBoard board;
    private BoardMarker symbol;
    private HashMap<BoardMarkerArray, BoardStateValues> cachedMoves;

    public AIPlayer(BoardMarker sym, TicTacToeBoard brd) {
        symbol = sym;
        board = brd;
        cachedMoves = new HashMap<BoardMarkerArray, BoardStateValues>();
    }

    @Override
    public BoardMarker getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove(BoardMarker[][] boardState) {
        BoardStateValues result = alphaBetaWithMemory(boardState, 1, -1, symbol);
        return new int[]{result.getRow(), result.getColumn()};
    }

    private BoardStateValues alphaBetaWithMemory(BoardMarker[][] boardState, int alpha, int beta, BoardMarker movePlayer){
        BoardMarkerArray currentBoardStateArray = new BoardMarkerArray(deep2DArrayCopy(boardState));
        int g, next_row, next_col;
        next_row = next_col = -1;

        if (cachedMoves.containsKey(currentBoardStateArray)){
            BoardStateValues boardStateValues = cachedMoves.get(currentBoardStateArray);
            if(boardStateValues.getLowerbound() >= beta){
                boardStateValues.setValue(boardStateValues.getLowerbound());
                return boardStateValues;
            } else if(boardStateValues.getUpperbound() <= alpha){
                boardStateValues.setValue(boardStateValues.getUpperbound());
                return boardStateValues;
            } else {
                alpha = Math.max(alpha, boardStateValues.getLowerbound());
                beta  = Math.min(beta, boardStateValues.getUpperbound());
            }
        }

        int size = boardState.length;
        board.setState(boardState);
        if(board.winner() != _){
            g = evaluate(board.winner());
        } else if(movePlayer == symbol){
            g = Integer.MIN_VALUE;
            int a = alpha;

            for(int cell = 0; cell < size * size; cell++){
                int row = cell / size;
                int col = cell % size;
                if(boardState[row][col] == _){
                    BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                    boardStateCopy[row][col] = movePlayer;
                    BoardMarker nextPlayer = (movePlayer == X) ? O : X;

                    int next_value = alphaBetaWithMemory(boardStateCopy, a, beta, nextPlayer).getValue();
                    if(next_value > g){
                        next_row = row;
                        next_col = col;
                    }
                    g = Math.max(g, next_value);
                    a = Math.max(a, g);
                    if(g < beta){
                        break;
                    }
                }
            }
        } else {
            g = Integer.MAX_VALUE;
            int b = beta;

            for(int cell = 0; cell < size * size; cell++){
                int row = cell / size;
                int col = cell % size;
                if(boardState[row][col] == _){
                    BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                    boardStateCopy[row][col] = movePlayer;
                    BoardMarker nextPlayer = (movePlayer == X) ? O : X;

                    int next_value = alphaBetaWithMemory(boardStateCopy, alpha, b, nextPlayer).getValue();
                    if(next_value < g){
                        next_row = row;
                        next_col = col;
                    }
                    g = Math.min(g, next_value);
                    b = Math.min(b, g);
                    if(g > alpha){
                        break;
                    }
                }
            }
        }
        BoardStateValues boardStateValues = new BoardStateValues(next_row, next_col);

        if(g <= alpha){
            boardStateValues.setUpperbound(g);
        } else if(g > alpha && g < beta){
            boardStateValues.setLowerbound(g);
            boardStateValues.setUpperbound(g);
        } else if(g >= beta){
            boardStateValues.setLowerbound(g);
        }

        boardStateValues.setValue(g);
        cachedMoves.put(currentBoardStateArray, boardStateValues);
        return boardStateValues;
    }

    private int evaluate(BoardMarker winner) {
        if(winner == T)
            return 0;
        else if(winner == symbol)
            return 1;
        else
            return -1;
    }

    private BoardStateValues getMove(BoardMarker[][] boardState, BoardMarker movePlayer) {
        BoardMarkerArray currentBoardStateArray = new BoardMarkerArray(deep2DArrayCopy(boardState));
        BoardStateValues storedBoardStateValues = cachedMoves.get(currentBoardStateArray);
        if(storedBoardStateValues != null){
            return storedBoardStateValues;
        }

        TreeMap<Integer, int[]> possibleMoves = new TreeMap<Integer, int[]>();
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == _){
                    BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                    board.setState(boardStateCopy);
                    board.makeMove(row, col, movePlayer);
                    BoardStateValues boardStateValues = new BoardStateValues(row, col);
                    BoardMarker outcome = board.winner();
                    if(outcome == T){//tie
                        boardStateValues.setValue(0);
                        cachedMoves.put(currentBoardStateArray, boardStateValues);
                        return boardStateValues;
                    } else if(outcome == movePlayer){//a player wins
                        if(movePlayer == symbol){//TicTacToe.AIPlayer wins
                            boardStateValues.setValue(1);
                            cachedMoves.put(currentBoardStateArray, boardStateValues);
                            return boardStateValues;
                        } else {//other player wins
                            boardStateValues.setValue(-1);
                            cachedMoves.put(currentBoardStateArray, boardStateValues);
                            return boardStateValues;
                        }
                    }
                    BoardMarker nextPlayer = (movePlayer == X) ? O : X;
                    int outcomeScore = getMove(boardStateCopy, nextPlayer).getValue();
                    possibleMoves.put(outcomeScore, new int[]{row, col, outcomeScore});
                }
            }
        }
        int[] move;
        if(movePlayer == symbol){//want highest outcome
            move = possibleMoves.get(possibleMoves.lastKey());
        } else {//want lowest outcome (highest outcome for other player)
            move = possibleMoves.get(possibleMoves.firstKey());
        }
        BoardStateValues boardStateValues = new BoardStateValues(move[0], move[1]);
        boardStateValues.setValue(move[2]);
        cachedMoves.put(currentBoardStateArray, boardStateValues);
        return boardStateValues;
    }

    public static BoardMarker[][] deep2DArrayCopy(BoardMarker[][] arr){
        BoardMarker[][] copy = new BoardMarker[arr.length][];
        for(int i = 0; i < arr.length; i++){
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }

    public HashMap<BoardMarkerArray, BoardStateValues> getCachedMoves(){
        return cachedMoves;
    }
}
