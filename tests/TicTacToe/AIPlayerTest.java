package TicTacToe;

import static TicTacToe.BoardMarker.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AIPlayerTest {
    AIPlayer player;
    TicTacToeBoard board;

    @Before
    public void initialize(){
        board = new TicTacToeBoard();
        player = new AIPlayer(X, board);
    }

    @Test
    public void shouldReturnSymbol(){
        assertEquals(X, player.getSymbol());
        player = new AIPlayer(O, board);
        assertEquals(O, player.getSymbol());
    }

    @Test
    public void shouldChooseCornerCellWhenBoardEmpty(){
        int[] move = player.getMove(new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}});
        int[][] corners = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        assertTrue(Arrays.equals(move, corners[0]) || Arrays.equals(move, corners[1]) ||
                Arrays.equals(move, corners[2]) || Arrays.equals(move, corners[3]));
    }

    @Test
    public void shouldChooseCell4WhenOnlyCell0Taken(){
        player = new AIPlayer(O, board);
        assertArrayEquals(new int[]{1, 1}, player.getMove(new BoardMarker[][]{{X, _, _}, {_, _, _}, {_, _, _}}));
    }

    @Test
    public void shouldChooseLastCellWhenOnlyCellLeft(){
        assertArrayEquals(new int[]{0, 0}, player.getMove(new BoardMarker[][]{{_, O, X}, {X, O, O}, {O, X, X}}));
        assertArrayEquals(new int[]{2, 0}, player.getMove(new BoardMarker[][]{{X, O, X}, {O, O, X}, {_, X, O}}));
        assertArrayEquals(new int[]{2, 2}, player.getMove(new BoardMarker[][]{{X, X, O}, {O, O, X}, {X, O, _}}));
        assertArrayEquals(new int[]{0, 2}, player.getMove(new BoardMarker[][]{{O, X, _}, {X, O, O}, {X, O, X}}));
        assertArrayEquals(new int[]{1, 2}, player.getMove(new BoardMarker[][]{{O, X, O}, {O, X, _}, {X, O, X}}));
        assertArrayEquals(new int[]{0, 1}, player.getMove(new BoardMarker[][]{{O, _, X}, {X, X, O}, {O, O, X}}));
        assertArrayEquals(new int[]{1, 0}, player.getMove(new BoardMarker[][]{{X, O, X}, {_, X, O}, {O, X, O}}));
        assertArrayEquals(new int[]{2, 1}, player.getMove(new BoardMarker[][]{{X, O, O}, {O, X, X}, {X, _, O}}));
    }

    @Test
    public void shouldPerformDeepCopy(){
        BoardMarker[][] arr = new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}};
        BoardMarker[][] copy = AIPlayer.deep2DArrayCopy(arr);
        assertArrayEquals(arr, copy);
        assertNotSame(arr[0], copy[0]);
    }

    @Test
    public void shouldChooseWinningMove(){
        assertArrayEquals(new int[]{2, 2}, player.getMove(new BoardMarker[][]{{X, _, O}, {X, X, O}, {O, _, _}}));
        assertArrayEquals(new int[]{0, 2}, player.getMove(new BoardMarker[][]{{O, O, _}, {_, X, _}, {X, X, O}}));
        assertArrayEquals(new int[]{2, 0}, player.getMove(new BoardMarker[][]{{O, X, X}, {_, X, _}, {_, O, O}}));
        assertArrayEquals(new int[]{1, 0}, player.getMove(new BoardMarker[][]{{X, O, O}, {_, X, _}, {X, _, O}}));
        player = new AIPlayer(O, board);
        assertArrayEquals(new int[]{0, 2}, player.getMove(new BoardMarker[][]{{X, _, _}, {_, _, _}, {O, X, O}}));
    }

    @Test
    public void shouldChooseMoveToForceTie(){
        assertArrayEquals(new int[]{2, 1}, player.getMove(new BoardMarker[][]{{X, O, X}, {_, X, _}, {O, _, O}}));
        assertArrayEquals(new int[]{2, 0}, player.getMove(new BoardMarker[][]{{X, X, O}, {_, O, _}, {_, _, _}}));
        assertFalse(Arrays.equals(new int[]{0, 1}, player.getMove(new BoardMarker[][]{{_, _, X}, {_, O, _}, {O, X, _}})));
        player = new AIPlayer(O, board);
        assertArrayEquals(new int[]{2, 0}, player.getMove(new BoardMarker[][]{{X, _, _}, {X, O, O}, {_, _, X}}));
    }

    @Test
    public void shouldReturnAllRowColForEmptyBoard(){
        BoardMarker[][] emptyBoard = new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}};
        ArrayList<int[]> emptyCells = new ArrayList<int[]>();
        for(int r = 0; r < emptyBoard.length; r++){
            for(int c = 0; c < emptyBoard.length; c++){
                emptyCells.add(new int[]{r, c});
            }
        }
        int[][] expectedEmptyCellsArr = emptyCells.toArray(new int[][]{});
        int[][] realEmptyCellsArr = AIPlayer.emptyCells(emptyBoard);
        assertEquals(expectedEmptyCellsArr.length, realEmptyCellsArr.length);
        for(int i = 0; i < expectedEmptyCellsArr.length; i++){
            assertArrayEquals(expectedEmptyCellsArr[i], realEmptyCellsArr[i]);
        }
    }

    @Test
    public void shouldReturnOnlyTheEmptyRowCol(){
        BoardMarker[][] board = new BoardMarker[][]{{X, O, X}, {_, O, _}, {_, X, _}};
        ArrayList<int[]> emptyCells = new ArrayList<int[]>();

        emptyCells.add(new int[]{1, 0});
        emptyCells.add(new int[]{1, 2});
        emptyCells.add(new int[]{2, 0});
        emptyCells.add(new int[]{2, 2});

        int[][] expectedEmptyCellsArr = emptyCells.toArray(new int[][]{});
        int[][] realEmptyCellsArr = AIPlayer.emptyCells(board);
        assertEquals(expectedEmptyCellsArr.length, realEmptyCellsArr.length);
        for(int i = 0; i < expectedEmptyCellsArr.length; i++){
            assertArrayEquals(expectedEmptyCellsArr[i], realEmptyCellsArr[i]);
        }
    }

    @Test
    public void shouldStartWithEmptyTranspositionTable(){
        assertEquals(0, player.getTranspositionTable().size());
    }

    @Test
    public void shouldCacheOpeningMoveWithATie(){
        BoardMarker[][] emptyBoard = new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}};
        BoardMarkerArray emptyBoardArray = new BoardMarkerArray(emptyBoard);
        player.getMove(emptyBoard);
        assertTrue(player.getTranspositionTable().containsKey(emptyBoardArray));
        BoardStateValues boardStateValues = player.getTranspositionTable().get(emptyBoardArray);
        assertEquals(0, boardStateValues.getRow());
        assertEquals(0, boardStateValues.getColumn());
    }

    @Test
    public void shouldAlreadyHaveSecondMoveCachedIn3x3(){
        player.getMove(new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}});
        int initialNumberOfCachedMoves = player.getTranspositionTable().size();
        BoardMarker[][] board = new BoardMarker[][]{{X, _, _}, {_, O, _}, {_, _, _}};
        BoardMarkerArray boardArray = new BoardMarkerArray(board);
        player.getMove(board);

        BoardStateValues boardStateValues = player.getTranspositionTable().get(boardArray);
        assertEquals(0, boardStateValues.getRow());
        assertEquals(1, boardStateValues.getColumn());
        assertEquals(initialNumberOfCachedMoves, player.getTranspositionTable().size());
    }

    @Test
    public void shouldFullyAnalyze3x3Game(){
        player.getMove(new BoardMarker[][]{{_, _, _}, {_, _, _}, {_, _, _}});
        BoardMarker[][] tieBoard1 = new BoardMarker[][]{{X, X, O}, {O, O, X}, {X, _, O}};
        BoardMarkerArray tieBoardArray1 = new BoardMarkerArray(tieBoard1);
        BoardMarker[][] tieBoard2 = new BoardMarker[][]{{X, X, O}, {O, O, X}, {X, O, _}};
        BoardMarkerArray tieBoardArray2 = new BoardMarkerArray(tieBoard2);

        assertTrue(player.getTranspositionTable().containsKey(tieBoardArray1));
        assertTrue(player.getTranspositionTable().containsKey(tieBoardArray2));
    }

    @Test
    public void shouldOnlySearchToADepthOf2ToStart10x10Game(){
        BoardMarker[][] board0 = new BoardMarker[10][];
        for(int i = 0; i<board0.length; i++){
            BoardMarker[] row = new BoardMarker[10];
            Arrays.fill(row, _);
            board0[i] = row;
        }
        BoardMarkerArray boardArray0 = new BoardMarkerArray(board0);
        BoardMarker[][] board1 = AIPlayer.deep2DArrayCopy(board0);
        board1[0][0] = X;
        BoardMarkerArray boardArray1 = new BoardMarkerArray(board1);
        BoardMarker[][] board2 = AIPlayer.deep2DArrayCopy(board1);
        board2[0][1] = O;
        BoardMarkerArray boardArray2 = new BoardMarkerArray(board2);
        BoardMarker[][] board3 = AIPlayer.deep2DArrayCopy(board2);
        board3[0][2] = X;
        BoardMarkerArray boardArray3 = new BoardMarkerArray(board3);

        player.getMove(board0);



        assertTrue(player.getTranspositionTable().containsKey(boardArray0));
        assertTrue(player.getTranspositionTable().containsKey(boardArray1));
        assertTrue(player.getTranspositionTable().containsKey(boardArray2));
        assertFalse(player.getTranspositionTable().containsKey(boardArray3));
    }
}
