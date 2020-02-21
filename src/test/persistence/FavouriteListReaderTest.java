package persistence;

import org.junit.jupiter.api.Test;
import ui.FavouriteList;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FavouriteListReaderTest {

    @Test
    public void favouriteListReaderTest0() {
        try {
            FavouriteList favList = new FavouriteListReader().read(new File("./data/testFavouriteList0.txt"));
            assertFalse(favList.containGame("minesweeper"));
            assertFalse(favList.containGame("sudoku"));
        } catch (IOException e) {
            fail("unexpected IOException");
        }

    }

    @Test
    public void favouriteListReaderTest1() {
        try {
            FavouriteList favList = new FavouriteListReader().read(new File("./data/testFavouriteList1.txt"));
            assertTrue(favList.containGame("minesweeper"));
            assertFalse(favList.containGame("sudoku"));
            assertEquals("minesweeper", favList.favList.get(0).getDescription());
        } catch (IOException e) {
            fail("unexpected IOException");
        }
    }

    @Test
    public void favouriteListReaderTest2() {
        try {
            FavouriteList favList = new FavouriteListReader().read(new File("./data/testFavouriteList2.txt"));
            assertTrue(favList.containGame("minesweeper"));
            assertTrue(favList.containGame("sudoku"));
            assertEquals("sudoku", favList.favList.get(0).getDescription());
            assertEquals("minesweeper", favList.favList.get(1).getDescription());
        } catch (IOException e) {
            fail("unexpected IOException");
        }
    }

    @Test
    public void testIOException() {
        try {
            FavouriteList testList = new FavouriteListReader().read(new
                    File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}
