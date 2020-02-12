package ui.sneakystuff.sudoku;

import java.util.ArrayList;
import java.util.Random;

public class SeedBank {
    Random rand = new Random();
    Seed seed1 = new Seed("523986147164735289897241563439512678782694351651873924346128795278359416915467832",
            "..3.861....47...8....2...6.4.........82....5......392.346..8..5.7...9.....5.6...2");
    ArrayList<Seed> bank;

    public SeedBank() {
        bank = new ArrayList<>();
        bank.add(seed1);
    }

    public Seed getSeed() {
        ///TODO add shuffle seed method
        Seed seed = bank.get(rand.nextInt(bank.size()));
        String seedActualSeed = seed.actualSeed;
        String seedVisibleSeed = seed.visibleSeed;

        return new Seed(seedActualSeed, seedVisibleSeed);
    }

}
