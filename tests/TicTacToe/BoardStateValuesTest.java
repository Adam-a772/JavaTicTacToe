package TicTacToe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardStateValuesTest {
    BoardStateValues bsv;

    @Before
    public void initialize() {
        bsv = new BoardStateValues(1, 2);
    }
    @Test
    public void testGetColumn() {
        assertEquals(2, bsv.getColumn());
    }

    @Test
    public void testSetColumn(){
        bsv.setColumn(6);
        assertEquals(6, bsv.getColumn());
    }

    @Test
    public void testGetLowerbound() {
        assertEquals(Integer.MIN_VALUE, bsv.getLowerbound());
    }

    @Test
    public void testSetLowerbound() {
        bsv.setLowerbound(5);
        assertEquals(5, bsv.getLowerbound());
    }

    @Test
    public void testGetRow() {
        assertEquals(1, bsv.getRow());
    }

    @Test
    public void testSetRow(){
        bsv.setRow(6);
        assertEquals(6, bsv.getRow());
    }

    @Test
    public void testGetUpperbound() {
        assertEquals(Integer.MAX_VALUE, bsv.getUpperbound());
    }

    @Test
    public void testSetUpperbound() {
        bsv.setUpperbound(6);
        assertEquals(6, bsv.getUpperbound());
    }

    @Test
    public void testSetRCLU(){
        bsv.setRCLU(5, 10, 15, 20);

        assertEquals(5, bsv.getRow());
        assertEquals(10, bsv.getColumn());
        assertEquals(15, bsv.getLowerbound());
        assertEquals(20, bsv.getUpperbound());
    }

    @Test
    public void testSetRCLUSkipsNullArgs(){
        bsv.setRCLU(5, 10, 15, 20);

        bsv.setRCLU(25, null, null, null);
        assertEquals(25, bsv.getRow());
        assertEquals(10, bsv.getColumn());
        assertEquals(15, bsv.getLowerbound());
        assertEquals(20, bsv.getUpperbound());

        bsv.setRCLU(null, 30, null, null);
        assertEquals(25, bsv.getRow());
        assertEquals(30, bsv.getColumn());
        assertEquals(15, bsv.getLowerbound());
        assertEquals(20, bsv.getUpperbound());

        bsv.setRCLU(null, null, 35, null);
        assertEquals(25, bsv.getRow());
        assertEquals(30, bsv.getColumn());
        assertEquals(35, bsv.getLowerbound());
        assertEquals(20, bsv.getUpperbound());

        bsv.setRCLU(null, null, null, 40);
        assertEquals(25, bsv.getRow());
        assertEquals(30, bsv.getColumn());
        assertEquals(35, bsv.getLowerbound());
        assertEquals(40, bsv.getUpperbound());

    }
}
