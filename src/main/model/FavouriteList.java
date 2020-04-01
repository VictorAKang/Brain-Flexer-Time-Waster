package model;

import persistence.FavouriteListReader;
import persistence.Saveable;
import persistence.Writer;
import ui.menu.Game;

import java.io.*;
import java.util.LinkedList;

public class FavouriteList implements Saveable {
    public static final String FAV_LIST = "./data/favouriteList.txt";
    private LinkedList<Game> favList;

    // constructor that should be called throughout the code
    public FavouriteList() {
        favList = new LinkedList<>();
        loadFavList();
    }

    // constructor for testing, loading from another path or no path if path is empty
    public FavouriteList(String path) throws IOException {
        favList = new LinkedList<>();

        if (!path.equals("")) {
            loadFavList(path);
        }
    }

    //MODIFIES: this
    //EFFECTS: straight forward add inputted game into the list
    public void addGame(Game g) {
        favList.add(g);
    }

    //MODIFIES: this
    //EFFECTS: removes the game with description description from favList
    public void removeFav(String description) {
        for (int i = 0; i < favList.size(); i++) {
            if (favList.get(i).getDescription().equals(description)) {
                favList.remove(i);
                return;
            }
        }
    }

    public void removeFav(int i) {
        favList.remove(i);
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
    public void saveFavList() {
        try {
            Writer writer = new Writer(new File(FAV_LIST));
            writer.write(this);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + FAV_LIST);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: saves state of the favourite list in path
    public void saveFavListOtherFile(String path) {
        try {
            Writer writer = new Writer(new File(path));
            writer.write(this);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + path);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the favourite list from the previous section
    public void loadFavList() {
        try {
            favList = new FavouriteListReader().readList(new File(FAV_LIST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the favourite from given path
    private void loadFavList(String path) throws IOException {
        favList = new FavouriteListReader().readList(new File(path));
    }

    //EFFECTS: returns the size of the favList
    public int getSizeFavList() {
        return favList.size();
    }

    //EFFECTS: returns the game at position i
    public Game getGame(int i) {
        return favList.get(i);
    }

    //EFFECTS: returns true if the both FavouriteLists have both the same list and both have the same order
    public boolean equals(FavouriteList favouriteList) {
        if (getSizeFavList() == favouriteList.getSizeFavList()) {
            for (int i = 0; i < getSizeFavList(); i++) {
                if (!getGame(i).getDescription().equals(favouriteList.getGame(i).getDescription())) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
