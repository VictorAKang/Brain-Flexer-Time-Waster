package model.Sudoku;

import java.util.ArrayList;

public class Cell {
    int value;
    ArrayList<Integer> possibilities;

    public Cell() {
        value = 0;
        possibilities = new ArrayList<>();
    }
}
