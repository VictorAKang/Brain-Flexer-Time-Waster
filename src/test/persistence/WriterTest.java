package persistence;

import model.FavouriteList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.menu.FavouriteListMenu;
import ui.menu.Minesweeper;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    FavouriteList favList;
    static final String TEST_FILE = "./data/testFavouriteList.txt";

    @BeforeEach
    public void setup() {
        try {
            favList = new FavouriteList("");
        } catch (IOException e) {
            fail();
        }
        favList.addGame(new Minesweeper(1));
    }

    @Test
    public void writeFavouriteListTest() {
        favList.saveFavListOtherFile(TEST_FILE);

        try {
            FavouriteList testList = new FavouriteList(TEST_FILE);
            assertTrue(testList.containGame("minesweeper"));
            assertFalse(testList.containGame("sudoku"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

}
