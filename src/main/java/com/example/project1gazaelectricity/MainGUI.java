package com.example.project1gazaelectricity;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainGUI extends Application {
    private RecordList list = new RecordList();
    private Button btnUpload = new Button("Upload File");
    private Button btnManagement = new Button("Management");
    private Button btnStatistics = new Button("Statistics");
    private Button btnSave = new Button("Save File");
    private FileChooser chooser = new FileChooser();
    private HBox hBox = new HBox();
    private VBox vBox = new VBox();
    private Scene scene;
    private Image gifImage = new Image(
            "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\system-solid-49-upload-file (1).gif");

    // Create ImageView with the GIF image
    private ImageView imageView = new ImageView(gifImage);

    @Override
    public void start(Stage primaryStage) throws Exception {
        handle(primaryStage);
        disable(false);
        btnUpload.setGraphic(imageView);
        hBox.getChildren().addAll(btnUpload, btnManagement, btnStatistics, btnSave);
        vBox.getChildren().addAll(hBox);
        scene = new Scene(vBox);
        primaryStage.setTitle("Electricity Record");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(
                "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\electricity.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void handle(Stage stage) {
        File initialDirectory = new File("C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src");
        btnUpload.setOnAction(e -> {
            chooser.setInitialDirectory(initialDirectory);
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(stage);
            if (file == null) {
                alert(Alert.AlertType.ERROR, "Error", "File Is Null");
            } else if (!file.exists()) {
                alert(Alert.AlertType.ERROR, "Error", "File not found");
            } else if (!file.isFile()) {
                alert(Alert.AlertType.ERROR, "Error", "File is not a file");
            } else {
                String fileName = file.getAbsolutePath();
                list.loadFile(fileName);
            }
            if (file != null) {
                disable(true);
            }
        });
        btnSave.setOnAction(event -> {
            chooser.setTitle("Save File");
            File file = chooser.showSaveDialog(stage);
            if (file != null) {
                list.saveFile(file.toString());
            }
        });
        btnManagement.setOnAction(e -> {
            scene.setRoot(new ManagementScreen(list));
        });
    }

    private void disable(boolean flag) {
        btnStatistics.setVisible(flag);
        btnManagement.setVisible(flag);
        btnSave.setVisible(flag);
    }

    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
