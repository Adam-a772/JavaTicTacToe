package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
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
        int[] result = alphabetaminimax(boardState, Integer.MIN_VALUE, Integer.MAX_VALUE, symbol);
        return new int[]{result[0], result[1]};
    }

    private int[] alphabetaminimax(BoardMarker[][] boardState, int alpha, int beta, BoardMarker movePlayer) {
        int nextRow, nextCol;
        nextRow = nextCol = -1;
        for(int[] emptyCell : emptyCells(boardState)){
            int row = emptyCell[0];
            int col = emptyCell[1];
            BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
            board.setState(boardStateCopy);
            board.makeMove(row, col, movePlayer);

            if(board.winner() != _)
                return new int[]{row, col, evaluateScore()};
        }
        if(movePlayer == symbol){
            for(int[] emptyCell : emptyCells(boardState)){
                int row = emptyCell[0];
                int col = emptyCell[1];
                int nextScore = getNextScore(boardState, alpha, beta, movePlayer, row, col);
                if(nextScore > alpha){
                    nextRow = row;
                    nextCol = col;
                    alpha = nextScore;
                }
                if(beta <= alpha){
                    return new int[]{nextRow, nextCol, alpha};
                }
            }
            return new int[]{nextRow, nextCol, alpha};
        } else {
            for(int[] emptyCell : emptyCells(boardState)){
                int row = emptyCell[0];
                int col = emptyCell[1];
                int nextScore = getNextScore(boardState, alpha, beta, movePlayer, row, col);
                if(nextScore < beta){
                    nextRow = row;
                    nextCol = col;
                    beta = nextScore;
                }
                if(beta <= alpha){
                    return new int[]{nextRow, nextCol, beta};
                }
            }
            return new int[]{nextRow, nextCol, beta};
        }
    }

    private int getNextScore(BoardMarker[][] boardState, int alpha, int beta, BoardMarker movePlayer, int row, int col) {
        BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
        boardStateCopy[row][col] = movePlayer;
        BoardMarker nextPlayer = (movePlayer == X) ? O : X;
        return alphabetaminimax(boardStateCopy, alpha, beta, nextPlayer)[2];
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
