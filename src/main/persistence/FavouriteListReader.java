package persistence;

import ui.menu.FavouriteList;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

// utility class that reads the data previously stored in Favourite List
public class FavouriteListReader implements Reader {

    //EFFECTS: returns the favourite list stored in favouriteList.txt
    @Override
    public FavouriteList read(File file) throws IOException {
        List<String> storedGames;
        storedGames = readFile(file);
        return parseContent(storedGames);
    }

    //EFFECTS: transforms the content of the file into the correspondent favourite game list
    private FavouriteList parseContent(List<String> storedGames) {
        FavouriteList favList = new FavouriteList();

        for (String s: storedGames) {
            if (s.equals("minesweeper")) {
                favList.simpleAdd(new Minesweeper(1));
//            } else if (s.equals("sudoku")) {
//                favList.simpleAdd(new Sudoku(1));
//            }
            } else {
                favList.simpleAdd(new Sudoku(1));
            }
        }

        return favList;
    }

    //EFFECTS: separates lines from the file
    public java.util.List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }
}
