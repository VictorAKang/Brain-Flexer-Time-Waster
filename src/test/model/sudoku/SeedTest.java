package model.sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeedTest {
    Seed s;
    int seedSize = 81;
    int numDigits;

    @BeforeEach
    public void setup() {
        s = new Seed();

        String stubString = s.visibleSeed;
        numDigits = 0;

        for (int i = 0; i < seedSize; i++) {
            if (Character.isDigit(stubString.charAt(i))) {
                numDigits++;
            }
        }
    }

    @Test
    public void testRandomizer() {
        String stubString;
        int numDigitsPostRandomizer;

        for (int i = 0; i < 100; i++) {
            s.randomizeSeed(s);

            assertEquals(seedSize, s.actualSeed.length());
            assertEquals(seedSize, s.visibleSeed.length());

            numDigitsPostRandomizer = 0;
            stubString = s.visibleSeed;

            for (int j = 0; j < seedSize; j++) {
                if (Character.isDigit(stubString.charAt(j))) {
                    numDigitsPostRandomizer++;
                }
            }

            assertEquals(numDigits, numDigitsPostRandomizer);
        }
    }

    @Test
    public void getCellVisibleNumberTest() {
        char c = s.getCellVisibleNumber(0,0);
        assertEquals(s.visibleSeed.charAt(0),c);
    }
}
