package persistence;

import model.FavouriteList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FavouriteListReaderTest {

    @Test
    public void favouriteListReaderTest0() {
        FavouriteList favList = null;
        try {
            favList = new FavouriteList("./data/testFavouriteList0.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(favList.containGame("minesweeper"));
        assertFalse(favList.containGame("sudoku"));
    }

    @Test
    public void favouriteListReaderTest1() {
        FavouriteList favList = null;
        try {
            favList = new FavouriteList("./data/testFavouriteList1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(favList.containGame("sudoku"));
        assertFalse(favList.containGame("minesweeper"));
        assertEquals("sudoku", favList.getGame(0).getDescription());
    }

    @Test
    public void favouriteListReaderTest2() {
        FavouriteList favList = null;
        try {
            favList = new FavouriteList("./data/testFavouriteList2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(favList.containGame("minesweeper"));
        assertTrue(favList.containGame("sudoku"));
        assertEquals("sudoku", favList.getGame(0).getDescription());
        assertEquals("minesweeper", favList.getGame(1).getDescription());
    }

    @Test
    public void testIOException() {
        try {
            FavouriteList testList = new FavouriteList("./path/does/not/exist/testAccount.txt");
        } catch (IOException e) {
            // expected
        }
    }
}
