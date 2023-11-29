package com.example.project1gazaelectricity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
          Button btn = new Button("Upload");

        Tooltip tt = new Tooltip("Upload File");
        tt.setText("Text on Hover");
        tt.setFont(new Font("Langdon", 14));
        tt.setStyle("-fx-base: #AE3522; -fx-text-fill: orange;");

        StackPane root = new StackPane(btn);
        // Tooltip.attach(btn, tt, Tooltip.Pos.BOTTOM);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Tooltip Demo");
        primaryStage.setScene(scene);
        primaryStage.show();}
    public static void main(String[] args) {
        launch(args);
    }

}