package ui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// represents the list of favourite games
public class FavouriteList {
    LinkedList<Game> favList;

    public FavouriteList() {
        favList = new LinkedList<Game>();
    }

    //MODIFIES: this
    //EFFECTS: allows user to add, remove or play games from list
    public void runFavList() {
        Scanner input = new Scanner(System.in);
        String stubInput;

        printList();
        System.out.println("What would you like to do?");
        System.out.println("1 - play a game from your list");
        System.out.println("2 - add a game to your list");
        System.out.println("3 - remove a game from your list");
        System.out.println("4 - return to main menu");
        stubInput = input.next();

        if (! (stubInput.equals("1") || stubInput.equals("2") || stubInput.equals("3") || stubInput.equals("4"))) {
            System.out.println("invalid input...");
            runFavList();
        }

        if (stubInput.equals("1")) {
            runFav();
        } else if (stubInput.equals("2")) {
            addFav();
        } else if (stubInput.equals("3")) {
            removeFav();
        } else {
            return;
        }
    }

    //MODIFIES: this
    //EFFECTS: runs the game chosen by the user
    public void runFav() {
        Scanner input = new Scanner(System.in);
        int num;

        printList();
        System.out.println("Which game would you like to play? ");
        num = input.nextInt();

        if (num < 1 || num > favList.size() + 1) {
            System.out.println("invalid input...");
            runFav();
        }

        favList.get(num - 1).runGame(1);
    }

    //MODIFIES: this
    //EFFECTS: adds the chosen game to the list
    public void addFav() {
        Scanner input = new Scanner(System.in);
        String gameName;

        printList();
        System.out.println("which game would like to add? ");
        System.out.println("m - minesweeper");
        System.out.println("s - sudoku");
        System.out.println("r - return");
        gameName = input.next();

        if (! (gameName.equals("m") || gameName.equals("s") || gameName.equals("r"))) {
            System.out.println("invalid input...");
            addFav();
        }

        if (gameName.equals("s")) {
            if (!containGame("sudoku")) {
                favList.add(new Sudoku(1));
            }
        } else if (gameName.equals("m")) {
            if (!containGame("minesweeper")) {
                favList.add(new Minesweeper(1));
            }
        } else {
            return;
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the chosen game from the list
    public void removeFav() {
        Scanner input = new Scanner(System.in);
        int number;

        printList();
        System.out.println("Which game would you like to remove? (0 to cancel)");
        number = input.nextInt();

        if (number < 0 || number > favList.size()) {
            System.out.println("invalid input...");
            removeFav();
        }

        if (number == 0) {
            return;
        }

        favList.remove(number - 1);
    }

    //EFFECTS: prints the list in the console
    public void printList() {
        System.out.println("List of Favourite Games:");
        for (int i = 0; i < favList.size(); i++) {
            System.out.println("-----------------------------------------------------");
            System.out.println((i + 1) + " - " + favList.get(i).getDescription());
        }
        System.out.println();
    }

    //EFFECTS: returns true if the game with inputted description is in list
    //         else, return false
    public boolean containGame(String s) {
        for (int i = 0; i < favList.size(); i++) {
            if (favList.get(i).getDescription().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
