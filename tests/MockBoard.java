class MockBoard implements TwoDimensionalGameBoard {
    private int[][] boardState;

    public MockBoard() {
        boardState = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
    }

    public void setState(int[][] newState) {
        boardState = newState;
    }

    @Override
    public int[][] getState() {
        return boardState;
    }

    @Override
    public int[] getRow(int rowNum) {
        return boardState[rowNum];
    }

    @Override
    public int[] getColumn(int columnNum) {
        return new int[]{boardState[0][columnNum], boardState[1][columnNum], boardState[2][columnNum]};
    }

    @Override
    public int[] getBackwardDiagonal() {
        return new int[]{boardState[0][0], boardState[1][1], boardState[2][2]};
    }

    @Override
    public int[] getForwardDiagonal() {
        return new int[]{boardState[0][2], boardState[1][1], boardState[2][0]};
    }
}
