package utils;

import java.util.Random;

public class RandomNumberGenerator {
    Random r;

    public RandomNumberGenerator() {
        r = new Random();
    }

    public double generateNumber() {
        return r.nextDouble();
    }
}
