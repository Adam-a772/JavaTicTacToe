package TicTacToe;

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
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == _){
                    BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                    board.setState(boardStateCopy);
                    board.makeMove(row, col, movePlayer);

                    if(board.winner() != _)
                        return new int[]{row, col, evaluateScore(movePlayer)};
                }
            }
        }
        if(movePlayer == symbol){
            for(int row = 0; row < boardState.length; row++){
                for(int col = 0; col < boardState.length; col++){
                    if(boardState[row][col] == _){
                        BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                        board.setState(boardStateCopy);
                        board.makeMove(row, col, movePlayer);
                        BoardMarker nextPlayer = (movePlayer == X) ? O : X;
                        int nextScore = alphabetaminimax(boardStateCopy, alpha, beta, nextPlayer)[2];
                        if(nextScore > alpha){
                            nextRow = row;
                            nextCol = col;
                        }
                        alpha = Math.max(alpha, nextScore);
                        if(beta <= alpha){
                            return new int[]{nextRow, nextCol, alpha};
                        }
                    }
                }
            }
            return new int[]{nextRow, nextCol, alpha};
        } else {
            for(int row = 0; row < boardState.length; row++){
                for(int col = 0; col < boardState.length; col++){
                    if(boardState[row][col] == _){
                        BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                        board.setState(boardStateCopy);
                        board.makeMove(row, col, movePlayer);
                        BoardMarker nextPlayer = (movePlayer == X) ? O : X;
                        int nextScore = alphabetaminimax(boardStateCopy, alpha, beta, nextPlayer)[2];
                        if(nextScore < beta){
                            nextRow = row;
                            nextCol = col;
                        }
                        beta = Math.min(beta, nextScore);
                        if(beta <= alpha){
                            return new int[]{nextRow, nextCol, beta};
                        }
                    }
                }
            }
            return new int[]{nextRow, nextCol, beta};
        }
    }

    private int evaluateScore(BoardMarker movePlayer) {
        BoardMarker winner = board.winner();
        if(winner == T){//tie
            return 0;
        } else if(winner == movePlayer){//a player wins
            if(movePlayer == symbol){//TicTacToe.AIPlayer wins
                return 1;
            } else {//other player wins
                return -1;
            }
        }
        return 0;
    }

    public static BoardMarker[][] deep2DArrayCopy(BoardMarker[][] arr){
        BoardMarker[][] copy = new BoardMarker[arr.length][];
        for(int i = 0; i < arr.length; i++){
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }
        return copy;
    }
}
