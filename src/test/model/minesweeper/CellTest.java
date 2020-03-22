package model.minesweeper;

import model.minesweeper.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    Cell emptyCell;
    Cell mine;
    Cell flaggedCell;
    Cell flaggedMine;

    @BeforeEach
    public void setup() {
        emptyCell = new Cell();
        emptyCell.setAdjacentBombs(3);

        mine = new Cell();
        mine.makeMine();

        flaggedCell = new Cell();
        flaggedCell.setAdjacentBombs(1);
        flaggedCell.changeMarking();

        flaggedMine = new Cell();
        flaggedMine.makeMine();
        flaggedMine.changeMarking();
    }

    @Test
    public void drawTest() {
        assertEquals("#",emptyCell.draw());
        assertEquals("#",mine.draw());
        assertEquals("F",flaggedCell.draw());
        assertEquals("F",flaggedMine.draw());
    }

    @Test
    public void openCellTest() {
        assertFalse(emptyCell.openCell());
        assertTrue(mine.openCell());
        assertFalse(flaggedCell.openCell());
        assertFalse(flaggedMine.openCell());
    }

    @Test
    public void drawAfterOpenTest() {
        assertFalse(emptyCell.openCell());
        assertEquals("3",emptyCell.draw());
    }
}
