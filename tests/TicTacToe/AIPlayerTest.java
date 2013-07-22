package TicTacToe;

import TicTacToe.AIPlayer;
import TicTacToe.TicTacToeBoard;
import static TicTacToe.BoardMarker.*;
import org.junit.Before;
import org.junit.Test;

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
    public void shouldStoreMovesBeforeReturning(){
        player = new AIPlayer(O, board);
        BoardMarkerArray testBoardState = new BoardMarkerArray(new BoardMarker[][]{{X, _, _}, {_, _, _}, {_, _, _}});
        player.getMove(new BoardMarker[][]{{X, _, _}, {_, _, _}, {_, _, _}});
        assertTrue(player.getCachedMoves().containsKey(testBoardState));
    }

    @Test
    public void shouldUseStoredMove(){
        int[] move = player.getMove(board.getState());
        int numCachedMoves = player.getCachedMoves().size();
        board.makeMove(move[0], move[1], X);
        board.makeMove(1, 1, O);
        player.getMove(board.getState());
        assertEquals(numCachedMoves, player.getCachedMoves().size());
    }
}
