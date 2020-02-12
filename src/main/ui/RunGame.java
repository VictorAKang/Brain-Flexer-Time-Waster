package ui;

import java.util.Scanner;

public class RunGame {
    Scanner input = new Scanner(System.in);
    Minesweeper gameMS;
    Sudoku gameS;
    boolean playAgain;
    int gameMode;


    public RunGame() {
        playAgain = true;
        gameMS = new Minesweeper();
        gameS = new Sudoku();
        gameMS.runGame();
        gameS.runGame();

//        while (playAgain) {
//            System.out.print("Which game do you wanna play? (1 - minesweeper, 2 - sudoku) ");
//            gameMode = input.nextInt();
//
//            while (gameMode != 1 && gameMode != 2) {
//                System.out.println("invalid input...");
//                System.out.print("Which game do you wanna play? (1 - minesweeper, 2 - sudoku) ");
//                gameMode = input.nextInt();
//            }
//
//            if (gameMode == 1) {
//                gameMS = new RegularMinesweeper();
//                gameMS.runGame();
//            } else {
//                gameS = new RegularSudoku();
//                gameS.runGame();
//            }
//
//        }
    }
}
