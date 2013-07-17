import java.io.PrintWriter;
import java.io.Writer;

public class BoardConsoleIO {
    private PrintWriter outputStream;
    private final static String horizontalDash = "\u2501";
    private final static String verticalDash = "\u2503";
    private final static String cross = "\u254B";
    private final static String[] blank = new String[]{"   ", "   ", " "};
    private final static String[] ex = new String[]{"\u2572 \u2571", " \u2573 ", "\u2571 \u2572"};
    private final static String[] oh = new String[]{"\u256D\u2500\u256E", "\u2502 \u2502", "\u2570\u2500\u256F"};

    public BoardConsoleIO(Writer writer) {
        outputStream = new PrintWriter(writer, true);
    }

    public void printBoard(int[][] boardState, String zeroSymbol, String oneSymbol) {
        int size = boardState.length;
        String horizontalDivider = "";
        for (int column = 0; column < size; column++) {
            if (column > 0) {
                horizontalDivider += cross;
            }
            horizontalDivider += horizontalDash + horizontalDash + horizontalDash;
        }
        for (int row = 0; row < size; row++) {
            if (row > 0) {
                outputStream.println(horizontalDivider);
            }
            printRow(boardState[row], row, new String[]{zeroSymbol, oneSymbol});
        }
    }

    public void printRow(int[] row, int rowNumber, String[] playerSymbols) {
        String[][] playerTemplates = new String[2][];
        if (playerSymbols[0].equals("X")) {
            playerTemplates[0] = ex;
        } else {
            playerTemplates[0] = oh;
        }
        if (playerSymbols[1].equals("X")) {
            playerTemplates[1] = ex;
        } else {
            playerTemplates[1] = oh;
        }
        String[] rowStrings = new String[]{"", "", ""};
        int size = row.length;
        for (int column = 0; column < size; column++) {
            if (column > 0) {
                for (int subRow = 0; subRow < 3; subRow++) {
                    rowStrings[subRow] += verticalDash;
                }
            }
            if (row[column] >= 0) {
                int player = row[column];
                rowStrings[0] += playerTemplates[player][0];
                rowStrings[1] += playerTemplates[player][1];
                rowStrings[2] += playerTemplates[player][2];
            } else {
                rowStrings[0] += blank[0];
                rowStrings[1] += blank[1];
                rowStrings[2] += blank[2];
                String cellNum = Integer.toString(rowNumber * size + column);
                if (cellNum.length() < 2) {
                    rowStrings[2] += " ";
                }
                rowStrings[2] += cellNum;
            }
        }
        for (int subRow = 0; subRow < 3; subRow++) {
            outputStream.println(rowStrings[subRow]);
        }
    }
}
