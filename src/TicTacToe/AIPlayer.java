package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static TicTacToe.BoardMarker.*;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class AIPlayer implements Player{
    private TicTacToeBoard board;
    private BoardMarker symbol;
    private HashMap<BoardMarkerArray, BoardStateValues> transpositionTable;

    public AIPlayer(BoardMarker sym, TicTacToeBoard brd) {
        symbol = sym;
        board = brd;
        transpositionTable = new HashMap<BoardMarkerArray, BoardStateValues>();
    }

    @Override
    public BoardMarker getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove(BoardMarker[][] boardState) {
        int depth = max(2, 12 - (int) Math.sqrt(emptyCells(boardState).length));
        int[] result = mtdf(boardState, 1, symbol, depth);
        return new int[]{result[0], result[1]};
    }

    private int[] mtdf(BoardMarker[][] boardState, int startGuess, BoardMarker movePlayer, int depth){
        int[] returnValues = new int[3];
        int upperbound = Integer.MAX_VALUE;
        int lowerbound = Integer.MIN_VALUE;
        int beta;
        while(upperbound > lowerbound){
            if(startGuess == lowerbound){
                beta = startGuess + 1;
            } else {
                beta = startGuess;
            }
            returnValues = alphaBetaMinimaxWithMemory(boardState, beta - 1, beta, movePlayer, depth);
            startGuess = returnValues[2];
            if(startGuess < beta){
                upperbound = startGuess;
            } else {
                lowerbound = startGuess;
            }
        }
        return returnValues;
    }

    private int[] alphaBetaMinimaxWithMemory(BoardMarker[][] boardState, int alpha, int beta, BoardMarker movePlayer, int depth) {
        int returnBound = 0;
        boolean leafnode = false;

        BoardMarkerArray currentStateArray = new BoardMarkerArray(deep2DArrayCopy(boardState));
        BoardStateValues boardStateValues  = new BoardStateValues(-1, -1);
        if(transpositionTable.containsKey(currentStateArray)){
            boardStateValues = transpositionTable.get(currentStateArray);
            if(boardStateValues.getLowerbound() >= beta)
                return new int[]{boardStateValues.getRow(), boardStateValues.getColumn(), boardStateValues.getLowerbound()};
            if(boardStateValues.getUpperbound() <= alpha)
                return new int[]{boardStateValues.getRow(), boardStateValues.getColumn(), boardStateValues.getUpperbound()};
            alpha = Math.max(alpha, boardStateValues.getLowerbound());
            beta  = Math.min(beta, boardStateValues.getUpperbound());
        }
        for(int[] emptyCell : emptyCells(boardState)){
            int row = emptyCell[0];
            int col = emptyCell[1];
            BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
            board.setState(boardStateCopy);
            board.makeMove(row, col, movePlayer);

            if(board.winner() != _){
                leafnode = true;
                returnBound = evaluateScore();
                boardStateValues.setRow(row);
                boardStateValues.setColumn(col);
                boardStateValues.setLowerbound(returnBound);
                boardStateValues.setUpperbound(returnBound);
            }

            if(leafnode)
                break;
        }
        if(leafnode){
            //already dealt with this
        } else if(depth == 0){
            board.setState(boardState);
            returnBound = evaluateScore();
        } else if(movePlayer == symbol){
            returnBound = Integer.MIN_VALUE;
            int a = alpha;
            for(int[] emptyCell : emptyCells(boardState)){
                int row = emptyCell[0];
                int col = emptyCell[1];
                returnBound = max(returnBound, getNextScore(boardState, a, beta, movePlayer, depth, row, col));
                if(returnBound > a){
                    boardStateValues.setRow(row);
                    boardStateValues.setColumn(col);
                    boardStateValues.setLowerbound(returnBound);
                    a = returnBound;
                }
                if(returnBound >= beta){
                    break;
                }
            }
        } else {
            returnBound = Integer.MAX_VALUE;
            int b = beta;
            for(int[] emptyCell : emptyCells(boardState)){
                int row = emptyCell[0];
                int col = emptyCell[1];
                returnBound = min(returnBound, getNextScore(boardState, alpha, b, movePlayer, depth, row, col));
                if(returnBound < beta){
                    boardStateValues.setRow(row);
                    boardStateValues.setColumn(col);
                    boardStateValues.setUpperbound(returnBound);
                    b = returnBound;
                }
                if(returnBound <= alpha){
                    break;
                }
            }
        }

        transpositionTable.put(currentStateArray, boardStateValues);
        return new int[]{boardStateValues.getRow(), boardStateValues.getColumn(), returnBound};
    }

    private int getNextScore(BoardMarker[][] boardState, int alpha, int beta, BoardMarker movePlayer, int depth, int row, int col) {
        BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
        boardStateCopy[row][col] = movePlayer;
        BoardMarker nextPlayer = (movePlayer == X) ? O : X;
        return alphaBetaMinimaxWithMemory(boardStateCopy, alpha, beta, nextPlayer, depth - 1)[2];
    }

    private int evaluateScore() {
        BoardMarker winner = board.winner();
        if(winner == T){//tie
            return 0;
        } else if(winner == symbol){//AI player wins
            return 1;
        } else if (winner == ((symbol == X) ? O : X)){//other player wins
            return -1;
        }
        return 0;
    }

    public static int[][] emptyCells(BoardMarker[][] boardState){
        int boardSize = boardState.length;
        ArrayList<int[]> emptyCellArray = new ArrayList<int[]>(boardSize * boardSize);
        for(int row = 0; row < boardSize; row++){
            for(int col = 0; col < boardSize; col++){
                if(boardState[row][col] == _)
                    emptyCellArray.add(new int[]{row, col});
            }
        }
        return emptyCellArray.toArray(new int[emptyCellArray.size()][]);
    }

    public static BoardMarker[][] deep2DArrayCopy(BoardMarker[][] arr){
        BoardMarker[][] copy = new BoardMarker[arr.length][];
        for(int i = 0; i < arr.length; i++){
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }
}
