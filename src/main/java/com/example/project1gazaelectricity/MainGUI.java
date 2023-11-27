package com.example.project1gazaelectricity;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {
    private RecordList list;
    //TODO!: oprations in tableView
    //TODO?: equals in ElctricityRecord
    // TODO*: date in tableview
    // TODO: Complete statistics Screen

    @Override
    public void start(Stage stage) throws Exception {
        list = new RecordList();
        SceneChanger.setMainStage(stage);
        SceneChanger.changeScene(new MainScreen(list));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
