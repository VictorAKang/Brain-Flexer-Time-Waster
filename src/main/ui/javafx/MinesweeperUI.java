package ui.javafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.minesweeper.Grid;

import java.io.FileInputStream;
import java.io.IOException;

// the ui part of the minesweeper game
public class MinesweeperUI {
    private static FileInputStream closedTileInput;
    private static FileInputStream flagTileInput;
    private static FileInputStream zeroTileInput;
    private static FileInputStream oneTileInput;
    private static FileInputStream twoTileInput;
    private static FileInputStream threeTileInput;
    private static FileInputStream fourTileInput;
    private static FileInputStream fiveTileInput;
    private static FileInputStream sixTileInput;
    private static FileInputStream severTileInput;
    private static FileInputStream eightTileInput;
    private static FileInputStream mineTileInput;

    private static Image closedTile;
    private static Image flagTile;
    private static Image zeroTile;
    private static Image oneTile;
    private static Image twoTile;
    private static Image threeTile;
    private static Image fourTile;
    private static Image fiveTile;
    private static Image sixTile;
    private static Image sevenTile;
    private static Image eightTile;
    private static Image mineTile;


    private static final int CELL_SIDE = 32;

    // button grid has the same orientation as the minesweeper grid
    private static Button[][] buttonField = new Button[Grid.LONG_SIDE][Grid.SHORT_SIDE];
    private static Grid minesweeperGrid = new Grid();

    private static boolean flagMode;

    private static BorderPane layout;
    private static Scene minesweeperScene;

    public MinesweeperUI() {
        setupImageInputs();
        setupImages();
    }

