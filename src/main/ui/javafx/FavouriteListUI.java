package ui.javafx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.FavouriteList;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


// represents the favourite games list ui
public class FavouriteListUI {
    private static final int GAME_LIST_HEIGHT = 50;

    private static FileInputStream starInput;
    private static FileInputStream emptyInput;
    private static Image starImage;
    private static Image emptyImage;
    private static ImageView minesweeperStarStatus = new ImageView();
    private static ImageView sudokuStarStatus = new ImageView();
    protected static FavouriteList favList = new FavouriteList();
    protected static MinesweeperUI minesweeper = new MinesweeperUI();
    protected static SudokuUI sudoku = new SudokuUI();
    private static Label title;
    private static BorderPane layout;
    private static Scene favouriteList;

    public FavouriteListUI() {
        setupImages();
    }

    //MODIFIES: this
    //EFFECTS: sets up the scene and returns it
    protected Scene display(Stage primaryStage) {
        layout = new BorderPane();
        layout.setMinSize(500,400);
        setupTitle();
        setupClose();
        VBox gamesList = new VBox(50);
        Node minesweeper = makeMinesweeperRow(primaryStage);
        Node sudoku = makeSudokuRow(primaryStage);
        gamesList.getChildren().addAll(minesweeper,sudoku);
        layout.setCenter(gamesList);
        favouriteList = new Scene(layout);
        return favouriteList;
    }

    //MODIFIES: this
    //EFFECTS: makes the row of for the sudoku game
    private Pane makeSudokuRow(Stage primaryStage) {
        HBox horBox = new HBox(20);

        ImageView blankSpace = new ImageView();
        blankSpace.setFitWidth(50);
        Label sudokuLabel = new Label("Sudoku");
        sudokuLabel.setMinWidth(100);
        sudokuLabel.setMinHeight(GAME_LIST_HEIGHT);
        Button sudokuPlay = new Button("play");
        sudokuPlay.setMinHeight(GAME_LIST_HEIGHT);
        sudokuPlay.setOnAction(e -> primaryStage.setScene(sudoku.display()));
        Button sudokuStatus = new Button("add/remove");
        sudokuStatus.setMinHeight(GAME_LIST_HEIGHT);
        sudokuStatus.setOnAction(e -> {
            if (favList.containGame("sudoku")) {
                favList.removeFav("sudoku");
            } else {
                favList.addGame(new Sudoku(1));
            }
            updateStarStatus("sudoku");
        });
        sudokuStarStatus.setImage(getFavStatusImage("sudoku"));
        horBox.getChildren().addAll(blankSpace,sudokuLabel,sudokuPlay,sudokuStatus,sudokuStarStatus);
        return horBox;
    }

    //MODIFIES: this
    //EFFECTS: makes the row for the minesweeper game
    private Pane makeMinesweeperRow(Stage primaryStage) {
        HBox horBox = new HBox(20);

        ImageView blankSpace = new ImageView();
        blankSpace.setFitWidth(50);
        Label minesweeperLabel = new Label("Minesweeper");
        minesweeperLabel.setMinWidth(100);
        minesweeperLabel.setMinHeight(GAME_LIST_HEIGHT);
        Button minesweeperPlay = new Button("play");
        minesweeperPlay.setMinHeight(GAME_LIST_HEIGHT);
        minesweeperPlay.setOnAction(e -> primaryStage.setScene(minesweeper.display()));
        Button minesweeperStatus = new Button("add/remove");
        minesweeperStatus.setMinHeight(GAME_LIST_HEIGHT);
        minesweeperStatus.setOnAction(e -> {
            if (favList.containGame("minesweeper")) {
                favList.removeFav("minesweeper");
            } else {
                favList.addGame(new Minesweeper(1));
            }
            updateStarStatus("minesweeper");
        });
        minesweeperStarStatus.setImage(getFavStatusImage("minesweeper"));
        horBox.getChildren()
                .addAll(blankSpace,minesweeperLabel,minesweeperPlay,minesweeperStatus,minesweeperStarStatus);
        return horBox;
    }

    //EFFECTS: returns the image that corresponds to the favourite status of given game
    private Image getFavStatusImage(String description) {
//        //        ImageView referenceView;
////        if (favList.containGame(description)) {
//        if (favList.containGame(description)) {
//            return starImage;
//        }
        if (favList.containGame(description)) {
            return starImage;
        }

        return emptyImage;
    }

    //MODIFIES: this
    //EFFECTS: updates the star status image of given game
    private void updateStarStatus(String description) {
        if (description.equals("minesweeper")) {
            minesweeperStarStatus.setImage(getFavStatusImage("minesweeper"));
        } else {
            sudokuStarStatus.setImage(getFavStatusImage("sudoku"));
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up the title and the background color
    private void setupTitle() {
        layout.setStyle("-fx-background-color: #0CD2BA;");

        title = new Label();
        title.setText("Favourite Games List");
        title.setFont(Font.font("Heveltica", FontPosture.ITALIC,30));
        VBox verBox = new VBox();
        verBox.setMinHeight(100);
        verBox.setAlignment(Pos.CENTER);
        verBox.getChildren().addAll(title);
        layout.setTop(verBox);
    }

    //MODIFIES: this
    //EFFECTS: sets up the close button
    private void setupClose() {
        Button closeButton = new Button();

        closeButton.setText("Close");
        closeButton.setOnAction(e -> {
            favList.saveFavList();
            MainMenuUI.goBackMainMenu();
        });
        VBox verBox = new VBox();
        verBox.setMinHeight(50);
        verBox.setAlignment(Pos.CENTER);
        verBox.getChildren().add(closeButton);
        layout.setBottom(verBox);
    }

    //MODIFIES: this
    //EFFECTS: sets up all the images that will be used in the ui 
    private void setupImages() {
        try {
            starInput = new FileInputStream("./data/favouriteListImages/redStar.png");
            emptyInput = new FileInputStream("./data/favouriteListImages/emptyImage.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        starImage = new Image(starInput);
        emptyImage = new Image(emptyInput);
        minesweeperStarStatus.setFitWidth(GAME_LIST_HEIGHT);
        minesweeperStarStatus.setFitHeight(GAME_LIST_HEIGHT);
        sudokuStarStatus.setFitHeight(GAME_LIST_HEIGHT);
        sudokuStarStatus.setFitWidth(GAME_LIST_HEIGHT);
//
//        minesweeperStarStatus = new ImageView(starImage); 
//        minesweeperStarStatus.setFitWidth(GAME_LIST_HEIGHT);
//        minesweeperStarStatus.setFitHeight(GAME_LIST_HEIGHT);
//        sudokuStarStatus = new ImageView(starImage);
//        sudokuStarStatus.setFitHeight(GAME_LIST_HEIGHT);
//        sudokuStarStatus.setFitWidth(GAME_LIST_HEIGHT);
//
//        minesweeperNotStarStatus = new ImageView();
//        sudokuNotStarStatus = new ImageView();
    }
}