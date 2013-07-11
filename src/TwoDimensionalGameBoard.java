public interface TwoDimensionalGameBoard {
    int[][] getState();

    int[] getRow(int rowNum);

    int[] getColumn(int columnNum);

    int[] getBackwardDiagonal();

    int[] getForwardDiagonal();
}
