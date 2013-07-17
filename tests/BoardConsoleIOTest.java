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
        String[] ex = new String[]{"\\ /", " x ", "/ \\"};
        String[] oh = new String[]{"/-\\", "| |", "\\-/"};
        io = new BoardConsoleIO(ioOutput, "-", "|", "+", ex, oh);
    }

    @Test
    public void shouldPrintAnEmptyFirstRow() {
        String emptyRow = "   |   |   \n" +
                          "   |   |   \n" +
                          "  0|  1|  2\n";
        io.printRow(new int[]{-1, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptySecondRow() {
        String emptyRow = "   |   |   \n" +
                          "   |   |   \n" +
                          "  3|  4|  5\n";
        io.printRow(new int[]{-1, -1, -1}, 1, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyThirdRow() {
        String emptyRow = "   |   |   \n" +
                          "   |   |   \n" +
                          "  6|  7|  8\n";
        io.printRow(new int[]{-1, -1, -1}, 2, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyFirstRowFor4() {
        String emptyRow = "   |   |   |   \n" +
                          "   |   |   |   \n" +
                          "  0|  1|  2|  3\n";
        io.printRow(new int[]{-1, -1, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyLastRowFor4() {
        String emptyRow = "   |   |   |   \n" +
                          "   |   |   |   \n" +
                          " 12| 13| 14| 15\n";
        io.printRow(new int[]{-1, -1, -1, -1}, 3, new String[]{"X", "O"});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyBoard() {
        String emptyBoard = "   |   |   \n" +
                            "   |   |   \n" +
                            "  0|  1|  2\n" +
                            "---+---+---\n" +
                            "   |   |   \n" +
                            "   |   |   \n" +
                            "  3|  4|  5\n" +
                            "---+---+---\n" +
                            "   |   |   \n" +
                            "   |   |   \n" +
                            "  6|  7|  8\n";
        io.printBoard(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}}, "X", "O");
        assertEquals(emptyBoard, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyBoardFor4() {
        String emptyBoard = "   |   |   |   \n" +
                            "   |   |   |   \n" +
                            "  0|  1|  2|  3\n" +
                            "---+---+---+---\n" +
                            "   |   |   |   \n" +
                            "   |   |   |   \n" +
                            "  4|  5|  6|  7\n" +
                            "---+---+---+---\n" +
                            "   |   |   |   \n" +
                            "   |   |   |   \n" +
                            "  8|  9| 10| 11\n" +
                            "---+---+---+---\n" +
                            "   |   |   |   \n" +
                            "   |   |   |   \n" +
                            " 12| 13| 14| 15\n";
        io.printBoard(new int[][]{{-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}, {-1, -1, -1, -1}}, "X", "O");
        assertEquals(emptyBoard, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnX() {
        String row = "\\ /|   |   \n" +
                     " x |   |   \n" +
                     "/ \\|  1|  2\n";
        io.printRow(new int[]{0, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnO() {
        String row = "/-\\|   |   \n" +
                     "| ||   |   \n" +
                     "\\-/|  1|  2\n";
        io.printRow(new int[]{0, -1, -1}, 0, new String[]{"O", "X"});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnOForP2() {
        String row = "/-\\|   |   \n" +
                     "| ||   |   \n" +
                     "\\-/|  1|  2\n";
        io.printRow(new int[]{1, -1, -1}, 0, new String[]{"X", "O"});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintFull4x4Board() {
        String board = "\\ /|/-\\|/-\\|/-\\\n" +
                       " x || ||| ||| |\n" +
                       "/ \\|\\-/|\\-/|\\-/\n" +
                       "---+---+---+---\n" +
                       "\\ /|\\ /|/-\\|/-\\\n" +
                       " x | x || ||| |\n" +
                       "/ \\|/ \\|\\-/|\\-/\n" +
                       "---+---+---+---\n" +
                       "\\ /|\\ /|/-\\|\\ /\n" +
                       " x | x || || x \n" +
                       "/ \\|/ \\|\\-/|/ \\\n" +
                       "---+---+---+---\n" +
                       "/-\\|\\ /|\\ /|/-\\\n" +
                       "| || x | x || |\n" +
                       "\\-/|/ \\|/ \\|\\-/\n";
        io.printBoard(new int[][]{{0, 1, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 0}, {1, 0, 0, 1}}, "X", "O");
        assertEquals(board, ioOutput.toString());
    }
}
