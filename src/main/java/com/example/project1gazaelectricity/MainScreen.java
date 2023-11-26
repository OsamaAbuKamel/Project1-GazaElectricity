package com.example.project1gazaelectricity;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainScreen extends BorderPane {
    private RecordList list;
    private Button btnUpload = new Button();
    private Button btnManagement = new Button("Management");
    private Button btnStatistics = new Button("Statistics");
    private Button btnSave = new Button();
    private FileChooser chooser = new FileChooser();
    private HBox hBox = new HBox();
    private Scene scene;
    private Image gifImageUpload = new Image(
            "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\upload.gif");
    private Image gifImageDownload = new Image(
            "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\download.gif");
    private ImageView uploadImage = new ImageView(gifImageUpload);
    private ImageView downloadImage = new ImageView(gifImageDownload);
    private Tooltip toolTip = new Tooltip("Upload File");
    public MainScreen(RecordList list) {
        this.list=list;
        toolTip.setTextAlignment(TextAlignment.LEFT);
        btnUpload.setTooltip(toolTip);
        style();
        handle(list);
        hBox.getChildren().addAll(btnUpload, btnManagement, btnStatistics, btnSave);
        setBottom(hBox);
    }
      private void style() {
        hBox.setPadding(new Insets(24));
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(24);
        setPadding(new Insets(16));
        uploadImage.setFitHeight(50);
        uploadImage.setFitWidth(50);
        downloadImage.setFitHeight(50);
        downloadImage.setFitWidth(50);
        btnUpload.setGraphic(uploadImage);
        btnSave.setGraphic(downloadImage);
        btnUpload.setStyle("-fx-background-color: transparent;");
        btnSave.setStyle("-fx-background-color: transparent;");
        btnManagement.setStyle("-fx-background-color: #ffffff;");
        btnStatistics.setStyle("-fx-background-color: #ffffff;");
        btnManagement.setPrefSize(100, 50);
        btnStatistics.setPrefSize(100, 50);
    }

    private void handle(RecordList list) {
        btnUpload.setOnAction(e -> {
            chooser.setTitle("Open File");
            File file = chooser.showOpenDialog(null);
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
                // disable(true);
            }
        });
        btnManagement.setOnAction(e->{
            SceneChanger.changeScene(new ManagementScreen(list)); 
        });
        btnStatistics.setOnAction(e->{
            SceneChanger.changeScene(new StatisticsScreen(list));
        });
        btnSave.setOnAction(e -> {
            SceneChanger.changeScene(new SaveScreen(list));
        });
    }
    private void alert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}