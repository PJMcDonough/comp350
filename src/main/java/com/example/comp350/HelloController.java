package com.example.comp350;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    public Button aboutUsBtn;
    public void aboutUsAction(){
        System.out.println("Patrick, David, Israel, Jeyner");
    }
}