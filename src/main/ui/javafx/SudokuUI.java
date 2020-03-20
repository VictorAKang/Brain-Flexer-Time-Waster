package ui.javafx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.sudoku.Grid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SudokuUI {
    private static final int CELL_SIDE = 32;

    private static FileInputStream sudokuBoardInput;

    private static Image sudokuBoardImage;

//    private static FileInputStream plain1Input;
//    private static FileInputStream plain2Input;
//    private static FileInputStream plain3Input;
//    private static FileInputStream plain4Input;
//    private static FileInputStream plain5Input;
//    private static FileInputStream plain6Input;
//    private static FileInputStream plain7Input;
//    private static FileInputStream plain8Input;
//    private static FileInputStream plain9Input;
//
//    private static FileInputStream full1Input;
//    private static FileInputStream full2Input;
//    private static FileInputStream full3Input;
//    private static FileInputStream full4Input;
//    private static FileInputStream full5Input;
//    private static FileInputStream full6Input;
//    private static FileInputStream full7Input;
//    private static FileInputStream full8Input;
//    private static FileInputStream full9Input;
//
//    private static Image plain1Image;
//    private static Image plain2Image;
//    private static Image plain3Image;
//    private static Image plain4Image;
//    private static Image plain5Image;
//    private static Image plain6Image;
//    private static Image plain7Image;
//    private static Image plain8Image;
//    private static Image plain9Image;
//
//    private static Image full1Image;
//    private static Image full2Image;
//    private static Image full3Image;
//    private static Image full4Image;
//    private static Image full5Image;
//    private static Image full6Image;
//    private static Image full7Image;
//    private static Image full8Image;
//    private static Image full9Image;

    private static BorderPane layout;
    private static Scene sudokuScene;

//    private static Node[] numberButtons = new Button[9];
    private static TextField[][] numberReferenceField = new TextField[9][9];
    private static Grid sudokuGrid = new Grid();

    public SudokuUI() {
        setupBoardImage();
    }

    protected Scene display() {

        sudokuGrid.resetGame();

        layout = new BorderPane();
        setMenuField();
        setNumberField();

        sudokuScene = new Scene(layout);

        return sudokuScene;
    }

    private void setMenuField() {
        HBox menuButtons = new HBox(10);

        //Node numberButtons = makeNumberButtons();

        Label isDone = new Label();

        Button checkButton = new Button("check");
        checkButton.setOnAction(e -> {
            updateGrid();
            if (sudokuGrid.gameComplete()) {
                isDone.setText("Done!");
            } else {
                isDone.setText("Not done..");
            }
        });

        Button resetButton = new Button();
        resetButton.setText("reset");
        resetButton.setOnAction(e -> {
            sudokuGrid.resetGame();
            //resetNumberButtons();
            setNumberField();
        });

        Button closeGameButton = new Button();
        closeGameButton.setText("Close");
        closeGameButton.setOnAction(e -> MainMenuUI.goBackMainMenu());

        menuButtons.setAlignment(Pos.CENTER);

        menuButtons.getChildren().addAll(isDone,checkButton,resetButton,closeGameButton);
        layout.setTop(menuButtons);
    }

    private void setNumberField() {
        StackPane totalPane = new StackPane();

        ImageView background = new ImageView(sudokuBoardImage);
        background.setFitWidth(CELL_SIDE * 9);
        background.setFitHeight(CELL_SIDE * 9);

        FlowPane numberField = new FlowPane();
        numberField.setPrefWrapLength(CELL_SIDE * 9);
        numberField.setStyle("-fx-background-color: #0CD2A2");

        Node referenceNode;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                referenceNode = getCellNode(sudokuGrid.getCellVisible(i,j),i,j);
                numberField.getChildren().add(referenceNode);
            }
        }

        totalPane.getChildren().addAll(background,numberField);

        layout.setBottom(totalPane);
    }

    private Node getCellNode(char c, int i, int j) {
        if (Character.isDigit(c)) {
            Label referenceNode = new Label((String.valueOf(Integer.parseInt((String.valueOf(c))))));
            referenceNode.setMinSize(CELL_SIDE,CELL_SIDE);
            referenceNode.setMaxSize(CELL_SIDE,CELL_SIDE);
            return referenceNode;
        } else {
            TextField referenceNode = new TextField();
            referenceNode.setMinSize(CELL_SIDE,CELL_SIDE);
            referenceNode.setMaxSize(CELL_SIDE,CELL_SIDE);
            numberReferenceField[i][j] = referenceNode;
            return referenceNode;
        }
    }

    private void updateGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (numberReferenceField[i][j] != null) {
                    int cellValue = parseTextField(numberReferenceField[i][j]);
                    sudokuGrid.changeCellValue(i,j,cellValue);
                }
            }
        }
    }

    private int parseTextField(TextField t) {
        String content;
        content = t.getText();
        if (content.length() == 1 && Character.isDigit(content.charAt(0))) {
            return Integer.parseInt(content);
        } else {
            return 0;
        }
    }

    private void setupBoardImage() {
        try {
            sudokuBoardInput = new FileInputStream("./data/sudokuImages/sudokuBoard.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        sudokuBoardImage = new Image(sudokuBoardInput);
    }
}
