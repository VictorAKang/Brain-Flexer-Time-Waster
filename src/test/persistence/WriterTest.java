package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.FavouriteList;
import ui.Minesweeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    FavouriteList favList;
    static final String TEST_FILE = "./data/testFavouriteList.txt";

    @BeforeEach
    public void setup() {
        favList = new FavouriteList();
        favList.simpleAdd(new Minesweeper(1));
    }

    @Test
    public void writeFavouriteListTest() {
        favList.saveFavList(TEST_FILE);

        try {
            FavouriteList testList = new FavouriteListReader().read(new File(TEST_FILE));
            assertTrue(testList.containGame("minesweeper"));
            assertFalse(testList.containGame("sudoku"));
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

}