    //MODIFIES: this
    //EFFECTS: sets up all file input stream
    private void setupImageInputs() {
        try {
            closedTileInput = new FileInputStream("./data/minesweeperImages/closed_tile.jpg");
            flagTileInput = new FileInputStream("./data/minesweeperImages/flag_tile.jpg");
            zeroTileInput = new FileInputStream("./data/minesweeperImages/empty_tile.jpg");
            oneTileInput = new FileInputStream("./data/minesweeperImages/1_tile.jpeg");
            twoTileInput = new FileInputStream("./data/minesweeperImages/2_tile.jpeg");
            threeTileInput = new FileInputStream("./data/minesweeperImages/3_tile.jpeg");
            fourTileInput = new FileInputStream("./data/minesweeperImages/4_tile.jpeg");
            fiveTileInput = new FileInputStream("./data/minesweeperImages/5_tile.jpeg");
            sixTileInput = new FileInputStream("./data/minesweeperImages/6_tile.jpeg");
            severTileInput = new FileInputStream("./data/minesweeperImages/7_tile.jpg");
            eightTileInput = new FileInputStream("./data/minesweeperImages/8_tile.jpg");
            mineTileInput = new FileInputStream("./data/minesweeperImages/mine_tile.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up all images
    private void setupImages() {
        closedTile = new Image(closedTileInput);
        flagTile = new Image(flagTileInput);
        zeroTile = new Image(zeroTileInput);
        oneTile = new Image(oneTileInput);
        twoTile = new Image(twoTileInput);
        threeTile = new Image(threeTileInput);
        fourTile = new Image(fourTileInput);
        fiveTile = new Image(fiveTileInput);
        sixTile = new Image(sixTileInput);
        sevenTile = new Image(severTileInput);
        eightTile = new Image(eightTileInput);
        mineTile = new Image(mineTileInput);
    }

    //MODIFIES: this
    //EFFECTS: sets up the scene and returns it
    protected Scene display() {
        //minesweeperGrid.setAllClosed();

        flagMode = false;
        minesweeperGrid.genGrid();

        layout = new BorderPane();
        setMenuField();
        setButtonField();

        minesweeperScene = new Scene(layout);

        return minesweeperScene;
    }

    //MODIFIES: this
    //EFFECTS: sets up the menu buttons on the top
    private void setMenuField() {

        HBox buttonLayout = new HBox(5);

        Button resetGameButton = new Button();
        resetGameButton.setText("Reset");
        resetGameButton.setOnAction(e -> {
            flagMode = false;
            minesweeperGrid.genGrid();
            //minesweeperGrid.setAllClosed();
            setButtonField();
        });

        Button flagButton = new Button();
        flagButton.setText("Flag");
        flagButton.setOnAction(e -> {
            flagMode = !flagMode;
            System.out.println(flagMode);
        });

        Button closeGameButton = new Button();
        closeGameButton.setText("Close");
        closeGameButton.setOnAction(e -> MainMenuUI.goBackMainMenu());

        buttonLayout.getChildren().addAll(flagButton,resetGameButton,closeGameButton);
        buttonLayout.setAlignment(Pos.CENTER);

        layout.setTop(buttonLayout);
    }

    //MODIFIES: this
    //EFFECTS: sets up the button field that will make up the minesweeper game
    private void setButtonField() {

        FlowPane fieldLayout = new FlowPane();
        fieldLayout.setPrefWrapLength(CELL_SIDE * Grid.LONG_SIDE);
        fieldLayout.setStyle("-fx-background-color: #C0C0C0");

        Button referenceButton;

        for (int j = 0; j < Grid.SHORT_SIDE; j++) {
            for (int i = 0; i < Grid.LONG_SIDE; i++) {
                referenceButton = setButton();
                buttonField[i][j] = referenceButton;
                fieldLayout.getChildren().add(referenceButton);
            }
        }

        layout.setCenter(fieldLayout);
    }

    //EFFECTS: returns a field button with the correct behaviour
    private Button setButton() {
        Button referenceButton = new Button();

        referenceButton.setMinSize(CELL_SIDE,CELL_SIDE);
        referenceButton.setMaxSize(CELL_SIDE,CELL_SIDE);

        closedTileImageButton(referenceButton);

        referenceButton.setOnAction(e -> {
            if (flagMode) {
                flagButton(referenceButton);
            } else {
                openButton(referenceButton);
            }
        });

        return referenceButton;
    }

    //MODIFIES: this
    //EFFECTS: takes a button and opens it if its not a bomb or flagged
    //         if its a bomb disable all buttons
    //         if its a flag does nothing
    private void openButton(Button b) {
        int coordinateI = findIButton(b);
        int coordinateJ = findJButton(b);

        boolean stubVar = minesweeperGrid.openCell(coordinateJ, coordinateI);

        if (!stubVar) {
            refreshDisplay();
        } else if (minesweeperGrid.isMine(coordinateI, coordinateJ)) {
            disableAllButton();
        }
    }

    //MODIFIES: this
    //EFFECTS: goes through all the buttons and changes the images of those which are open
    private void refreshDisplay() {
        for (int i = 0; i < Grid.LONG_SIDE; i++) {
            for (int j = 0; j < Grid.SHORT_SIDE; j++) {
                if (minesweeperGrid.getIsOpen(i, j)) {
                    setButtonNumberImage(i,j);
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: takes the coordinates of a button and changes its image to the corresponding one
    private void setButtonNumberImage(int coordinateI, int coordinateJ) {
        Button b;
        b = buttonField[coordinateI][coordinateJ];
        b.setDisable(true);
        int number = minesweeperGrid.getNumber(coordinateI,coordinateJ);
        ImageView referenceView;
        referenceView = numberToImageView(number);
        b.setGraphic(referenceView);
    }

    //MODIFIES: this
    //EFFECTS: takes a button and alters its flag state and image
    private void flagButton(Button b) {
        int coordinateI = findIButton(b);
        int coordinateJ = findJButton(b);

        System.out.println(coordinateI);
        System.out.println(coordinateJ);

        if (minesweeperGrid.isFlagged(coordinateI,coordinateJ)) {
            closedTileImageButton(b);
        } else {
            flagImageButton(b);
        }
        minesweeperGrid.flagCell(coordinateI,coordinateJ);
    }

    //MODIFIES: this
    //EFFECTS: sets up the given button with the closed tile image
    private void closedTileImageButton(Button b) {
        ImageView referenceView;
        referenceView = new ImageView(closedTile);

        referenceView.setFitWidth(32);
        referenceView.setFitHeight(32);

        b.setGraphic(referenceView);
    }

    //MODIFIES: this
    //EFFECTS: sets the given button image to the flag tile image
    private void flagImageButton(Button b) {
        ImageView referenceView;
        referenceView = new ImageView(flagTile);

        referenceView.setFitWidth(32);
        referenceView.setFitHeight(32);

        b.setGraphic(referenceView);
    }

    //MODIFIES: this
    //EFFECTS:
    private void disableAllButton() {
        for (int i = 0; i < Grid.LONG_SIDE; i++) {
            for (int j = 0; j < Grid.SHORT_SIDE; j++) {
                buttonField[i][j].setDisable(true);

                if (minesweeperGrid.isMine(i,j) && !minesweeperGrid.isFlagged(i,j)) {
                    setImageButtonMine(buttonField[i][j]);
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up given button with the image of a bomb
    private void setImageButtonMine(Button b) {
        ImageView referenceView;
        referenceView = new ImageView(mineTile);

        referenceView.setFitHeight(32);
        referenceView.setFitWidth(32);

        b.setGraphic(referenceView);
    }

    //EFFECTS: returns the corresponding ImageView to the number given
    private ImageView numberToImageView(int number) {
        ImageView referenceView;

        if (number == 1) {
            referenceView = new ImageView(oneTile);
        } else if (number == 2) {
            referenceView = new ImageView(twoTile);
        } else if (number == 3) {
            referenceView = new ImageView(threeTile);
        } else if (number == 4) {
            referenceView = new ImageView(fourTile);
        } else if (number == 5) {
            referenceView = new ImageView(fiveTile);
        } else if (number == 6) {
            referenceView = new ImageView(sixTile);
        } else if (number == 7) {
            referenceView = new ImageView(sevenTile);
        } else if (number == 8) {
            referenceView = new ImageView(eightTile);
        } else {
            referenceView = new ImageView(zeroTile);
        }

        referenceView.setFitWidth(32);
        referenceView.setFitHeight(32);

        return referenceView;
    }

    //EFFECTS: returns the i coordinate of the given button
    private int findIButton(Button b) {
        for (int i = 0; i < Grid.LONG_SIDE; i++) {
            for (int j = 0; j < Grid.SHORT_SIDE; j++) {
                if (buttonField[i][j].equals(b)) {
                    return i;
                }
            }
        }

        return 0;
    }

    //EFFECTS: returns the j coordinate of the given button
    private int findJButton(Button b) {
        for (int i = 0; i < Grid.LONG_SIDE; i++) {
            for (int j = 0; j < Grid.SHORT_SIDE; j++) {
                if (buttonField[i][j].equals(b)) {
                    return j;
                }
            }
        }

        return 0;
    }
}


//        if (!minesweeperGrid.isFlagged(coordinateI,coordinateJ)) {
//            if (minesweeperGrid.isMine(coordinateI,coordinateJ)) {
//                //System.out.println("is mine");
//                disableAllButton();
//            } else {
//                setButtonNumber(b,coordinateI,coordinateJ);
//            }
//        } else {
//            System.out.println("a");
//        }


//    private void openButton(Button b) {
//        int coordinateI = findIButton(b);
//        int coordinateJ = findJButton(b);
//
//        //System.out.println(minesweeperGrid.isFlagged(coordinateI,coordinateJ));
//
//        if (!minesweeperGrid.isFlagged(coordinateI,coordinateJ)) {
//            if (minesweeperGrid.isMine(coordinateI,coordinateJ)) {
//                //System.out.println("is mine");
//                disableAllButton();
//            } else {
//                setButtonNumber(b,coordinateI,coordinateJ);
//            }
//        } else {
//            System.out.println("a");
//        }
//    }
//
//    private void setButtonNumber(Button b, int coordinateI, int coordinateJ) {
//        minesweeperGrid.setOpen(coordinateI,coordinateJ);
//        setButtonNumberImage(b,coordinateI,coordinateJ);
//        setAdjacentNumber(coordinateI, coordinateJ);
//    }
//
//    private void setButtonNumberImage(Button b, int coordinateI, int coordinateJ) {
//        b.setDisable(true);
//        int number = minesweeperGrid.getNumber(coordinateI,coordinateJ);
//        ImageView referenceView;
//        referenceView = numberToImageView(number);
//        b.setGraphic(referenceView);
//    }
//
//    private void setAdjacentNumber(int coordinateI, int coordinateJ) {
//        if (minesweeperGrid.getNumber(coordinateI,coordinateJ) == 0) {
//            int minI = coordinateI - 1;
//            int maxI = coordinateI + 1;
//            int minJ = coordinateJ - 1;
//            int maxJ = coordinateJ + 1;
//
//            if (coordinateI == 0) {
//                minI = coordinateI;
//            } else if (coordinateI == Grid.LONG_SIDE - 1) {
//                maxI = coordinateI;
//            }
//
//            if (coordinateJ == 0) {
//                minJ = coordinateJ;
//            } else if (coordinateJ == Grid.SHORT_SIDE - 1) {
//                maxJ = coordinateJ;
//            }
//
//            System.out.println(minesweeperGrid.getIsOpen(minI, coordinateJ));
//            openAdjacentHelper(coordinateI,coordinateJ,minI,maxI,minJ,maxJ);
//        }
//    }
//
//    private void openAdjacentHelper(int coordinateI, int coordinateJ, int minI, int maxI, int minJ, int maxJ) {
//        for (int i = minI; i <= maxI; i++) {
//            for (int j = minJ; j <= maxJ; j++) {
//                //System.out.println(!minesweeperGrid.getIsOpen(coordinateI, coordinateJ));
////                if (i == minI && j == coordinateJ) {
////                    System.out.println(minesweeperGrid.getIsOpen(i, j));
////                    System.out.println(!(i == coordinateI && j == coordinateJ));
////                }
//               if (!((i == coordinateI && j == coordinateJ) && minesweeperGrid.getIsOpen(coordinateI, coordinateJ))) {
//                    minesweeperGrid.setOpen(i, j);
//                    setButtonNumber(buttonField[i][j], i, j);
//                    //setAdjacentNumber(i, j);
//                }
//            }
//        }
//    }

