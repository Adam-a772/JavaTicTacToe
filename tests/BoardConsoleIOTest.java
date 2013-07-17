import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class BoardConsoleIOTest {
    BoardConsoleIO io;
    StringWriter ioOutput;


    @Before
    public void initialize() {
        ioOutput = new StringWriter();
        io = new BoardConsoleIO(ioOutput);
    }

    @Test
    public void shouldPrintAnEmptyFirstRow() {
        String emptyRow = "   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \n" +
                "  0\u2503  1\u2503  2\n";
        io.printRow(new int[]{-1, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptySecondRow() {
        String emptyRow = "   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \n" +
                "  3\u2503  4\u2503  5\n";
        io.printRow(new int[]{-1, -1, -1}, 1, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyThirdRow() {
        String emptyRow = "   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \n" +
                "  6\u2503  7\u2503  8\n";
        io.printRow(new int[]{-1, -1, -1}, 2, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyFirstRowFor4() {
        String emptyRow = "   \u2503   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \u2503   \n" +
                "  0\u2503  1\u2503  2\u2503  3\n";
        io.printRow(new int[]{-1, -1, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyLastRowFor4() {
        String emptyRow = "   \u2503   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \u2503   \n" +
                " 12\u2503 13\u2503 14\u2503 15\n";
        io.printRow(new int[]{-1, -1, -1, -1}, 3, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyBoard() {
        String emptyBoard = "   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \n" +
                "  0\u2503  1\u2503  2\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \n" +
                "  3\u2503  4\u2503  5\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \n" +
                "  6\u2503  7\u2503  8\n";
        io.printBoard(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}, "X", "O");
        assertEquals(emptyBoard, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyBoardFor4() {
        String emptyBoard = "   \u2503   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \u2503   \n" +
                "  0\u2503  1\u2503  2\u2503  3\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "   \u2503   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \u2503   \n" +
                "  4\u2503  5\u2503  6\u2503  7\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "   \u2503   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \u2503   \n" +
                "  8\u2503  9\u2503 10\u2503 11\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "   \u2503   \u2503   \u2503   \n" +
                "   \u2503   \u2503   \u2503   \n" +
                " 12\u2503 13\u2503 14\u2503 15\n";
        io.printBoard(new int[][]{{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}}, "X", "O");
        assertEquals(emptyBoard, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnX() {
        String row = "\u2572 \u2571\u2503   \u2503   \n" +
                " \u2573 \u2503   \u2503   \n" +
                "\u2571 \u2572\u2503  1\u2503  2\n";
        io.printRow(new int[]{0, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnO() {
        String row = "\u256D\u2500\u256E\u2503   \u2503   \n" +
                "\u2502 \u2502\u2503   \u2503   \n" +
                "\u2570\u2500\u256F\u2503  1\u2503  2\n";
        io.printRow(new int[]{0, -1, -1}, 0, new String[]{"O", "X"});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnOForP2() {
        String row = "\u256D\u2500\u256E\u2503   \u2503   \n" +
                "\u2502 \u2502\u2503   \u2503   \n" +
                "\u2570\u2500\u256F\u2503  1\u2503  2\n";
        io.printRow(new int[]{1, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintFull4x4Board() {
        String board = "\u2572 \u2571\u2503\u256D\u2500\u256E\u2503\u256D\u2500\u256E\u2503\u256D\u2500\u256E\n" +
                " \u2573 \u2503\u2502 \u2502\u2503\u2502 \u2502\u2503\u2502 \u2502\n" +
                "\u2571 \u2572\u2503\u2570\u2500\u256F\u2503\u2570\u2500\u256F\u2503\u2570\u2500\u256F\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "\u2572 \u2571\u2503\u2572 \u2571\u2503\u256D\u2500\u256E\u2503\u256D\u2500\u256E\n" +
                " \u2573 \u2503 \u2573 \u2503\u2502 \u2502\u2503\u2502 \u2502\n" +
                "\u2571 \u2572\u2503\u2571 \u2572\u2503\u2570\u2500\u256F\u2503\u2570\u2500\u256F\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "\u2572 \u2571\u2503\u2572 \u2571\u2503\u256D\u2500\u256E\u2503\u2572 \u2571\n" +
                " \u2573 \u2503 \u2573 \u2503\u2502 \u2502\u2503 \u2573 \n" +
                "\u2571 \u2572\u2503\u2571 \u2572\u2503\u2570\u2500\u256F\u2503\u2571 \u2572\n" +
                "\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\u254B\u2501\u2501\u2501\n" +
                "\u256D\u2500\u256E\u2503\u2572 \u2571\u2503\u2572 \u2571\u2503\u256D\u2500\u256E\n" +
                "\u2502 \u2502\u2503 \u2573 \u2503 \u2573 \u2503\u2502 \u2502\n" +
                "\u2570\u2500\u256F\u2503\u2571 \u2572\u2503\u2571 \u2572\u2503\u2570\u2500\u256F\n";
        io.printBoard(new int[][]{{0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 0}, {1, 0, 0, 1}}, "X", "O");
        assertEquals(board, ioOutput.toString());
    }
}
