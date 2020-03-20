package model.sudoku;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

// represents a sudoku board
public class Seed {
    String actualSeed;
    String visibleSeed;
    Random rand = new Random();

    //MODIFIES: this
    //EFFECTS: assigns a value to the seed
    public Seed(String actualSeed, String visibleSeed) {
        this.actualSeed = actualSeed;
        this.visibleSeed = visibleSeed;
    }

    //MODIFIES: this
    //EFFECTS: gets a seed from the bank and randomizes it
    public Seed() {
        SeedBank bank = new SeedBank();
        randomizeSeed(bank.getSeed());
    }

    public String getActualSeed() {
        return actualSeed;
    }

    public String getVisibleSeed() {
        return visibleSeed;
    }

    //MODIFIES: this
    //EFFECTS: randomize the seed
    public void randomizeSeed(Seed s) {
        ArrayList<Integer> num = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            num.add(i);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < num.size(); j++) {
                int swapIndex = rand.nextInt(num.size());
                int helperInt = num.get(swapIndex);
                num.set(swapIndex, num.get(j));
                num.set(j,helperInt);
            }
        }

        int rotation = rand.nextInt(4);
        boolean flipV = rand.nextBoolean();
        boolean flipH = rand.nextBoolean();


        String actualS = randomizeFlipH(flipH,randomizeFlipV(
                flipV,randomizeRotate(rotation,randomizeNumber(num,s.getActualSeed()))));
        String visibleS = randomizeFlipH(flipH,randomizeFlipV(
                flipV,randomizeRotate(rotation,randomizeNumber(num,s.getVisibleSeed()))));

        this.visibleSeed = visibleS;
        this.actualSeed = actualS;
    }

    //EFFECTS: returns the seed with all its numbers randomized
    public ArrayList<Character> randomizeNumber(ArrayList<Integer> num, String s) {
        ArrayList<Character> seedString = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            Character helperChar = s.charAt(i);

            if (Character.isDigit(helperChar)) {
                seedString.add(Integer.toString(num.get((int)helperChar - (int)'1')).charAt(0));
            } else {
                seedString.add('.');
            }
        }

        return seedString;
    }

    //EFFECTS: returns a rotated seed
    public ArrayList<Character> randomizeRotate(int rotation, ArrayList<Character> seedChar) {

        if (rotation == 1) {
            return rotate90(seedChar);
        } else if (rotation == 2) {
            return rotate180(seedChar);
        } else if (rotation == 3) {
            return rotate270(seedChar);
        } else {
            return seedChar;
        }
    }

    //EFFECTS: returns a board rotated by 90 degrees
    public ArrayList<Character> rotate90(ArrayList<Character> seedChar) {
        Character helperChar;
        Character helperChar2;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int pos0 = (9 * i) + j;
                int pos1 = (9 * j) + max - i;
                int pos2 = (9 * (max - i)) + max - j;
                int pos3 = (9 * (max - j)) + i;

                helperChar = seedChar.get(pos1);
                seedChar.set(pos1, seedChar.get(pos0));
                helperChar2 = seedChar.get(pos2);
                seedChar.set(pos2, helperChar);
                helperChar = seedChar.get(pos3);
                seedChar.set(pos3, helperChar2);
                seedChar.set(pos0, helperChar);
            }
        }

        return seedChar;
    }

    //EFFECTS: returns a board rotated by 180 degrees
    public ArrayList<Character> rotate180(ArrayList<Character> seedChar) {
        Character helperChar;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int pos0 = (9 * i) + j;
                int pos1 = (9 * (max - i)) + max - j;
                helperChar = seedChar.get(pos1);
                seedChar.set(pos1,seedChar.get(pos0));
                seedChar.set(pos0, helperChar);
            }
        }

        return seedChar;
    }

    //EFFECTS: returns the board rotated by 270 degrees
    public ArrayList<Character> rotate270(ArrayList<Character> seedChar) {
        Character helperChar;
        Character helperChar2;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int pos0 = (9 * i) + j;
                int pos1 = (9 * (max - j)) + i;
                int pos2 = (9 * (max - i)) + max - j;
                int pos3 = (9 * j) + max - i;

                helperChar = seedChar.get(pos1);
                seedChar.set(pos1, seedChar.get(pos0));
                helperChar2 = seedChar.get(pos2);
                seedChar.set(pos2, helperChar);
                helperChar = seedChar.get(pos3);
                seedChar.set(pos3, helperChar2);
                seedChar.set(pos0, helperChar);
            }
        }

        return seedChar;
    }

    //EFFECTS: returns a board flipped around the horizontal axis
    public ArrayList<Character> randomizeFlipV(boolean flipV, ArrayList<Character> seedChar) {
        if (!flipV) {
            return seedChar;
        }

        Character helperChar;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                helperChar = seedChar.get((9 * (max - i)) + j);
                seedChar.set((9 * (max - i)) + j,seedChar.get((9 * i) + j));
                seedChar.set((9 * i) + j, helperChar);
            }
        }

        return seedChar;
    }

    //EFFECTS: returns the board in form of seed rotated flipped around the vertical axis
    public String randomizeFlipH(boolean flipH, ArrayList<Character> seedChar) {
        if (!flipH) {
            return seedChar.stream().map(String::valueOf).collect(Collectors.joining());
        }

        String seedString = seedChar.stream().map(String::valueOf).collect(Collectors.joining());
        Character helperChar;
        int max = 8;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                helperChar = seedChar.get((9 * i) + max - j);
                seedChar.set((9 * i) + max - j,seedChar.get((9 * i) + j));
                seedChar.set((9 * i) + j, helperChar);
            }
        }

        return seedString;
    }

    protected char getCellVisibleNumber(int coordinateI, int coordinateJ) {
        return visibleSeed.charAt(coordinateI * 9 + coordinateJ);
    }
}
