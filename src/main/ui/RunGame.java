package ui;

import model.minesweeper.RegularMinesweeper;

public class RunGame {
    RegularMinesweeper game;

    public RunGame() {
        game = new RegularMinesweeper();
        game.runGame();
    }
}
