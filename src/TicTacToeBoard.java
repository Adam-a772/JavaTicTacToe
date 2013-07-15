import java.util.Arrays;

public class TicTacToeBoard {
    private int[][] state;

    public TicTacToeBoard() {
        state = new int[3][3];
        for (int i = 0; i < state.length; i++) {
            Arrays.fill(state[i], -1);
        }
    }

    public int[][] getState() {
        return state;
    }


    public void setState(int[][] newState) {
        state = newState;
    }

    public void makeMove(int row, int col, int player) {
        state[row][col] = player;
    }

    public int[] getRow(int row) {
        return state[row];
    }

    public int[] getColumn(int col) {
        int[] column = new int[3];
        for (int i = 0; i < column.length; i++) {
            column[i] = state[i][col];
        }
        return column;
    }

    public int[] getForwardDiagonal() {
        int[] diag = new int[3];
        for (int i = 0; i < diag.length; i++) {
            diag[i] = state[i][i];
        }
        return diag;
    }

    public int[] getBackwardDiagonal() {
        int[] diag = new int[3];
        for (int i = 0; i < diag.length; i++) {
            diag[i] = state[i][diag.length - 1 - i];
        }
        return diag;
    }

    public static boolean hasWinner(int[] row) {
        int firstElem = row[0];
        if (firstElem == -1) {
            return false;
        }
        for (int i = 1; i < row.length; i++) {
            if (row[i] != firstElem) {
                return false;
            }
        }
        return true;
    }

    public int winner() {
        for (int i = 0; i < getRow(0).length; i++) {
            if (hasWinner(getRow(i)) || hasWinner(getColumn(i))) {
                return getState()[i][i];
            }
        }
        if (hasWinner(getForwardDiagonal()) || hasWinner(getBackwardDiagonal())) {
            return getState()[1][1];
        } else {
            return -1;
        }
    }
}
