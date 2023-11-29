package com.example.project1gazaelectricity;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainGUI extends Application {
    private RecordList list;

    @Override
    public void start(Stage stage) throws Exception {
        list = new RecordList();
        SceneChanger.setMainStage(stage);
        SceneChanger.changeScene(new MainScreen(list));
        stage.getIcons().add(new Image(
                "C:\\Users\\osama\\repos\\Project1-GazaElectricity\\src\\main\\resources\\com\\example\\project1gazaelectricity\\electricity.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
