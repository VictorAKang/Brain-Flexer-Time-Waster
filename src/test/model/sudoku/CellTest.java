package model.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    Cell staticCell;
    Cell emptyCell;

    @BeforeEach
    public void setup() {
        staticCell = new Cell(3);
        staticCell.makeStatic();
        emptyCell = new Cell(5);
    }

    @Test
    public void makeStaticTest() {
        assertEquals(3,staticCell.correctValue);
        assertEquals(3,staticCell.visibleValue);
        assertFalse(staticCell.modifiable);
    }

    @Test
    public void setVisibleValueStatic() {
        staticCell.setVisibleValue(1);
        assertEquals(3,staticCell.visibleValue);
        assertFalse(staticCell.modifiable);
        assertEquals(3,staticCell.correctValue);
    }

    @Test
    public void setVisibleValueNonStatic() {
        assertEquals(5,emptyCell.correctValue);
        assertEquals(0,emptyCell.visibleValue);
        assertTrue(emptyCell.modifiable);

        emptyCell.setVisibleValue(6);

        assertEquals(5,emptyCell.correctValue);
        assertEquals(6,emptyCell.visibleValue);
        assertTrue(emptyCell.modifiable);

        emptyCell.setVisibleValue(5);

        assertEquals(5,emptyCell.correctValue);
        assertEquals(5,emptyCell.visibleValue);
        assertTrue(emptyCell.modifiable);
    }

    @Test
    public void drawCellTest() {
        assertTrue(staticCell.drawCell().equals("3"));
        assertTrue(emptyCell.drawCell().equals(" "));

        emptyCell.setVisibleValue(1);

        assertTrue(emptyCell.drawCell().equals("1"));
    }

    @Test
    public void matchValueTest() {
        assertTrue(staticCell.matchValue());
        assertFalse(emptyCell.matchValue());

        emptyCell.setVisibleValue(1);
        assertFalse(emptyCell.matchValue());

        emptyCell.setVisibleValue(5);
        assertTrue(emptyCell.matchValue());
    }
}
