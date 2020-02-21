package model.sudoku;

import java.util.ArrayList;
import java.util.Random;

// represents the bank of all seeds
public class SeedBank {
    static ArrayList<Seed> bank;

    public SeedBank() {
        bank = new ArrayList<>();
        setup();
    }

    //EFFECTS: return a random seed from the bank
    public Seed getSeed() {
        Random rand = new Random();

        return bank.get(rand.nextInt(bank.size()));
    }


    //MODIFIES: this
    //EFFECTS: adds the determined seeds to the bank
    public void setup() {
        bank.add(new Seed("523986147164735289897241563439512678782694351651873924346128795278359416915467832",
                "..3.861....47...8....2...6.4.........82....5......392.346..8..5.7...9.....5.6...2"));
        bank.add(new Seed("957613284483257196612849537178364952524971368369528741845792613291436875736185429",
                "......2...8...7.9.6.2...5...7..6.......9.1.......2..4...5...6.3.9.4...7...6......"));
        bank.add(new Seed("369782541418596372527431896986145723152973468743628159691357284274819635835264917",
                "..9.8.5...1......25..43.8.69.6..5..3.5..7.....4...81.9..13....4..4..96..83..6.1.."));
        bank.add(new Seed("497138652263954871851726394319467528726815943584293716135672489978341265642589137",
                "..71...5..6.....7....7..3.43..4..52..2.8.5.4..84..3..61.5..2....7.....6..4...91.."));
        bank.add(new Seed("136492587594768132728315964861574293275931648349286715657143829482659371913827456",
                "..6...5.7.9..68...7.8..5..4..15.429..7..3....3.9...71..5..4.8.9.826.........274.6"));
    }
}
