package ui;

import model.minesweeper.RegularMinesweeper;
import model.sudoku.RegularSudoku;

import java.util.Scanner;

public class RunGame {
    Scanner input = new Scanner(System.in);
    RegularMinesweeper gameMS;
    RegularSudoku gameS;
    boolean playAgain;
    int gameMode;


    public RunGame() {
        playAgain = true;

        while (playAgain) {
            System.out.print("Which game do you wanna play? (1 - minesweeper, 2 - sudoku) ");
            gameMode = input.nextInt();

            while (gameMode != 1 && gameMode != 2) {
                System.out.println("invalid input...");
                System.out.print("Which game do you wanna play? (1 - minesweeper, 2 - sudoku) ");
                gameMode = input.nextInt();
            }

            if (gameMode == 1) {
                gameMS = new RegularMinesweeper();
                gameMS.runGame();
            } else {
                gameS = new RegularSudoku();
                gameS.runGame();
            }

        }
    }
}
