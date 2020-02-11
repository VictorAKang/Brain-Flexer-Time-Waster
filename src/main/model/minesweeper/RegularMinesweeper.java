package model.minesweeper;

import java.util.Scanner;

public class RegularMinesweeper {
    Grid map;
    boolean isGameOver;

    public RegularMinesweeper() {
        map = new Grid();
        isGameOver = false;
    }

    public void runGame() {
        int cellsToBeOpen = Grid.TOTAL_NUM_CELLS - Grid.NUM_MINES;
        System.out.println("Welcome to minesweeper!\n");
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

    private int askCoordinateY() {
        //TODO implement exception in case a string is inputted here
        Scanner input = new Scanner(System.in);
        //String stringCoordinateY;
        int coordinateY;

        System.out.print("Y coordinate (e.g. 1, 2, 3): ");
//        stringCoordinateY = input.next();
//
//        if (stringCoordinateY.length() != 1 || ! Character.isDigit(stringCoordinateY.charAt(0))) {
//            System.out.println("invalid input...");
//            return askCoordinateY();
//        }
//
//        coordinateY = Integer.parseInt(stringCoordinateY) - 1;

        coordinateY = input.nextInt() - 1;

        if (coordinateY > Grid.HEIGHT || coordinateY < 0) {
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

        if (coordinateX < 0 || coordinateX > (int)'p' - (int)'a') {
            System.out.println("invalid input...");
            return askCoordinateX();
        }

        return coordinateX;

    }

    private String askCommand() {
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

    private void runCommand(int coordinateX, int coordinateY, String command) {
        if (command.equals("open")) {
            isGameOver = map.openCell(coordinateX, coordinateY);
        } else {
            map.flagCell(coordinateX, coordinateY);
        }
    }

    //TODO end game if all mines were flagged and all non mine cells were open
}
