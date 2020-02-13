package model.sudoku;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Seed {
    String actualSeed;
    String visibleSeed;
    Random rand = new Random();

    public Seed(String actualSeed, String visibleSeed) {
        this.actualSeed = actualSeed;
        this.visibleSeed = visibleSeed;
    }

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

    private void randomizeSeed(Seed s) {
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

    public ArrayList<Character> rotate90(ArrayList<Character> seedChar) {
        Character helperChar;
        Character helperChar2;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                helperChar = seedChar.get((9 * j) + max - i);
                seedChar.set((9 * j) + max - i, seedChar.get((9 * i) + j));
                helperChar2 = seedChar.get((9 * (max - i)) + max - j);
                seedChar.set((9 * (max - i)) + max - j, helperChar);
                helperChar = seedChar.get((9 * (max - j)) + i);
                seedChar.set((9 * (max - j)) + i, helperChar2);
                seedChar.set((9 * i) + j, helperChar);
            }
        }

        return seedChar;
    }

    public ArrayList<Character> rotate180(ArrayList<Character> seedChar) {
        Character helperChar;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                helperChar = seedChar.get((9 * (max - i)) + max - j);
                seedChar.set((9 * (max - i)) + max - j,seedChar.get((9 * i) + j));
                seedChar.set((9 * i) + j, helperChar);
            }
        }

        return seedChar;
    }

    public ArrayList<Character> rotate270(ArrayList<Character> seedChar) {
        Character helperChar;
        Character helperChar2;
        int max = 8;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                helperChar = seedChar.get((9 * (max - j)) + i);
                seedChar.set((9 * (max - j)) + i, seedChar.get((9 * i) + j));
                helperChar2 = seedChar.get((9 * (max - i)) + max - j);
                seedChar.set((9 * (max - i)) + max - j, helperChar);
                helperChar = seedChar.get((9 * j) + max - i);
                seedChar.set((9 * j) + max - i, helperChar2);
                seedChar.set((9 * i) + j, helperChar);
            }
        }

        return seedChar;
    }

    public ArrayList<Character> randomizeFlipV(boolean flipV, ArrayList<Character> seedChar) {
        if (flipV == false) {
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

    public String randomizeFlipH(boolean flipH, ArrayList<Character> seedChar) {
        if (flipH == false) {
            String seedString = seedChar.stream().map(String::valueOf).collect(Collectors.joining());
            return seedString;
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

//    private void setupSeeds() {
//        String seed1 = "..3.861....47...8....2...6.4.........82....5......392.346..8..5.7...9.....5.6...2"
//                + "523986147164735289897241563439512678782694351651873924346128795278359416915467832";
//        seeds.add(seed1);
//    }
}
