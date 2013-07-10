import java.util.Arrays;

public class Board {
    private int[][] state;

    public Board() {
        state = new int[3][3];
        for (int i = 0; i < state.length; i++) {
            Arrays.fill(state[i], -1);
        }
    }

    public int[][] getState() {
        return state;
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
}
