package TicTacToe;

public class BoardStateValues {
    int row, column, value, lowerbound, upperbound;

    public BoardStateValues(int row, int column) {
        this.row = row;
        this.column = column;
        this.value = 0;
        this.lowerbound = Integer.MIN_VALUE;
        this.upperbound = Integer.MAX_VALUE;
    }

    public int getColumn() {
        return column;
    }

    public int getLowerbound() {
        return lowerbound;
    }

    public void setLowerbound(int lowerbound) {
        this.lowerbound = lowerbound;
    }

    public int getRow() {
        return row;
    }

    public int getUpperbound() {
        return upperbound;
    }

    public void setUpperbound(int upperbound) {
        this.upperbound = upperbound;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
