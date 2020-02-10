package model;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    static final int WIDTH = 16;
    static final int HEIGHT = 30;
    static final int NUM_MINES = 99;
    static final int TOTAL_NUM_CELLS = WIDTH * HEIGHT;

    private Cell[][] grid; //represents the field in the current state of the game

    public Grid() {
        grid = new Cell[HEIGHT][WIDTH];
        genGrid();
    }

    //MODIFIES: this
    //EFFECTS: returns a grid with randomly placed mines and
    //         all cells that are not mines set (correct values for adjacent bombs) to play the game
    public void genGrid() {
        ArrayList<Cell> toBeRandomized = new ArrayList<>();
        Cell referenceCell;

        for (int i = 0; i < NUM_MINES; i++) {
            referenceCell = new Cell();
            referenceCell.makeMine();

            toBeRandomized.add(referenceCell);
        }

        for (int i = 0; i < TOTAL_NUM_CELLS - NUM_MINES; i++) {
            referenceCell = new Cell();

            toBeRandomized.add(referenceCell);
        }

        ArrayList<Cell> randomizedField;
        randomizedField = shuffle(toBeRandomized);

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = randomizedField.get(j + (WIDTH * i));
            }
        }

        setAllNonMineCells();
    }

    //REQUIRES: input array must be of size TOTAL_NUM_CELLS
    //MODIFIES: input list
    //EFFECTS: shuffles the inputted list
    private ArrayList<Cell> shuffle(ArrayList<Cell> toBeRandomized) {
        Cell helperCell;
        Random rand = new Random();
        int swapIndex;

        for (int i = 0; i < 175; i++) {
            for (int j = 0; j < TOTAL_NUM_CELLS; j++) {
                swapIndex = rand.nextInt(TOTAL_NUM_CELLS);

                helperCell = toBeRandomized.get(swapIndex);
                toBeRandomized.set(swapIndex, toBeRandomized.get(j));
                toBeRandomized.set(j,helperCell);
            }
        }

        return toBeRandomized;
    }

    //MODIFIES: this
    //EFFECTS: set the value of adjacentMines of all nonMineCells to the correct value
    private void setAllNonMineCells() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (!grid[i][j].getIsMine()) {
                    grid[i][j].setAdjacentBombs(findAdjacentMines(i, j));
                }
            }
        }
    }

    //EFFECTS: returns the number of adjacent mines relative to a cell in the grid
    private int findAdjacentMines(int i, int j) {
        int answer = 0;
        int minI = i - 1;
        int maxI = i + 1;
        int minJ = j - 1;
        int maxJ = j + 1;

        if (i == 0) {
            minI = i;
        } else if (i == HEIGHT - 1) {
            maxI = i;
        }

        if (j == 0) {
            minJ = j;
        } else if (j == WIDTH - 1) {
            maxJ = j;
        }

        for (int p = minI; p < maxI; p++) {
            for (int q = minJ; q < maxJ; q++) {
                if (grid[p][q].getIsMine()) {
                    answer++;
                }
            }
        }

        return answer;
    }


    //REQUIRES: grid must have cells in it
    //EFFECTS: print a representation of the grid with a coordinate system on its edges
    public void drawGrid() {
        String line;
        System.out.println("    A B C D E F G H I J K L M N O P");

        for (int i = 0; i < 9; i++) {
            line = "";
            line += (i + 1) + "-  ";
            for (int j = 0; j < WIDTH; j++) {
                line += grid[i][j].draw() + " ";
            }
            System.out.println(line);
        }

        for (int i = 9; i < HEIGHT; i++) {
            line = "";
            line += (i + 1) + "- ";
            for (int j = 0; j < WIDTH; j++) {
                line += grid[i][j].draw() + " ";
            }
            System.out.println(line);
        }
    }
}
