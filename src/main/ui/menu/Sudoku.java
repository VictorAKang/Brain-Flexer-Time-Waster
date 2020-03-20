package ui.menu;

import model.sudoku.Grid;
import ui.menu.Game;

import java.util.Scanner;

// represents the functional runner of sudoku
public class Sudoku implements Game {
    String description = "sudoku";
    Grid game;

    //MODIFIES: this
    //EFFECTS: generates game and runs it
    public Sudoku() {
        game = new Grid();
        runGame();
    }

    //MODIFIES: this
    //EFFECTS: generates game
    public Sudoku(int i) {
        game = new Grid();
    }

    //REQUIRES: generated game
    //EFFECTS: runs game
    public void runGame(int i) {
        runGame();
        game = new Grid();
    }

    //MODIFIES: this
    //EFFECTS: runs game
    public void runGame() {
        int coordinateX;
        int coordinateY;
        int value;

        while (!game.gameComplete()) {
            game.drawGrid();
            System.out.println();

            coordinateX = askCoordinateX();
            coordinateY = askCoordinateY();
            value = askValue();

            game.changeCellValue(coordinateY, coordinateX, value);
            System.out.println();
        }
    }

    //EFFECTS: asks the user to input the value to be inputted into a cell
    //         and reads that input
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

    //EFFECTS: asks the user to input the value of the y coordinate of the cell he/she wants to change
    //         and reads that input
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

    //EFFECTS: asks the user to input the value to x coordinate of the cell he/she wants to change
    //         and reads that input
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

    //EFFECTS: returns the description
    @Override
    public String getDescription() {
        return description;
    }
}
