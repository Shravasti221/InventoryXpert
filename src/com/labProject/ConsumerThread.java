package com.labProject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsumerThread extends Application implements Runnable {
    Consumer c;
    public ConsumerThread(Consumer C){
        this.c = C;
    }

    public void run(){
        Stage stage = new Stage();
        try {
            this.start(stage);
        }catch (Exception e){
            System.out.println("Consumer :"+ this.c.getID() + "'s stage could not be set up." );
            e.printStackTrace();
            Platform.exit();
        }

    }
    public void start(Stage ConsumerStage) throws Exception{
        javafx.scene.layout.AnchorPane _consumerLayout = FXMLLoader.load(getClass().getResource("Consumer.fxml"));
        Scene _consumerScene = new Scene(_consumerLayout);
        ConsumerStage.setUserData(c);
        ConsumerStage.setScene(_consumerScene);
        ConsumerStage.show();
    }


}
