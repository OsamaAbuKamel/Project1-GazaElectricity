package com.example.project1gazaelectricity;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application{

// A main method to test the code
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Pane pane = new Pane();
    pane.getChildren().addAll(new DatePicker());
    Scene scene = new Scene(pane,300,400);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    
  }
}