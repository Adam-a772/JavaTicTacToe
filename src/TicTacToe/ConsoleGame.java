package TicTacToe;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import static TicTacToe.BoardMarker.*;

public class ConsoleGame {
    public static void main(String[] args) {
        InputStreamReader inputStream  = new InputStreamReader(System.in);
        PrintWriter outputStream = new PrintWriter(System.out, true);
        ConsoleIO playerIO = new ConsoleIO(inputStream, outputStream);
        ConsoleGameIO gameIO = new ConsoleGameIO(inputStream, outputStream);

        gameIO.promptForPlayAI();
        boolean playAI = gameIO.getYesNo();
        gameIO.promptForPlayFirst();
        boolean playFirst = gameIO.getYesNo();
        gameIO.promptForPlayerSymbol();
        BoardMarker playerSymbol = gameIO.getXorO();
        gameIO.promptForBoardSize();
        int boardSize = gameIO.getBoardSize();

        Player[] players = initializePlayers(playAI, playFirst, playerSymbol, boardSize, playerIO);
        Game game = new Game(players, new TicTacToeBoard(boardSize, 2), new BoardConsoleIO(outputStream));

        BoardMarker winner = game.play();
        gameIO.notifyResult(playAI, playerSymbol, winner);
    }

    private static Player[] initializePlayers(boolean playAI, boolean playFirst, BoardMarker playerSymbol, int boardSize, ConsoleIO playerIO) {
        BoardMarker otherPlayerSymbol = (playerSymbol == X) ? O : X;
        Player player0, player1;
        if(playAI){
            if(playFirst){
                player0 = new HumanPlayer(playerSymbol, playerIO);
                player1 = new AIPlayer(otherPlayerSymbol, new TicTacToeBoard(boardSize, 2));
            } else {
                player0 = new AIPlayer(otherPlayerSymbol, new TicTacToeBoard(boardSize, 2));
                player1 = new HumanPlayer(playerSymbol, playerIO);
            }
        } else {
            player0 = new HumanPlayer(playFirst ? playerSymbol : otherPlayerSymbol, playerIO);
            player1 = new HumanPlayer(playFirst ? otherPlayerSymbol : playerSymbol, playerIO);
        }

        return new Player[]{player0, player1};
    }
}
