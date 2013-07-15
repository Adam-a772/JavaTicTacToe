import java.util.Arrays;

public class TicTacToeBoard {
    private int size;
    private int[][] state;

    public TicTacToeBoard(int size) {
        state = new int[size][size];
        this.size = size;
        for (int[] aState : state) {
            Arrays.fill(aState, -1);
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
        int[] column = new int[size];
        for (int i = 0; i < size; i++) {
            column[i] = state[i][col];
        }
        return column;
    }

    public int[] getForwardDiagonal() {
        int[] diag = new int[size];
        for (int i = 0; i < size; i++) {
            diag[i] = state[i][i];
        }
        return diag;
    }

    public int[] getBackwardDiagonal() {
        int[] diag = new int[size];
        for (int i = 0; i < size; i++) {
            diag[i] = state[i][size - 1 - i];
        }
        return diag;
    }

    public boolean hasWinner(int[] row) {
        int firstElem = row[0];
        if (firstElem == -1) {
            return false;
        }
        for (int i = 1; i < size; i++) {
            if (row[i] != firstElem) {
                return false;
            }
        }
        return true;
    }

    public int winner() {
        for (int i = 0; i < size; i++) {
            if (hasWinner(getRow(i)) || hasWinner(getColumn(i))) {
                return getState()[i][i];
            }
        }
        if (hasWinner(getForwardDiagonal())) {
            return getState()[0][0];
        } else if (hasWinner(getBackwardDiagonal())) {
            return getState()[0][size - 1];
        } else {
            return -1;
        }
    }
}
