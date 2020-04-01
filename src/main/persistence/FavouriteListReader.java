package persistence;

import model.FavouriteList;
import ui.menu.Game;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

// utility class that reads the data previously stored in Favourite List
public class FavouriteListReader {

    //EFFECTS: returns the list of games stored in favouriteList.txt
    public LinkedList<Game> readList(File file) throws IOException {
        List<String> storedGames;
        storedGames = readFile(file);
        return parseContentIntoList(storedGames);
    }

    //EFFECTS: transforms the content of the file into the correspondent List of games
    private LinkedList<Game> parseContentIntoList(List<String> storedGames) {
        LinkedList<Game> favList = new LinkedList<>();

        for (String s: storedGames) {
            if (s.equals("minesweeper")) {
                favList.add(new Minesweeper(1));
            } else {
                favList.add(new Sudoku(1));
            }
        }

        return favList;

    }

    //EFFECTS: separates lines from the file
    public List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }
}
