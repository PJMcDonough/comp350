package com.example.comp350;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.css.Stylesheet;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 900);
            stage.setTitle("MiYE!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){System.out.println(e);}
    }

    public static void main(String[] args) {
        launch();
    }
}
