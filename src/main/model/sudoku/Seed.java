package model.sudoku;

import com.sun.javafx.collections.ImmutableObservableList;

import java.util.ArrayList;
import java.util.Random;

public class Seed {
    String actualSeed;
    String visibleSeed;
    Random rand = new Random();

    public Seed(String actualSeed, String visibleSeed) {
        this.actualSeed = actualSeed;
        this.visibleSeed = visibleSeed;

//        seeds = new ArrayList<>();
//        setupSeeds();
//        seed = randomizeSeed(seeds.get(rand.nextInt(seeds.size())));
//        System.out.println(seed);

    }

    public String getActualSeed() {
        return actualSeed;
    }

    public String getVisibleSeed() {
        return visibleSeed;
    }

    private Seed randomizeSeed(Seed s) {
        //TODO make this work
        return s;
    }

//    private void setupSeeds() {
//        String seed1 = "..3.861....47...8....2...6.4.........82....5......392.346..8..5.7...9.....5.6...2"
//                + "523986147164735289897241563439512678782694351651873924346128795278359416915467832";
//        seeds.add(seed1);
//    }
}
