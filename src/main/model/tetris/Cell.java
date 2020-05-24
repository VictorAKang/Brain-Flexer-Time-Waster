package model.tetris;

import exceptions.OutOfRangeException;

// TODO: add the colour feature
// represents a cell in the grid
public class Cell {
    private final int coordinateI; // Height, 0 < i < 24
    private final int coordinateJ; // Width, 0 < j < 10
    private boolean filled;
    private boolean fixed;
    //private String colour;

    public Cell(int coordinateI, int coordinateJ) throws OutOfRangeException {
        if (coordinateI < 0 || coordinateI > 24) {
            throw new OutOfRangeException();
        }
        if (coordinateJ < 0 || coordinateJ > 10) {
            throw  new OutOfRangeException();
        }

        filled = false;
        fixed = false;
        this.coordinateI = coordinateI;
        this.coordinateJ = coordinateJ;
    }

    //MODIFIES: this
    //EFFECTS: changes the status of the cell to filled
    public void fillCell() {
        filled = true;
    }

    //MODIFIES: this
    //EFFECTS: changes the status of the cell to not filled
    public void emptyCell() {
        if (!fixed) {
            filled = false;
        }
    }

    //MODIFIES: this
    //EFFECTS: changes the cell status to fixed
    public void fixCell() {
        fixed = true;
    }

    public int getCoordinateI() {
        return coordinateI;
    }

    public int getCoordinateJ() {
        return coordinateJ;
    }

    public boolean getFilled() {
        return filled;
    }

    public boolean getFixed() {
        return fixed;
    }
}
