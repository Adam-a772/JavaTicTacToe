package TicTacToe;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import static TicTacToe.BoardMarker.*;

public class ConsoleGame {
    public static void main(String[] args) {
        InputStreamReader inputStream  = new InputStreamReader(System.in);
        PrintWriter outputStream = new PrintWriter(System.out, true);
        ConsoleIO playerIO = new ConsoleIO(inputStream, outputStream);
        Scanner inScanner = new Scanner(inputStream);
        Player player0, player1;
        boolean playAsX = true;
        boolean playFirst = true;

        outputStream.println("Would you like to play against an AI opponent? (yes or no)");
        boolean playAI = inScanner.nextLine().matches("^[yY].*");
        if(playAI){
            outputStream.println("Would you like to go first? (yes or no)");
            playFirst = inScanner.nextLine().matches("^[yY].*");
            outputStream.println("Would you like to be Xs or Os? (X or O)");
            playAsX = inScanner.nextLine().matches("^[xX].*");
        } else {
            outputStream.println("Would you like Xs or Os to move first? (X or O)");
            playAsX = inScanner.nextLine().matches("^[xX].*");
        }

        if(playAI && playFirst){
            player0 = new HumanPlayer((playAsX ? X : O), playerIO);
            player1 = new AIPlayer((playAsX ? O : X), new TicTacToeBoard());
        } else if(playAI){
            player0 = new AIPlayer((playAsX ? O : X), new TicTacToeBoard());
            player1 = new HumanPlayer((playAsX ? X : O), playerIO);
        } else {
            player0 = new HumanPlayer((playAsX ? X : O), playerIO);
            player1 = new HumanPlayer((playAsX ? O : X), playerIO);
        }

        Player[] players = new Player[]{player0, player1};
        Game game = new Game(players, new TicTacToeBoard(), new BoardConsoleIO(outputStream));

        BoardMarker winner = game.play();
        if(winner == T){
            outputStream.println("It was a tie.");
        } else if(playAI) {
            if((playAsX && winner == X) || (!playAsX && winner == O)) {
                outputStream.println("You won!");
            } else {
                outputStream.println("You lost!");
            }
        } else {
            outputStream.println("TicTacToe.Player with " + (winner == X ? "X" : "O") + "s won!");
        }
    }
}
