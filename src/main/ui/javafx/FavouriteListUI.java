package ui.javafx;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import persistence.FavouriteListReader;
import ui.menu.FavouriteList;
import ui.menu.Game;
import ui.menu.Minesweeper;
import ui.menu.Sudoku;

import java.io.File;
import java.io.IOException;


public class FavouriteListUI {
    protected static FavouriteList favList;
    protected static MinesweeperUI minesweeper = new MinesweeperUI();
    protected static SudokuUI sudoku = new SudokuUI();

    protected static void display(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #0CD2BA;");
        try {
            favList = new FavouriteListReader().read(new File(FavouriteList.FAV_LIST));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Label title = new Label();
        title.setText("Favourite Games List");
        title.setFont(Font.font("Heveltica", FontPosture.ITALIC,30));

        VBox verBox = new VBox(50);
        verBox.setAlignment(Pos.TOP_CENTER);
        verBox.getChildren().addAll(title);
        borderPane.setTop(verBox);

        Label minesweeperLabel = new Label("Minesweeper");
        minesweeperLabel.setMinWidth(100);
        Button minesweeperPlay = new Button("play");
        minesweeperPlay.setOnAction(e -> primaryStage.setScene(minesweeper.display()));
        Button minesweeperStatus = new Button("add to favourites");
        minesweeperStatus.setOnAction(e -> {
            favList.simpleAdd(new Minesweeper(1));
        });

        Label sudokuLabel = new Label("Sudoku");
        sudokuLabel.setMinWidth(100);
        Button sudokuPlay = new Button("play");
        sudokuPlay.setOnAction(e -> primaryStage.setScene(sudoku.display()));
        Button sudokuStatus = new Button("add to favourites");
        sudokuStatus.setOnAction(e -> favList.simpleAdd(new Sudoku(1)));

        for (Game g: favList.favList) {
            if (g.getDescription().equals("minesweeper")) {
                minesweeperStatus.setText("remove from favourites");
                minesweeperStatus.setOnAction(e -> favList.removeFav("minesweeper"));
            } else if (g.getDescription().equals("sudoku")) {
                sudokuStatus.setText("remove from favourites");
                sudokuStatus.setOnAction(e -> favList.removeFav("sudoku"));
            }
        }

        VBox list = new VBox();
        HBox favouriteSettings = new HBox(20);
        favouriteSettings.getChildren().addAll(minesweeperLabel,minesweeperPlay,minesweeperStatus);
        list.getChildren().add(favouriteSettings);
        favouriteSettings = new HBox(20);
        favouriteSettings.getChildren().addAll(sudokuLabel,sudokuPlay,sudokuStatus);
        list.getChildren().add(favouriteSettings);
        list.setAlignment(Pos.CENTER);

        borderPane.setCenter(list);

        MainMenuUI.favourite = new Scene(borderPane,500,400);
        primaryStage.setScene(MainMenuUI.favourite);
    }

    private class AddStatus implements EventHandler<Event> {

        @Override
        public void handle(Event event) {

        }
    }
}


