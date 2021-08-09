package com.labProject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProducerThread extends Application implements Runnable {
    Producer p;
    public ProducerThread(Producer P){
        this.p = P;
    }

    public void run(){
        Stage stage = new Stage();
        try {
            this.start(stage);
        }catch (Exception e){
            System.out.println("Producer :"+ this.p.getID() + "'s stage could not be set up." );
            e.printStackTrace();
            Platform.exit();
        }

    }
    public void start(Stage ProducerStage) throws Exception{
        javafx.scene.layout.AnchorPane _producerLayout = FXMLLoader.load(getClass().getResource("Producer.fxml"));
        Scene _producerScene = new Scene(_producerLayout);
        ProducerStage.setUserData(p);
        ProducerStage.setScene(_producerScene);
        ProducerStage.show();
    }


}
