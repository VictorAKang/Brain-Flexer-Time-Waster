package ui.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class MainJavaFX extends Application {
    Scene scene1;
    Scene scene2;
    TextArea textArea;
    Label label;
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main menu");

        button = new Button();
        textArea = new TextArea();
        textArea.setMaxHeight(12.0);
        textArea.setMaxWidth(120.0);
        label = new Label();
        label.setMaxSize(50,100);
        label.setTextFill(Color.web("#10DFBA"));
        //label.getGraphic().setStyle("-fx-text-fill: #000083;");
        button.setText("Change text!");
        button.setOnAction(e -> label.setText(textArea.getText()));//primaryStage.setTitle(textArea.getText()));

        //StackPane layout = new StackPane();
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
//        StackPane layout = new StackPane();
//        layout.getChildren().add(stub);

        layout.getChildren().addAll(textArea,button,label);
        //layout.getChildren().add(button);

        Scene scene = new Scene(layout, 500,400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
