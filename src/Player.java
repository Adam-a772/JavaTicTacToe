import java.io.IOException;

public interface Player {
    String getSymbol();

    int[] getMove(int[][] boardState) throws IOException;
}
