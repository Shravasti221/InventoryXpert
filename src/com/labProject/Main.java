package com.labProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;

public class Main extends Application {
    public static Godown godown;
    @Override
    public void start(Stage primaryStage) throws Exception{
        javafx.scene.layout.AnchorPane _sampleFXML = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene _sample = new Scene(_sampleFXML);
        primaryStage.setScene(_sample);
        primaryStage.show();
    }


    public static void main(String[] args) {
        godown = new Godown();
        launch(args);
    }
}
