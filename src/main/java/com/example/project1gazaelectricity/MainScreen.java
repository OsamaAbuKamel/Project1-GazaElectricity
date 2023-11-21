package com.example.project1gazaelectricity;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainScreen extends VBox {
    private Button btnUpload = new Button("Upload File");
    private Button btnManagement = new Button("Management");
    private Button btnStatistics = new Button("Statistics");
    private Button btnSave = new Button("Save File");
    private FileChooser chooser = new FileChooser();
    private HBox hBox = new HBox();
    private RecordList list ;
    private Stage stage;
    private Scene   scene;
    public MainScreen(RecordList list ,Stage stage,Scene scene) {

        this.list=list;
        this.stage = stage;
        this.scene=scene;
        handle();
        disable(false);
        hBox.getChildren().addAll(btnUpload,btnManagement,btnStatistics,btnSave);
        getChildren().addAll(hBox);
    }
    private void handle(){
        File initialDirectory = new File("C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src");
        btnUpload.setOnAction(e->{
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
                // list.loadFile(fileName);
            }
            if (file != null){
                disable(true);
            }
        });
        btnSave.setOnAction(event ->{
            chooser.setTitle("Save File");
            File file = chooser.showSaveDialog(stage);
            if (file != null){
                // list.saveFile(file.toString());
            }
        });
        btnManagement.setOnAction(e->{
            scene = new Scene(new ManagementScreen(list));
            stage.setScene(scene);      });
    }
    private void disable(boolean flag){
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
