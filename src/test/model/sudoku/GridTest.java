package model.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    Grid grid;

    @BeforeEach
    public void setup() {
        grid = new Grid();
    }

    @Test
    public void seedToGridTest() {
        assertEquals(9,grid.grid.length);

        for (int i = 0; i < 9; i++) {
            assertEquals(9, grid.grid[i].length);
        }
    }

    @Test
    public void drawGridTest() {
        grid.drawGrid();
    }

    @Test
    public void gameCompleteTest() {
        assertFalse(grid.gameComplete());

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid.changeCellValue(i,j,grid.grid[i][j].correctValue);
            }
        }

        assertTrue(grid.gameComplete());
    }
}
