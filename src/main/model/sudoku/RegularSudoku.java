package model.sudoku;

import java.util.Scanner;

public class RegularSudoku {
    Grid map;

    public RegularSudoku() {
        map = new Grid();
    }

    public void runGame() {
        int coordinateX;
        int coordinateY;
        int value;
        int mode; //1 for note mode, 0 for change currentValue mode

        System.out.println("Welcome to Sudoku!\n");

        while (!map.gameComplete()) {
            map.drawGrid();
            System.out.println();

            coordinateX = askCoordinateX();
            coordinateY = askCoordinateY();
            mode = askMode();
            value = askValue();

            runCommand(coordinateY, coordinateX, value, mode);
            System.out.println();
        }
    }

    private void runCommand(int i, int j, int value, int mode) {
        if (mode == 1) {
            map.addPossibility(i, j, value);
        } else if (mode == 0) {
            map.changeCurrentValue(i, j, value);
        } else {
            //TODO something about the case in which value is not in possibilities
            map.removePossibility(i, j, value);
        }
    }

    private int askMode() {
        Scanner input = new Scanner(System.in);
        int mode;

        System.out.print("Choose what mode you want to use (0 - not note, 1 - note, 2 - remove note): ");
        mode = input.nextInt();

        if (mode == 1 || mode == 0 || mode == 2) {
            return mode;
        }

        System.out.println("invalid input...");
        return askMode();
    }

    private int askValue() {
        //TODO implement exception in case a string is inputted here
        Scanner input = new Scanner(System.in);
        int value;

        System.out.print("value to be inputted (e.g. 1, 2, 3): ");

        value = input.nextInt();

        if (value > 9 || value < 1) {
            System.out.println("invalid input...");
            return askCoordinateY();
        }

        return value;
    }

    private int askCoordinateY() {
        //TODO implement exception in case a string is inputted here
        Scanner input = new Scanner(System.in);
        int coordinateY;

        System.out.print("Y coordinate (e.g. 1, 2, 3): ");

        coordinateY = input.nextInt() - 1;

        if (coordinateY > 8 || coordinateY < 0) {
            System.out.println("invalid input...");
            return askCoordinateY();
        }

        return coordinateY;
    }

    private int askCoordinateX() {
        Scanner input = new Scanner(System.in);
        String letterCoordinateX;
        int coordinateX;

        System.out.print("X coordinate (e.g. a, b, c): ");
        letterCoordinateX = input.next();

        if (letterCoordinateX.length() > 1 || Character.isDigit(letterCoordinateX.charAt(0))) {
            System.out.println("invalid input...");
            return askCoordinateX();
        }

        coordinateX = (int)letterCoordinateX.charAt(0) - (int)'a';

        if (coordinateX < 0 || coordinateX > (int)'i' - (int)'a') {
            System.out.println("invalid input...");
            return askCoordinateX();
        }

        return coordinateX;
    }
}
