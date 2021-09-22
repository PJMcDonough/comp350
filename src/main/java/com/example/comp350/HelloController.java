package com.example.comp350;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public Button makeReservation;
    public void onReservationClick()
    {
        System.out.println("Debug Israel Patrick");
    }
/*
    @FXML
    protected void setMakeReservation()
    {

    }*/

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}