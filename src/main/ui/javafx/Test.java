package ui.javafx;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Test extends Scene {
    static StackPane layout = new StackPane();

    public Test() {
        super(layout);

        layout.setStyle("-fx-background-color: teal;");
    }



}
