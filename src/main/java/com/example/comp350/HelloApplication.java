package com.example.comp350;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            stage.setTitle("MiYE!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){System.out.println(e);}
    }

    public static void main(String[] args) {
        launch();
    }
}
