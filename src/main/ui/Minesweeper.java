package ui;

import model.minesweeper.Grid;

import java.util.Scanner;

// represents the functional runner of minesweeper
public class Minesweeper implements Game {
    String description = "minesweeper";
    Grid map;
    boolean isGameOver;

    //MODIFIES: this
    //EFFECTS: generates game and runs it
    public Minesweeper() {
        map = new Grid();
        isGameOver = false;
        runGame();
    }

    //MODIFIES: this
    //EFFECTS: generates game
    public Minesweeper(int i) {
        map = new Grid();
        isGameOver = false;
    }

    //REQUIRES: generated game
    //EFFECTS: runs game
    @Override
    public void runGame(int i) {
        runGame();
        map = new Grid();
        isGameOver = false;
    }

    //MODIFIES: this
    //EFFECTS: runs game
    public void runGame() {
        int cellsToBeOpen = Grid.TOTAL_NUM_CELLS - Grid.NUM_MINES;
        int coordinateX;
        int coordinateY;
        String command;

        while (!isGameOver) {
            map.drawGrid();

            coordinateX = askCoordinateX();
            coordinateY = askCoordinateY();
            command = askCommand();
            System.out.println();
            runCommand(coordinateX, coordinateY, command);

            if (isGameOver) {
                break;
            }

            if (command.equals("open")) {
                cellsToBeOpen--;
                if (cellsToBeOpen == 0) {
                    System.out.println("Congratulations! You won!");
                    isGameOver = true;
                }
            }
        }
    }

    //EFFECTS: ask the user to input the y coordinate of the cell that he/she wishes to change
    public int askCoordinateY() {
        Scanner input = new Scanner(System.in);
        String stringCoordinateY;
        int coordinateY;

        System.out.print("Y coordinate (e.g. 1, 2, 3): ");
        stringCoordinateY = input.next();

        if (!(stringCoordinateY.length() == 1 && Character.isDigit(stringCoordinateY.charAt(0)))) {
            System.out.println("invalid input...");
            return askCoordinateY();
        }

        coordinateY = (int)stringCoordinateY.charAt(0) - (int)'1';
//
//        coordinateY = input.nextInt() - 1;

        if (coordinateY > Grid.HEIGHT - 1 || coordinateY < 0) {
            System.out.println(coordinateY);
            System.out.println("invalid input...");
            return askCoordinateY();
        }

        return coordinateY;
    }

    //EFFECTS: ask the user to input the x coordinate of the cell that he/she wishes to change
    public int askCoordinateX() {
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

        if (coordinateX < 0 || coordinateX > (int)'p' - (int)'a') {
            System.out.println("invalid input...");
            return askCoordinateX();
        }

        return coordinateX;

    }

    //EFFECTS: ask the user to input the command he/she wishes to execute on referenced cell
    public String askCommand() {
        Scanner input = new Scanner(System.in);
        String command;

        System.out.print("Choose what to do with the chosen cell (open or flag): ");
        command = input.next();

        if (command.equals("open") || command.equals("flag")) {
            return command;
        }

        System.out.println("invalid input...");
        return askCommand();
    }

    //
    //TODO add conditions to guarantee that the command can be executed
    public void runCommand(int coordinateX, int coordinateY, String command) {
        if (command.equals("open")) {
            isGameOver = map.openCell(coordinateX, coordinateY);
        } else {
            map.flagCell(coordinateX, coordinateY);
        }
    }

    //EFFECTS: returns the description
    @Override
    public String getDescription() {
        return description;
    }
}
