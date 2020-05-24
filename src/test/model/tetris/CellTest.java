package model.tetris;

import exceptions.OutOfRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private Cell c1;
    private Cell c2;

    @BeforeEach
    public void setup() {
        c1 = new Cell(0, 0);
        c2 = new Cell(24, 10);
    }

    @Test
    public void constructorTest() {
        assertFalse(c1.getFilled());
        assertEquals(0, c1.getCoordinateI());
        assertEquals(0, c1.getCoordinateJ());
        assertFalse(c1.getFixed());
        assertEquals(24, c2.getCoordinateI());
        assertEquals(10, c2.getCoordinateJ());
        assertFalse(c2.getFixed());
    }

    @Test
    public void constructorExceptionTest() {
        try {
            c1 = new Cell(10,24);
            fail("Expected OutOfRangeException");
        } catch (OutOfRangeException e) {
            //expected
        }
        try {
            c1 = new Cell(100,2);
            fail("Expected OutOfRangeException");
        } catch (OutOfRangeException e) {
            //expected
        }
        try {
            c1 = new Cell(-10,2);
            fail("Expected OutOfRangeException");
        } catch (OutOfRangeException e) {
            //expected
        }
        try {
            c1 = new Cell(10,-4);
            fail("Expected OutOfRangeException");
        } catch (OutOfRangeException e) {
            //expected
        }
    }

    @Test
    public void fillCellTest() {
        c1.fillCell();
        assertTrue(c1.getFilled());
        c1.fillCell();
        assertTrue(c1.getFilled());
        c1.emptyCell();
        assertFalse(c1.getFilled());
        c1.emptyCell();
        assertFalse(c1.getFilled());
    }

    @Test
    public void fixCellTest() {
        c1.fixCell();
        assertTrue(c1.getFixed());
        c1.fixCell();
        assertTrue(c1.getFixed());
    }

    @Test
    public void emptyFixedCellTest() {
        c1.fillCell();
        c1.fixCell();
        c1.emptyCell();
        assertTrue(c1.getFilled());
    }
}
