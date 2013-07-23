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
        int[] result = minimax(boardState, symbol);
        return new int[]{result[0], result[1]};
    }

    private int[] minimax(BoardMarker[][] boardState, BoardMarker movePlayer) {
        int nextRow, nextCol;
        nextRow = nextCol = -1;
        int score = (movePlayer == symbol) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for(int row = 0; row < boardState.length; row++){
            for(int col = 0; col < boardState.length; col++){
                if(boardState[row][col] == _){
                    BoardMarker[][] boardStateCopy = deep2DArrayCopy(boardState);
                    board.setState(boardStateCopy);
                    board.makeMove(row, col, movePlayer);

                    if(board.winner() != _)
                        return new int[]{row, col, evaluateScore(movePlayer)};

                    BoardMarker nextPlayer = (movePlayer == X) ? O : X;
                    int nextScore = minimax(boardStateCopy, nextPlayer)[2];
                    if(movePlayer == symbol){
                        if(nextScore > score){
                            nextRow = row;
                            nextCol = col;
                            score = nextScore;
                        }
                    } else {
                        if(nextScore < score){
                            nextRow = row;
                            nextCol = col;
                            score = nextScore;
                        }
                    }
                }
            }
        }
        return new int[]{nextRow, nextCol, score};
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
