package model.sudoku;

// represents a sudoku cell, with a currentValue, a trueValue, and a modifiable clause
public class Cell {
    int correctValue;
    int visibleValue;
    boolean modifiable;

    public Cell(int correctValue) {
        this.correctValue = correctValue;
        visibleValue = 0;
        modifiable = true;
    }

    //MODIFIES: this
    //EFFECTS: makes the visible value of the same as the correct one and makes it imodifiable.
    public void makeStatic() {
        visibleValue = correctValue;
        modifiable = false;
    }

    //MODIFIES: this
    //EFFECTS: if the cell is modifiable, change the visible value
    //         else, do nothing
    public void setVisibleValue(int visibleValue) {
        if (modifiable) {
            this.visibleValue = visibleValue;
        }
    }

    //EFFECTS: returns the visible value of cell if it's not 0
    //         if it is, returns and empty space
    public String drawCell() {
        if (visibleValue == 0) {
            return " ";
        }
        return Integer.toString(visibleValue);
    }

    //EFFECTS: returns true if the visible value matches the correct value
    public boolean matchValue() {
        return visibleValue == correctValue;
    }
}
