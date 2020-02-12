//package ui.sneakystuff.sudoku;
//
//import ui.RegularMinesweeper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//public class RegularMinesweeperTest {
//    RegularMinesweeper game;
//    InputStream sysInBackup = System.in;
//    ByteArrayInputStream in;
//    ByteArrayInputStream stubIn = new ByteArrayInputStream("zzz".getBytes());
//
//    @BeforeEach
//    public void setup() {
//        game = new RegularMinesweeper();
//        in = new ByteArrayInputStream("a".getBytes());
//    }
//
//    @Test
//    public void loseGame() {
//
//        game.runGame();
//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 30; j++) {
//                if ()
//            }
//        }
//    }
//
//    @Test
//    public void winGame() {
//
//    }
//
//    public void inOpen() {
//        in = stubIn;
//        System.setIn(sysInBackup);
//        in = new ByteArrayInputStream("open".getBytes());
//        System.setIn(sysInBackup);
//    }
//
//    public void inFlag() {
//        in = stubIn;
//        System.setIn(sysInBackup);
//        in = new ByteArrayInputStream("flag".getBytes());
//        System.setIn(sysInBackup);
//    }
//
//    public void inCoordinateX(int x) {
//        in = stubIn;
//        System.setIn(sysInBackup);
//
//        int inputNum = (int)'a';
//        inputNum += x;
//        char inputChar = (char)inputNum;
//        String input = String.valueOf(inputChar);
//
//        in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(sysInBackup);
//    }
//
//    public void inCoordinateY(int y) {
//        in = new ByteArrayInputStream("32".getBytes());
//        System.setIn(sysInBackup);
//
//        String input = Integer.toString(y);
//
//        in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(sysInBackup);
//    }
//}
