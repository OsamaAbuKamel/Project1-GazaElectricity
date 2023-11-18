package com.example.project1gazaelectricity;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application{

// A main method to test the code
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    VBox pane = new VBox();
    // LocalDate today = LocalDate.now();

    DatePicker picker = new DatePicker();
    Label label = new Label();
    picker.valueProperty().addListener((obs, oldDate, newDate) -> {
      label.setText(newDate.toString()); 
    });
    pane.getChildren().addAll(picker,label);
    Scene scene = new Scene(pane,300,400);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    
  }
}