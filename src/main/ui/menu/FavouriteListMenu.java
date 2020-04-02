package ui.menu;

import model.FavouriteList;
import persistence.Saveable;
import persistence.Writer;

import java.io.*;
import java.util.Scanner;

// represents the list of favourite games
public class FavouriteListMenu {
    Scanner input = new Scanner(System.in);
    private FavouriteList favList;

    public FavouriteListMenu() {
        favList = new FavouriteList();
    }

//    public FavouriteList(int i) {
//        try {
//            this = new FavouriteListReader().read(new File(FAV_LIST));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //MODIFIES: this
    //EFFECTS: allows user to add, remove or play games from list
    //         keeps running until told to stop
    public void runFavList() throws IOException {
        String stubInput = "";

        favList.loadFavList();

        while (!stubInput.equals("4")) {
            printList();
            System.out.println("What would you like to do?");
            System.out.println("1 - play a game from your list");
            System.out.println("2 - add a game to your list");
            System.out.println("3 - remove a game from your list");
            System.out.println("4 - return to main menu");
            stubInput = input.next();

            if (!(stubInput.equals("1") || stubInput.equals("2") || stubInput.equals("3") || stubInput.equals("4"))) {
                System.out.println("invalid input...");
                runFavList();
            }

            if (stubInput.equals("1")) {
                runFav();
            } else if (stubInput.equals("2")) {
                addFav();
            } else if (stubInput.equals("3")) {
                removeFav();
            }
        }

        favList.saveFavList();
    }

    //MODIFIES: this
    //EFFECTS: runs the game chosen by the user
    public void runFav() {
        int num;

        printList();
        System.out.println("Which game would you like to play? ");
        num = input.nextInt();

        if (num < 1 || num > favList.getSizeFavList() + 1) {
            System.out.println("invalid input...");
            runFav();
        }

        favList.getGame(num - 1).runGame(1);
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
            if (!favList.containGame("sudoku")) {
                favList.addGame(new Sudoku(1));
            }
        } else if (gameName.equals("m")) {
            if (!favList.containGame("minesweeper")) {
                favList.addGame(new Minesweeper(1));
            }
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

        if (number < 0 || number > favList.getSizeFavList()) {
            System.out.println("invalid input...");
            removeFav();
        }

        if (number == 0) {
            return;
        }

        favList.removeFav(number - 1);
    }

    public void removeFav(String description) {
        for (int i = 0; i < favList.getSizeFavList(); i++) {
            if (favList.getGame(i).getDescription().equals(description)) {
                favList.removeFav(i);
                return;
            }
        }
    }

    //EFFECTS: prints the list in the console
    public void printList() {
        System.out.println("List of Favourite Games:");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < favList.getSizeFavList(); i++) {
            System.out.println((i + 1) + " - " + favList.getGame(i).getDescription());
        }
        System.out.println();
    }

//    //EFFECTS: returns true if the game with inputted description is in list
//    //         else, return false
//    public boolean containGame(String s) {
//        for (int i = 0; i < favList.getSizeFavList(); i++) {
//            if (favList.getGame(i).getDescription().equals(s)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void save(PrintWriter printWriter) {
//        for (Game g: favList) {
//            printWriter.println(g.getDescription());
//        }
//    }
//
//    // EFFECTS: saves state of the favourite list in FAV_LIST
//    public void saveFavList(String s) {
//        try {
//            Writer writer = new Writer(new File(s));
//            writer.write(this);
//            writer.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save accounts to " + FAV_LIST);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //MODIFIES: this
//    //EFFECTS: straight forward add inputted game into the list
//    public void simpleAdd(Game g) {
//        favList.add(g);
//    }
//
//    public boolean Game(String description) {
//        for (Game g: favList) {
//            if (g.getDescription().equals(description)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
