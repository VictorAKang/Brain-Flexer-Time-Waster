package ui.menu;

import persistence.FavouriteListReader;
import persistence.Saveable;
import persistence.Writer;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

// represents the list of favourite games
public class FavouriteList implements Saveable {
    public static final String FAV_LIST = "./data/favouriteList.txt";
    Scanner input = new Scanner(System.in);
    public LinkedList<Game> favList;

    public FavouriteList() {
        favList = new LinkedList<>();
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

        this.favList = new FavouriteListReader().read(new File(FAV_LIST)).favList;

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

        saveFavList(FAV_LIST);
    }

    //MODIFIES: this
    //EFFECTS: runs the game chosen by the user
    public void runFav() {
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
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < favList.size(); i++) {
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

    @Override
    public void save(PrintWriter printWriter) {
        for (Game g: favList) {
            printWriter.println(g.getDescription());
        }
    }

    // EFFECTS: saves state of the favourite list in FAV_LIST
    public void saveFavList(String s) {
        try {
            Writer writer = new Writer(new File(s));
            writer.write(this);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + FAV_LIST);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: straight forward add inputted game into the list
    public void simpleAdd(Game g) {
        favList.add(g);
    }

    public void removeFav(String game) {
        for (int i = 0; i < favList.size(); i++) {
            if (favList.get(i).getDescription().equals(game)) {
                favList.remove(i);
            }
        }
    }
}
