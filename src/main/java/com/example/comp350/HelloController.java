package com.example.comp350;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import RoomService.java;
public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    public Button Bookingbtn;
    public void bookingAction(){


    }
    public Button roomservicebtn;
    public void roomServiceAction(){
        RoomService service1() = new RoomService();
    }
}