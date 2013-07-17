import java.io.PrintWriter;
import java.io.Writer;

public class BoardConsoleIO {
    private PrintWriter outputStream;
    private String horizontalDash;
    private String verticalDash;
    private String cross;
    private String[] blank = new String[]{"   ", "   ", " "};
    private String[] ex;
    private String[] oh;

    public BoardConsoleIO(Writer writer) {
        outputStream = new PrintWriter(writer, true);
        horizontalDash = "\u2501";
        verticalDash = "\u2503";
        cross = "\u254B";
        ex = new String[]{"\u2572 \u2571", " \u2573 ", "\u2571 \u2572"};
        oh = new String[]{"\u256D\u2500\u256E", "\u2502 \u2502", "\u2570\u2500\u256F"};
    }

    public BoardConsoleIO(Writer writer, String hDash, String vDash, String crss, String[] ex, String[] oh){
        outputStream = new PrintWriter(writer, true);
        horizontalDash = hDash;
        verticalDash = vDash;
        cross = crss;
        this.ex = ex;
        this.oh = oh;
    }

    public void printBoard(int[][] boardState, String zeroSymbol, String oneSymbol) {
        int size = boardState.length;
        String horizontalDivider = constructHorizontalDivider(size);
        for (int row = 0; row < size; row++) {
            if (row > 0) {
                outputStream.println(horizontalDivider);
            }
            printRow(boardState[row], row, new String[]{zeroSymbol, oneSymbol});
        }
    }

    private String constructHorizontalDivider(int size) {
        String horizontalDivider = "";
        for (int column = 0; column < size; column++) {
            if (column > 0) {
                horizontalDivider += cross;
            }
            horizontalDivider += horizontalDash + horizontalDash + horizontalDash;
        }
        return horizontalDivider;
    }

    public void printRow(int[] row, int rowNumber, String[] playerSymbols) {
        String[][] playerTemplates = constructPlayerTemplates(playerSymbols);
        String[] rowStrings = new String[]{"", "", ""};
        int size = row.length;
        for (int column = 0; column < size; column++) {
            if (column > 0) {
                addDividerToCell(rowStrings);
            }
            if (row[column] >= 0) {
                int player = row[column];
                String[] playerSymbol = playerTemplates[player];
                addPlayerCellToRowStrings(rowStrings, playerSymbol);
            } else {
                String cellNum = Integer.toString(rowNumber * size + column);
                addBlankCellToRowStrings(rowStrings, cellNum);
            }
        }
        for (int subRow = 0; subRow < 3; subRow++) {
            outputStream.println(rowStrings[subRow]);
        }
    }

    private void addBlankCellToRowStrings(String[] rowStrings, String cellNum) {
        rowStrings[0] += blank[0];
        rowStrings[1] += blank[1];
        rowStrings[2] += blank[2];
        if (cellNum.length() < 2) {
            rowStrings[2] += " ";
        }
        rowStrings[2] += cellNum;
    }

    private void addPlayerCellToRowStrings(String[] rowStrings, String[] playerSymbol) {
        for(int i = 0; i < rowStrings.length; i++){
            rowStrings[i] += playerSymbol[i];
        }
    }

    private void addDividerToCell(String[] rowStrings) {
        for (int subRow = 0; subRow < 3; subRow++) {
            rowStrings[subRow] += verticalDash;
        }
    }

    private String[][] constructPlayerTemplates(String[] playerSymbols) {
        String[][] playerTemplates = new String[2][];
        for(int i = 0; i < playerSymbols.length; i++){
            if (playerSymbols[i].equals("X")) {
                playerTemplates[i] = ex;
            } else {
                playerTemplates[i] = oh;
            }
        }
        return playerTemplates;
    }
}
