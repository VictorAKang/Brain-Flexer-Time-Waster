package model.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Cell {
    int trueValue;
    int currentValue;
    ArrayList<Integer> possibilities;
    boolean modifiable;

    public Cell(int trueValue) {
        this.trueValue = trueValue;
        currentValue = 0;
        possibilities = new ArrayList<>();
        modifiable = true;
    }

    public void makeStatic() {
        currentValue = trueValue;
        modifiable = false;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public void addPossibility(int pos) {
        possibilities.add(pos);
        Collections.sort(possibilities);
    }

    public void removePossibility(int value) {
        //TODO maybe return an error instead
        for (int i = 0; i < possibilities.size(); i++) {
            if (possibilities.get(i) == value) {
                possibilities.remove(i);
                return;
            }
        }
        return;
    }

    public boolean inPossibilities(int value) {
        return possibilities.contains(value);
    }

    public void printCurrentValue() {
        if (currentValue == 0) {
            System.out.print(" ");
            return;
        }
        System.out.print(currentValue);
    }

    public void printPossibilities() {
        String printable = "";
        int freeSpaces = 9 - possibilities.size();;

        for (int k = 0; k < freeSpaces; k++) {
            printable += " ";
        }

        for (int k = 0; k < possibilities.size(); k++) {
            printable += possibilities.get(k) + " ";
        }

        for (int k = 0; k < freeSpaces; k++) {
            printable += " ";
        }

        System.out.print(printable);
    }

    public boolean matchValue() {
        return currentValue == trueValue;
    }
}
