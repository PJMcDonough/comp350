package com.example.comp350;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class HelloController {
    public Button setReservation;

    private final int HEIGHT = 1920;
    private final int WIDTH = 1080;

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

    /* @FXML
     protected void onHelloButtonClick() {
         welcomeText.setText("Welcome to JavaFX Application!");
     }
   /*public Button Bookingbtn;
     public void bookingAction() {
         Booking book = new Booking();
         book.book();
     }*/


    public void makePage(String fxml, String title, ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root1 = fxmlLoader.load();
        //Replaces existing window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root1,HEIGHT,WIDTH));
        stage.show();
    }

    @FXML
    private void handleButtonActionHome(ActionEvent event)
    {
        try {
            String name = "hello-view.fxml";
            String title = "MiYe";
            makePage(name, title,event);
        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForMake (ActionEvent event)
    {
        try {
            String name = "make-reservation-page.fxml";
            String title = "Make Reservation";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }

    @FXML
    private void handleButtonActionForView (ActionEvent event)
    {
        try {
            String name = "view-reservation-page.fxml";
            String title = "View Reservation";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }
    @FXML
    private void handleButtonActionForRemove (ActionEvent event)
    {
        try {
            String name = "remove-reservation-page.fxml";
            String title = "Remove Reservation";
            makePage(name, title,event);

        } catch (Exception e) {
            System.out.println("Can't load a new window");
        }
    }
}