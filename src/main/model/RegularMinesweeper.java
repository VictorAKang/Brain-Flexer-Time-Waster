package model;

import java.util.Scanner;

public class RegularMinesweeper {
    Grid map;
    boolean isGameOver;

    public RegularMinesweeper() {
        map = new Grid();
        isGameOver = false;
    }

    public void runGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to minesweeper!\n");
        int coordinateX;
        int coordinateY;
        String command;

        while (isGameOver) {
            map.drawGrid();

            System.out.print("Choose a cell:\nX coordinate (e.g. A, B, C): ");
            coordinateX = (int) input.next().charAt(0) - (int) 'A';
            System.out.print("Y coordinate (e.g. 1, 2, 3): ");
            coordinateY = input.nextInt();
            System.out.print("What do you want to do? (open, flag): ");
            command = input.next();

            runCommand();
        }
    }

    private void runCommand() {

    }
}
