package model.sudoku;

// represents the entire board
public class Grid {
    Seed seed;
    Cell[][] grid;

    public Grid() {
        grid = new Cell[9][9];
        seed = new Seed("523986147164735289897241563439512678782694351651873924346128795278359416915467832",
                "..3.861....47...8....2...6.4.........82....5......392.346..8..5.7...9.....5.6...2");
        seedToGrid();
    }

    //MODIFIES: this
    //EFFECTS: use seed to generate the board
    public void seedToGrid() {
        Seed seed = new Seed();
        Cell accessPoint;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                accessPoint = new Cell(Integer.parseInt(String.valueOf(seed.getActualSeed().charAt(j + (i * 9)))));

                if (Character.isDigit(seed.getVisibleSeed().charAt(j + (i * 9)))) {
                    accessPoint.makeStatic();
                }

                grid[i][j] = accessPoint;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: modifies the visible value of cell [i][j] to the inputted value
    public void changeCellValue(int i, int j, int value) {
        grid[i][j].setVisibleValue(value);
    }

    //EFFECTS: draws the entire board
    public void drawGrid() {
        System.out.println("     a   b   c   d   e   f   g   h   i\n");
        for (int i = 0; i < 3; i++) {
            System.out.print((3 * i + 1) + " - ");
            drawLineWithValues(3 * i);
            drawDottedLine();
            System.out.print((3 * i + 2) + " - ");
            drawLineWithValues((3 * i) + 1);
            drawDottedLine();
            System.out.print((3 * i + 3) + " - ");
            drawLineWithValues((3 * i) + 2);

            if (i != 2) {
                drawSolidLine();
            }
        }
        System.out.println();
    }

    //EFFECTS: draws a straight solid line
    public void drawSolidLine() {
        System.out.println("    -----------+-----------+-----------");
    }

    //EFFECTS: draws a dotted line
    public void drawDottedLine() {
        System.out.println("    ...:...:...|...:...:...|...:...:...");
    }

    //EFFECTS: draws a line that has the visible values of each cell in it
    public void drawLineWithValues(int i) {
        for (int j = 0; j < 9; j++) {
            System.out.print(" ");
            System.out.print(grid[i][j].drawCell());
            System.out.print(" ");
            if (j != 2 && j != 5 && j != 8) {
                System.out.print(":");
            } else if (j == 2 || j == 5) {
                System.out.print("|");
            }
        }

        System.out.println();
    }


    //EFFECTS: returns true if all cells are correctly filled
    public boolean gameComplete() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!grid[i][j].matchValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
