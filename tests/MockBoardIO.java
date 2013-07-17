public class MockBoardIO implements BoardIO {
    private int timesPrintedBoard;

    public MockBoardIO(){
        timesPrintedBoard = 0;
    }

    @Override
    public void printBoard(int[][] boardState, String zeroSymbol, String oneSymbol) {
        ++timesPrintedBoard;
    }

    public int getTimesPrintedBoard(){
        return timesPrintedBoard;
    }
}
