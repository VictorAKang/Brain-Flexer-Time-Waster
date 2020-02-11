package model.sudoku;

import com.sun.javafx.collections.ImmutableObservableList;

import java.util.ArrayList;
import java.util.Random;

public class Seed {
    Random rand = new Random();
    String seed1 = "..3.861....47...8....2...6.4.........82....5......392.346..8..5.7...9.....5.6...2"
            + "523986147164735289897241563439512678782694351651873924346128795278359416915467832";
    ArrayList<String> seeds;
    String seed;

    public Seed() {
        seeds = new ArrayList<>();
        setupSeeds();
        seed = randomizeSeed(seeds.get(rand.nextInt(seeds.size())));
        System.out.println(seed);
    }

    private String randomizeSeed(String s) {
        //TODO make this work
        return s;
    }

    private void setupSeeds() {
        seeds.add(seed1);
    }
}
