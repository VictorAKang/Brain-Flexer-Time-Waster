package ui.javafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class MainMenuUI extends Application {
    protected static Stage primaryStage;

    protected static Scene mainMenu;
    protected static MinesweeperUI minesweeper = new MinesweeperUI();
    protected static SudokuUI sudoku = new SudokuUI();
    protected static FavouriteListUI favourite = new FavouriteListUI();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Main");
        primaryStage.setResizable(false);

        Label mainTittle = setTitleLabel();
        Button minesweeperButton = setMinesweeperButton();
        Button sudokuButton = setSudokuButton();
        Button favouriteButton = setFavListButton();
        Button quitButton = setQuitButton();

        VBox layout = new VBox(30);
        layout.getChildren().addAll(mainTittle,minesweeperButton,sudokuButton,favouriteButton,quitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: teal;");

        mainMenu = new Scene(layout,500,400);
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    protected static void goBackMainMenu() {
        primaryStage.setScene(mainMenu);
        centerScreen();
    }

    protected static void centerScreen() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    private Label setTitleLabel() {
        Label mainTittle = new Label();
        mainTittle.setText("Welcome to the puzzle hub");
        mainTittle.setFont(Font.font("Arial", FontPosture.ITALIC,30));

        return mainTittle;
    }

    private Button setMinesweeperButton() {
        Button minesweeperButton = new Button();
        minesweeperButton.setText("Play minesweeper");
        minesweeperButton.setMaxWidth(130);
        minesweeperButton.setOnAction(e -> {
            primaryStage.setScene(minesweeper.display());
            centerScreen();
        });

        return minesweeperButton;
    }

    private Button setSudokuButton() {
        Button sudokuButton = new Button();
        sudokuButton.setText("Play sudoku");
        sudokuButton.setMaxWidth(130);
        sudokuButton.setOnAction(e -> {
            primaryStage.setScene(sudoku.display());
            centerScreen();
        });

        return sudokuButton;
    }

    private Button setFavListButton() {
        Button favouriteButton = new Button();
        favouriteButton.setText("Open favourite list");
        favouriteButton.setMaxWidth(130);
        favouriteButton.setOnAction(e ->
                primaryStage.setScene(favourite.display(primaryStage)));

        return favouriteButton;
    }

    private Button setQuitButton() {
        Button quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setOnAction(e -> primaryStage.close());

        return quitButton;
    }
}
