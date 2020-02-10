package testingUtils;

import java.util.ArrayList;

public class DistributionArray {
    private final static double subintervalsRange = 0.05;
    ArrayList<Integer> distribution;

    public DistributionArray() {
        distribution = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            distribution.add(0);
        }
    }

    public void addEntry(double entry) {
        double startingLowerBound = 0.95;
        double intEntry = entry;
        for (int i = 0; i < 20; i++) {
            //System.out.println(startingLowerBound - i * 100 * subintervalsRange);
            if (intEntry - (startingLowerBound - i * subintervalsRange) >= 0.0) {
                distribution.set(19 - i, distribution.get(19 - i) + 1);
                //System.out.println(startingLowerBound - i * subintervalsRange);
                return;
            }
        }
    }

    public void printDistribution() {
        int startingLowerBound = 0;
        for (int i = 0; i < 20; i++) {
            System.out.print((startingLowerBound + i * 5) + ": ");
            for (int j = 0; j < distribution.get(i) / 20; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
