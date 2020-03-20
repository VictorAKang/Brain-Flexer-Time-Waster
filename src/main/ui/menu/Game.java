package ui.menu;

// represents a Game
public interface Game {

    //MODIFIES: this
    //EFFECTS: runs the game
    void runGame(int i);

    //EFFECTS: returns the description of game
    String getDescription();
}
