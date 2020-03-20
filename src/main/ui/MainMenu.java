package ui;

import ui.menu.FavouriteList;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;

import java.io.IOException;
import java.util.Scanner;

// main menu that leads to all other applications
public class MainMenu {
    int option;
    boolean keepGoing;
    FavouriteList fav;

    public MainMenu() {
        keepGoing = true;
        fav = new FavouriteList();

        while (keepGoing) {
            option = askOption();

            executeOption();
        }
    }

    public int askOption() {
        Scanner input = new Scanner(System.in);
        String stubInput;

        System.out.println("What do want to do? ");
        System.out.println("m - play minesweeper");
        System.out.println("s - play sudoku");
        System.out.println("f - check favourite games list");
        System.out.println("q - quit");
        stubInput = input.next();

        if (! (stubInput.equals("m") || stubInput.equals("s") || stubInput.equals("f") || stubInput.equals("q"))) {
            System.out.println("invalid input...");
            return askOption();
        }

        if (stubInput.equals("m")) {
            return 1;
        } else if (stubInput.equals("s")) {
            return 2;
        } else if (stubInput.equals("f")) {
            return 0;
        } else {
            return -1;
        }
    }

    public void executeOption() {
        if (option == -1) {
            keepGoing = false;
        } else if (option == 1) {
            new Minesweeper();
        } else if (option == 2) {
            new Sudoku();
        } else {
            try {
                fav.runFavList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
