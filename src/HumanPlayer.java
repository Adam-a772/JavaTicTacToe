import java.io.IOException;

public class HumanPlayer implements Player {
    private String symbol;
    private ConsoleIO io;

    public HumanPlayer(String symbol, ConsoleIO consoleIO) {
        this.symbol = symbol;
        this.io = consoleIO;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int[] getMove(int[][] boardState) throws IOException {
        io.askForMove();
        String cell_str = io.readMove();
        int cell;
        try {
            cell = Integer.parseInt(cell_str);
        } catch (NumberFormatException e) {
            io.notifyInvalidCell();
            return getMove(boardState);
        }
        if (cell < 0 || cell >= boardState.length * boardState.length) {
            io.notifyInvalidCell();
            return getMove(boardState);
        } else {
            int row = cell / boardState.length;
            int col = cell % boardState.length;
            return new int[]{row, col};
        }
    }
}
