public class MockPlayer implements Player {
    private String symbol;
    private int[] next_move;

    public MockPlayer(String symbol) {
        this.symbol = symbol;
        this.next_move = new int[]{0, 0};
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove(int[][] boardState) {
        return next_move;
    }
}
