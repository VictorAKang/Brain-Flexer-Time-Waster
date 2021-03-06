# Brain-Flexer or Time-Waster

A Hub for all the minigames that I coded for fun. The hub contains for now Minesweeper and Sudoku games, with minesweeper generating itself randomly each playthrough and sudoku board taken from a preset database. The games can be run on an application or on the terminal console.

## How to run the code

**Running the console based version**

Follow the path src/main/ui and run the Main class to start the application. 

**Running the application based version**

Follow the path src/main/ui/javafx and run the MainMenuUI class to start the application. 

Main Menu

From the Main Menu you can choose if you want to play **Minesweeper** or **Sudoku**, look into your **Favourite Games 
List** or simply *Quit* the app. From there, you can follow the instructions in the corresponding section.

Minesweeper

Play the game as if it were normal **Minesweeper**! For you to flag the bombs, click on *flag* to activate flag mode and from 
now on your clicks will flag cells. Cells that are flagged will not be opened by normal clicks anymore. For you to 
deactivate flag mode, just click the *flag* button again and all of your clicks will once again open cells. In case you 
click a mine, the game will be deactivated and all cells that were bombs and were not flagged will be revealed. For you 
to restart the game by generating a new board, click the *reset* button. For you to go back to the **Main Menu**, click on
the *close* button.

Sudoku

Might not look pretty but take it as an extra challenge, same rules of **sudoku** but with an ugly board. Click on the cells 
that you would like to change and edit the number to the one you would like. If you think that you are done, click the 
*check* button and the game will tell you if you are done or if you messed something up. If you want to try a different 
board, click on the *reset* button. If you want to close the game and go back to the **Main Menu**, click on the *close* 
button.

Favourite Games List

A very simple application that will mark your favourite games with a star. Click on the *add/remove* button to add or 
remove the star. Click on the *play* button if you would like to play the selected game. Click on the *close* button if you
would like to return to the **Main Menu**. The application will save your favourite games for the next time you open not
only the **Favourite Games List** application but also the **Puzzle Hub** application.

