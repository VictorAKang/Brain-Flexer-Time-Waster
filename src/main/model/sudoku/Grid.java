package model.sudoku;

public class Grid {
    SeedBank bank = new SeedBank();
    Cell[][] grid;

    public Grid() {
        grid = new Cell[9][9];
        seedToGrid(bank.getSeed());
    }

    public void seedToGrid(Seed seed) {
        Cell accessPoint;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                accessPoint = new Cell(seed.getActualSeed().charAt(j + (i * 9)));

                if (Character.isDigit(seed.getVisibleSeed().charAt(j + (i * 9)))) {
                    accessPoint.makeStatic();
                }

                grid[i][j] = accessPoint;
            }
        }
    }

    public void drawGrid() {
        for (int i = 0; i < 3; i++) {
            drawWholeCell(3 * i);
            drawDottedLine();
            drawWholeCell(3 * i + 1);
            drawDottedLine();
            drawWholeCell(3 * i + 2);

            if (i != 2) {
                drawSolidLine();
            }
        }
    }

    private void drawWholeCell(int i) {
        drawPlainLine();
        drawPlainLine();
        drawLineWithCurrentValue(i);
        drawPlainLine();
        drawGuessLine(i);
    }


    private void drawPlainLine() {
        System.out.print("                   :                   :                  |");
        System.out.print("                   :                   :                  |");
        System.out.println("                   :                   :                  ");
    }

    private void drawSolidLine() {
        System.out.print("——————————————+——————————————+——————————————+");
        System.out.print("——————————————+——————————————+——————————————+");
        System.out.print("——————————————+——————————————+——————————————");
    }

    private void drawDottedLine() {
        System.out.println("...................:...................:...................");
    }

    private void drawLineWithCurrentValue(int i) {
        for (int j = 0; j < 3; j++) {
            System.out.print("         ");
            grid[i][3 * j].printCurrentValue();
            System.out.print("         :         ");
            grid[i][3 * j + 1].printCurrentValue();
            System.out.print("         :         ");
            grid[i][3 * j + 2].printCurrentValue();
            if (j != 2) {
                System.out.print("        |");
            }
        }
        System.out.println();
    }

    private void drawGuessLine(int i) {

        for (int j = 0; j < 3; j++) {
            grid[i][3 * j].printPossibilities();
            System.out.print(":");
            grid[i][3 * j + 1].printPossibilities();
            System.out.print(":");
            grid[i][3 * j + 2].printPossibilities();
            if (j != 2) {
                System.out.print("|");
            }
        }

        System.out.println();
    }


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

    public void addPossibility(int i, int j, int value) {
        grid[i][j].addPossibility(value);
    }

    public void changeCurrentValue(int i, int j, int value) {
        grid[i][j].setCurrentValue(value);
    }

    public void removePossibility(int i, int j, int value) {
        grid[i][j].removePossibility(value);
    }
}
