package com.labProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

public class Main extends Application {
    public static Godown godown;
    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Line1");
        javafx.scene.layout.AnchorPane _sampleFXML = FXMLLoader.load(getClass().getResource("Consumer.fxml"));
        System.out.println("Line2");
        Scene _sample = new Scene(_sampleFXML);
        System.out.println("Line3");
        primaryStage.setScene(_sample);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
