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

    public void setColumn(int column) {
        this.column = column;
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

    public void setRow(int row) {
        this.row = row;
    }

    public int getUpperbound() {
        return upperbound;
    }

    public void setUpperbound(int upperbound) {
        this.upperbound = upperbound;
    }
}
