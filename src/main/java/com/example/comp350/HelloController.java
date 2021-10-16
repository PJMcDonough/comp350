package com.example.comp350;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public Button makeReservation;
    public void onMakeReservationClick() throws SQLException {
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
    public void onViewReservationClick() throws SQLException {
        SpaReservation.displayReservation();
    }

    public void onRemoveReservationClick() throws SQLException {SpaReservation.removeReservation();}

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    /*public Button Bookingbtn;
    public void bookingAction() {
        Booking book = new Booking();
        book.book();
    }*/
    @FXML
    private void handleButtonAction ()
    {
        try {
            String name = "make-reservation-page.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(name));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Make Reservation");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }
}