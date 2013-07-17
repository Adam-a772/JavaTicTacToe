import java.io.IOException;

public class MockPlayer implements Player {
    private int[] nextMove;
    private String symbol;
    private int timesGetMove;

    public MockPlayer(String sym){
        symbol = sym;
        timesGetMove = 0;
        nextMove = new int[]{0, 0};
    }
    @Override
    public String getSymbol() {
        return symbol;
    }

    public void setNextMove(int[] move){
        nextMove = move;
    }
    @Override
    public int[] getMove(int[][] boardState) {
        ++timesGetMove;
        return nextMove;
    }

    public int getTimesGetMove(){
        return timesGetMove;
    }
}
