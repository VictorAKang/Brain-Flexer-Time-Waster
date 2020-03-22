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
import persistence.FavouriteListReader;
import ui.menu.FavouriteList;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FavouriteListUI {
    private static final int GAME_LIST_HEIGHT = 50;

    private static FileInputStream starInput;
    private static FileInputStream emptyInput;

    private static Image starImage;
    private static Image emptyImage;

    private static ImageView minesweeperStarStatus = new ImageView();
    private static ImageView sudokuStarStatus = new ImageView();

    protected static FavouriteList favList;
    protected static MinesweeperUI minesweeper = new MinesweeperUI();
    protected static SudokuUI sudoku = new SudokuUI();

    private static Label title;
    private static BorderPane layout;

    private static Scene favouriteList;

    public FavouriteListUI() {
        setupImages();
    }

    protected Scene display(Stage primaryStage) {
        try {
            favList = new FavouriteListReader().read(new File(FavouriteList.FAV_LIST));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            if (favList.hasGame("sudoku")) {
                favList.removeFav("sudoku");
            } else {
                favList.simpleAdd(new Sudoku(1));
            }
            updateStarStatus("sudoku");
        });

        sudokuStarStatus.setImage(getFavStatusImage("sudoku"));

        horBox.getChildren().addAll(blankSpace,sudokuLabel,sudokuPlay,sudokuStatus,sudokuStarStatus);
        return horBox;
    }

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
            if (favList.hasGame("minesweeper")) {
                favList.removeFav("minesweeper");
            } else {
                favList.simpleAdd(new Minesweeper(1));
            }
            updateStarStatus("minesweeper");
        });

        minesweeperStarStatus.setImage(getFavStatusImage("minesweeper"));

        horBox.getChildren()
                .addAll(blankSpace,minesweeperLabel,minesweeperPlay,minesweeperStatus,minesweeperStarStatus);
        return horBox;
    }

    private Image getFavStatusImage(String description) {
//        ImageView referenceView;
        if (favList.hasGame(description)) {
//            referenceView = new ImageView(starImage);
//            referenceView.setFitHeight(GAME_LIST_HEIGHT);
//            referenceView.setFitWidth(GAME_LIST_HEIGHT);
            return starImage;
        }

        return emptyImage;
    }

    private void updateStarStatus(String description) {
        if (description.equals("minesweeper")) {
            minesweeperStarStatus.setImage(getFavStatusImage("minesweeper"));
        } else {
            sudokuStarStatus.setImage(getFavStatusImage("sudoku"));
        }
    }

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

    private void setupClose() {
        Button closeButton = new Button();

        closeButton.setText("Close");
        closeButton.setOnAction(e -> {
            favList.saveFavList(FavouriteList.FAV_LIST);
            MainMenuUI.goBackMainMenu();
        });

        VBox verBox = new VBox();
        verBox.setMinHeight(50);
        verBox.setAlignment(Pos.CENTER);
        verBox.getChildren().add(closeButton);

        layout.setBottom(verBox);
    }

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