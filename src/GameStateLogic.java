public class GameStateLogic {
    public boolean hasWinner(int[] row) {
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

    public int winner(TwoDimensionalGameBoard board) {
        for (int i = 0; i < board.getRow(0).length; i++) {
            if (hasWinner(board.getRow(i)) || hasWinner(board.getColumn(i))) {
                return board.getState()[i][i];
            }
        }
        if (hasWinner(board.getForwardDiagonal()) || hasWinner(board.getBackwardDiagonal())) {
            return board.getState()[1][1];
        } else {
            return -1;
        }
    }
}
