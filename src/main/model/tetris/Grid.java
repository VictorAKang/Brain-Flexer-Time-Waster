package model.tetris;

import java.util.LinkedList;
import java.util.Random;

public class Grid {
    private static Random random = new Random();
    private static final int HEIGHT = 24;
    private static final int WIDTH = 10;

    private Cell[][] grid; // convention adopted is i = 0 is the top of the grid

    private int referenceI;
    private int referenceJ;
    private boolean[][] currentLayout;
    private Tetronimo currentTetronimo;

    private LinkedList<Tetronimo> nextTetronimo;

    public Grid() {
        grid = new Cell[HEIGHT][WIDTH];
        nextTetronimo = new LinkedList<>();
        resetGrid();
    }

    //EFFECTS: returns true if all of the currentCells can go down one row
    //         else returns false
    public boolean canGoDown() {

        Cell reference;
        int referenceI = currentLayout[0][0].getCoordinateI();
        int referenceJ = currentLayout[0][0].getCoordinateJ();
        for (int i = 0; i < currentLayout.length; i++) {
            for (int j = 0; j < currentLayout.length; j++) {
                reference = grid[referenceI + i][referenceJ + j];
                if (reference.getFilled() && !reference.getFixed() &&
                        grid[referenceI + i + 1][referenceJ + j].getFixed()) {
                    return false;
                }
            }
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: if the current piece can be moved down, do so
    public void moveCurrent() {
        if (canGoDown()) {
            gravityOnCurrent();
        }
    }

    //MODIFIES: this
    //EFFECTS: fixes the currentTetronimo and invokes the next one
    public void fixPiece() {
        fixCurrentCells();
        getNext();
    }

    //EFFECTS: returns true if the current piece is on the top most row possible
    public boolean isOut() {
        if (canGoDown()) {
            return false;
        }

        Cell reference;
        int referenceI = currentLayout[0][0].getCoordinateI();
        int referenceJ = currentLayout[0][0].getCoordinateJ();
        for (int i = 0; i < currentLayout.length; i++) {
            for (int j = 0; j < currentLayout.length; j++) {
                reference = grid[referenceI + i][referenceJ + j];
                if (reference.getCoordinateI() == 0 && reference.getFilled() && reference.getFixed()) {

                }
            }
        }

        for (Cell c: currentLayout) {
            if (c.getCoordinateI() == 0 && !canGoDown()) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: move the current piece to the left if it remains in bounds
    //         if it doesn't do nothing
    public void moveCurrentLeft() {
        if (canGoLeft()) {
            Cell referenceCell;
            emptyCurrentCells();
            for (int i = 0; i < 4; i++) {
                referenceCell= currentLayout[i];
                currentLayout[i] = grid[referenceCell.getCoordinateI()][referenceCell.getCoordinateJ() - 1];
            }
            fillCurrentCells();
        }
    }

    //MODIFIES: this
    //EFFECTS: move the current piece to the right if it remains in bounds
    //         if it doesn't do nothing
    public void moveCurrentRight() {
        if (canGoRight()) {
            Cell referenceCell;
            emptyCurrentCells();
            for (int i = 0; i < 4; i++) {
                referenceCell= currentLayout[i];
                currentLayout[i] = grid[referenceCell.getCoordinateI()][referenceCell.getCoordinateJ() + 1];
            }
            fillCurrentCells();
        }
    }

    //MODIFIES: this
    //EFFECTS: resets the grid for a new game
    private void resetGrid() {
        isOut = false;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = new Cell(i,j);
            }
        }
        setInitialPieceList();
    }

    //MODIFIES: this
    //EFFECTS: sets up 3 initial Tetronimo types for the beginning of the game
    private void setInitialPieceList() {
        Tetronimo next;
        for (int i = 0; i < 3; i++) {
            next = makeNextTetronimo();
            nextTetronimo.add(next);
        }
    }

    //TODO: MODIFY this to adequate to the new currentCells
    //MODIFIES: this
    //EFFECTS: moves all of the currentCells down one row
    private void gravityOnCurrent() {
        Cell referenceCell;
        emptyCurrentCells();
        for (int i = 3; i >= 0; i--) {
            referenceCell = currentLayout[i];
            currentLayout[i] = grid[referenceCell.getCoordinateI() + 1][referenceCell.getCoordinateJ()];
        }
        fillCurrentCells();
    }

    //MODIFIES: this
    //EFFECTS: changes all the currentCells to fixed
    private void fixCurrentCells() {
        for (Cell c: currentLayout) {
            if (c.getFilled()) {
                c.fixCell();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: gets the next Tetronimo and sets it as current
    private void getNext() {
        currentTetronimo = nextTetronimo.get(0);
        setNextCurrentCells(nextTetronimo.get(0));
        nextTetronimo.remove(0);
        addNextTetronimo();
    }

    //MODIFIES: this
    //EFFECTS: sets the currentCells to the initial position of the next pieceType
    private void setNextCurrentCells(Tetronimo type) {
        switch (type) {
            case I:
                setupI();
                break;
            case J:
                setupJ();
                break;
            case L:
                setupL();
                break;
            case O:
                setupO();
                break;
            case S:
                setupS();
                break;
            case T:
                setupT();
                break;
            case Z:
                setupZ();
                break;
        }

        fillCurrentCells();
    }

    //TODO: add the exception to the default case
    //MODIFIES: this
    //EFFECTS: creates a new tetronimo and returns it
    private Tetronimo makeNextTetronimo() {
        int type = random.nextInt(6);
        switch (type) {
            case 0:
                return Tetronimo.I;
            case 1:
                return Tetronimo.J;
            case 2:
                return Tetronimo.L;
            case 3:
                return Tetronimo.O;
            case 4:
                return Tetronimo.S;
            case 5:
                return Tetronimo.Z;
            case 6:
                return Tetronimo.T;
            //default: throw new InvalidTypeException();
            default:
                return Tetronimo.I;
        }
    }

    //MODIFIES: this
    //EFFECTS: adds a created tetronimo to the end of nextTetronimo
    private void addNextTetronimo() {
        Tetronimo next = makeNextTetronimo();
        nextTetronimo.add(next);
    }

    //MODIFIES: this
    //EFFECTS: empty the current cells
    private void emptyCurrentCells() {
        for (Cell c: currentLayout) {
            c.emptyCell();
        }
    }

    //EFFECTS: return true if the current piece can go left
    //         else return false
    private boolean canGoLeft() {
        for (Cell c: currentLayout) {
            if (c.getCoordinateJ() == 0) {
                return false;
            }
        }
        return true;
    }

    //EFFECTS: return true if the current piece can go right
    //         else return false
    private boolean canGoRight() {
        for (Cell c: currentLayout) {
            if (c.getCoordinateJ() == WIDTH - 1) {
                return false;
            }
        }
        return true;
    }

    // TODO: modify this
    // the following clauses refer to the following 7 methods
    //
    //MODIFIES: this
    //EFFECTS: sets up the specified tetronimo as the current one
    private void setupI() {
        for (int i = 0; i < 4; i++) {
            currentLayout[i] = grid[0][3 + i];
        }
    }

    private void setupJ() {
        currentLayout[0] = grid[0][3];
        for (int i = 0; i < 3; i++) {
            currentLayout[1 + i] = grid[1][3 + i];
        }
    }

    private void setupL() {
        currentLayout[0] = grid[0][5];
        for (int i = 0; i < 3; i++) {
            currentLayout[1 + i] = grid[1][3 + i];
        }
    }

    private void setupO() {
        currentLayout[0] = grid[0][4];
        currentLayout[1] = grid[0][5];
        currentLayout[2] = grid[1][4];
        currentLayout[3] = grid[1][5];
    }

    private void setupS() {
        currentLayout[0] = grid[0][4];
        currentLayout[1] = grid[0][5];
        currentLayout[2] = grid[1][3];
        currentLayout[3] = grid[1][4];
    }

    private void setupZ() {
        currentLayout[0] = grid[0][3];
        currentLayout[1] = grid[0][4];
        currentLayout[2] = grid[1][4];
        currentLayout[3] = grid[1][5];
    }

    private void setupT() {
        currentLayout[0] = grid[0][4];
        for (int i = 0; i < 3; i++) {
            currentLayout[1 + i] = grid[1][3 + i];
        }
    }

//    //MODIFIES: this
//    //EFFECTS: fill the current cells
//    private void fillCurrentCells() {
//        for (Cell c: currentCells) {
//            c.fillCell();
//        }
//    }

//    public void rotateCurrentClockwise() {
//        currentTetronimo.rotateClockwise();
//        switch (currentTetronimo.getPieceType()) {
//            case Z: ;
//            case T: ;
//            case S: ;
//            case L: ;
//            case J: ;
//            case I:
//                rotateI();
//                break;
//        }
//
//    }
//
//    public void rotateCurrentCounterclockwise() {
//        currentTetronimo.rotateCounterclockwise();
//        switch (currentTetronimo.getPieceType()) {
//            case Z:
//                ;
//            case T:
//                ;
//            case S:
//                ;
//            case L:
//                ;
//            case J:
//                ;
//            case I:
//                rotateI();
//                break;
//        }
//    }
//
//    public void rotateI() {
//        int orientation = currentTetronimo.getOrientation();
//        switch (orientation) {
//            case 0:
//
//        }
//    }
}
