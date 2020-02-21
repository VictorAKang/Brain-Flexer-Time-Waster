package model.minesweeper;

import model.minesweeper.Cell;
import model.minesweeper.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    Grid grid;

    @BeforeEach
    public void setup() {
        grid  = new Grid();
    }

    @Test
    public void constructorTest() {
        grid = new Grid();
    }

    @Test
    public void shuffleTest() {
        ArrayList<Cell> testingArray = new ArrayList<>();
        ArrayList<Cell> testResult;
        Cell referenceCell;
        grid = new Grid();

        for (int i = 0; i < 99; i++) {
            referenceCell = new Cell();
            referenceCell.makeMine();

            testingArray.add(referenceCell);
        }

        for (int i = 0; i < 16 * 30 - 99; i++) {
            referenceCell = new Cell();

            testingArray.add(referenceCell);
        }

        testResult = grid.shuffle(testingArray);

        assertEquals(16 * 30,testResult.size());
        assertEquals(99,countMines(testResult));
        assertEquals(16 * 30 - 99,countNotMines(testResult));
    }

    @Test
    public void drawGridTest() {
        grid.drawGrid();
    }

    @Test
    public void opeCellTest() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 16; j++) {
                if (grid.grid[i][j].getIsMine()) {
                    assertTrue(grid.openCell(j,i));
                } else {
                    assertFalse(grid.openCell(j,i));
                }
            }
        }
    }

    @Test
    public void flagCellTest() {
        grid.flagCell(0,1);
        assertTrue(grid.grid[1][0].isFlagged());
        grid.flagCell(0,1);
        assertFalse(grid.grid[1][0].isFlagged());
    }

    @Test
    public void openFlaggedCellTest() {
        grid.flagCell(0,0);
        assertTrue(grid.openCell(0,0));
    }


    public int countMines(ArrayList<Cell> array) {
        int answer = 0;

        for (Cell c: array) {
            if (c.getIsMine()) {
                answer++;
            }
        }

        return answer; 
    }

    public int countNotMines(ArrayList<Cell> array) {
        int answer = 0;

        for (Cell c: array) {
            if (!c.getIsMine()) {
                answer++;
            }
        }

        return answer;
    }
}
