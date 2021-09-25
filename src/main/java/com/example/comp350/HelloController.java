package com.example.comp350;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public Button makeReservation;
    public void onMakeReservationClick()
    {
        /*
        System.out.println("When and what kind?");
        Scanner testing = new Scanner(System.in);
        String when = testing.next();
        String whatKind = testing.next();

        System.out.println("The Customer wants a " + whatKind + " at " + when);
         */
        SpaReservation.makeSpaReservation();
    }

    @FXML
    public void onViewReservationClick()
    {
        SpaReservation.displayReservation();
    }

    public void onRemoveReservationClick() {SpaReservation.removeReservation();}

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}