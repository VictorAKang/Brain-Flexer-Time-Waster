package model;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;

public class Cell {
    private boolean isOpen;
    private boolean isMine;
    private boolean isFlagged;
    private int adjacentBombs;

    public Cell() {
        isOpen = false; //true if  cell is open, false if closed
        //TODO change this when determined the mine generation method
        isMine = false; //true if cell is mine, false if not
        isFlagged = false; //true if cell is flagged, false if not or is open
        //TODO change this when found a better way to solve this problem
        adjacentBombs = 0; //number of adjacent bombs, counts diagonal as adjacent
    }

    //REQUIRES: cell must not be an open bomb
    //EFFECTS: returns adjacentBombs in case it is open
    //                 F if cell is closed and isFlagged
    //                 # if cell is closed
    public String draw() {
        if (isOpen) {
            return Integer.toString(adjacentBombs);
        }
        if (isFlagged) {
            return "F";
        }
        return "#";
    }

    //EFFECTS: returns true if mine is open, false if it's not
    public boolean getIsOpen() {
        return isOpen;
    }

    //REQUIRES: must not be open
    //MODIFIES: this
    //EFFECTS: returns false if the cell is a mine,
    //         else, return true and change cell status to open and remove flag if necessary
    public boolean openCell() {
        if (this.isMine = true) {
            return false;
        }
        this.isOpen = true;
        this.isFlagged = false;
        return true;
        //TODO add/change something that explodes in case this is a bomb
    }

    public void makeMine() {
        isMine = true;
    }

    //EFFECTS: return true if cell is a mine, else return false
    public boolean getIsMine() {
        return isMine;
    }

    //EFFECTS: return true if cell is marked with flag, else return false
    public boolean getIsMarked() {
        return isFlagged;
    }

    //REQUIRES: cell must be closed
    //MODIFIES: this
    //EFFECTS: change the status of whether cell is flagged or not
    public void changeMarking() {
        this.isFlagged = !this.isFlagged;
    }

    //EFFECTS: return the amount of bombs adjacent to cell
    public int getAdjacentBombs() {
        return adjacentBombs;
    }

    //MODIFIES: this
    //EFFECTS: changes the value of the adjacentBombs
    public void setAdjacentBombs(int adjacent) {
        this.adjacentBombs = adjacent;
    }
}
