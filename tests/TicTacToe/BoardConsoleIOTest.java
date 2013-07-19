package TicTacToe;

import TicTacToe.BoardConsoleIO;
import static TicTacToe.BoardMarker.*;
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
        io.printRow(new BoardMarker[]{_, _, _}, 0, new BoardMarker[]{X, O});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptySecondRow() {
        String emptyRow = "   |   |   \n" +
                          "   |   |   \n" +
                          "  3|  4|  5\n";
        io.printRow(new BoardMarker[]{_, _, _}, 1, new BoardMarker[]{X, O});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyThirdRow() {
        String emptyRow = "   |   |   \n" +
                          "   |   |   \n" +
                          "  6|  7|  8\n";
        io.printRow(new BoardMarker[]{_, _, _}, 2, new BoardMarker[]{X, O});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyFirstRowFor4() {
        String emptyRow = "   |   |   |   \n" +
                          "   |   |   |   \n" +
                          "  0|  1|  2|  3\n";
        io.printRow(new BoardMarker[]{_, _, _, _}, 0, new BoardMarker[]{X, O});
        assertEquals(emptyRow, ioOutput.toString());
    }

    @Test
    public void shouldPrintAnEmptyLastRowFor4() {
        String emptyRow = "   |   |   |   \n" +
                          "   |   |   |   \n" +
                          " 12| 13| 14| 15\n";
        io.printRow(new BoardMarker[]{_, _, _, _}, 3, new BoardMarker[]{X, O});
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
        io.printBoard(new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}}, X, O);
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
        io.printBoard(new BoardMarker[][]{{_, _, _, _}, {_, _, _, _}, {_, _, _, _}, {_, _, _, _}}, X, O);
        assertEquals(emptyBoard, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnX() {
        String row = "\\ /|   |   \n" +
                     " x |   |   \n" +
                     "/ \\|  1|  2\n";
        io.printRow(new BoardMarker[]{X, _, _}, 0, new BoardMarker[]{X, O});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnO() {
        String row = "/-\\|   |   \n" +
                     "| ||   |   \n" +
                     "\\-/|  1|  2\n";
        io.printRow(new BoardMarker[]{O, _, _}, 0, new BoardMarker[]{O, X});
        assertEquals(row, ioOutput.toString());
    }

    @Test
    public void shouldPrintAFirstRowWithAnOForP2() {
        String row = "/-\\|   |   \n" +
                     "| ||   |   \n" +
                     "\\-/|  1|  2\n";
        io.printRow(new BoardMarker[]{O, _, _}, 0, new BoardMarker[]{X, O});
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
        io.printBoard(new BoardMarker[][]{{X, O, O, O}, {X, X, O, O}, {X, X, O, X}, {O, X, X, O}}, X, O);
        assertEquals(board, ioOutput.toString());
    }
}
