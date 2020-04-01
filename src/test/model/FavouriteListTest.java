package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FavouriteListTest {
    FavouriteList favouriteListEmpty;
    FavouriteList favouriteList;

    @BeforeEach
    public void setup() {
        try {
            favouriteListEmpty = new FavouriteList("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        favouriteList = new FavouriteList();
    }

    @Test
    public void testConstructor() {
        assertEquals(0,favouriteListEmpty.getSizeFavList());

        FavouriteList referenceList;
        try {
            referenceList = new FavouriteList(FavouriteList.FAV_LIST);
            assertTrue(favouriteList.equals(referenceList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEquals() {
        try {
            FavouriteList referenceList = new FavouriteList("");
            assertTrue(favouriteListEmpty.equals(referenceList));
            referenceList.addGame(new Minesweeper(1));
            assertFalse(referenceList.equals(favouriteListEmpty));
            favouriteListEmpty.addGame(new Sudoku(1));
            assertFalse(referenceList.equals(favouriteListEmpty));
            favouriteListEmpty.addGame(new Minesweeper(1));
            assertFalse(referenceList.equals(favouriteListEmpty));
            referenceList.addGame(new Sudoku(1));
            assertFalse(referenceList.equals(favouriteListEmpty));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeFavTest() {
        assertFalse(favouriteListEmpty.containGame("sudoku"));
        favouriteListEmpty.removeFav("sudoku");
        assertFalse(favouriteListEmpty.containGame("sudoku"));
        favouriteListEmpty.addGame(new Sudoku(1));
        assertTrue(favouriteListEmpty.containGame("sudoku"));
        favouriteListEmpty.removeFav("sudoku");
        assertFalse(favouriteListEmpty.containGame("sudoku"));
        favouriteListEmpty.addGame(new Sudoku(1));
        favouriteListEmpty.removeFav(0);
        assertFalse(favouriteListEmpty.containGame("sudoku"));
    }

    @Test
    public void saveFavListTest() {
        FavouriteList reference = new FavouriteList();

        favouriteList.saveFavList();
        favouriteList = new FavouriteList();

        assertTrue(favouriteList.equals(reference));
    }
}
