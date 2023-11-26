package com.example.project1gazaelectricity;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChanger {

    private static Stage mainStage;
    
    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void changeScene(Parent scene) {
        Scene mainScene = new Scene(scene,1120,720);
        mainStage.setScene(mainScene);
        mainStage.sizeToScene();  
        mainStage.centerOnScreen();

    }
}